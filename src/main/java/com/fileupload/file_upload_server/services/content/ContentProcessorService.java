package com.fileupload.file_upload_server.services.content;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.file_upload_server.configuration.TimerTask;
import com.fileupload.file_upload_server.connection.IConnectionManager;
import com.fileupload.file_upload_server.exceptions.ContentParsingException;
import com.fileupload.file_upload_server.model.FileType;
import com.fileupload.file_upload_server.model.IMessage;
import com.fileupload.file_upload_server.processors.IFileProcessor;
import com.fileupload.file_upload_server.processors.IFileProcessorFactory;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * The Class ContentProcessorService.
 * 
 * @author Rohan
 */
@Service
@Component("Processor")
public class ContentProcessorService implements IService {

	/** The file processor factory. */
	private final IFileProcessorFactory fileProcessorFactory;

	/** The connection manager. */
	private final IConnectionManager connectionManager;

	/** The executor. */
	private final ExecutorService executor;

	private final Logger logger;

	/**
	 * Instantiates a new content processor service.
	 *
	 * @param fileProcessorFactory the file processor factory
	 * @param connectionManager    the connection manager
	 * @param configProperties     the config properties
	 */
	@Autowired
	public ContentProcessorService(final IFileProcessorFactory fileProcessorFactory,
			final IConnectionManager connectionManager, final ExecutorService executor, final Logger logger) {
		this.fileProcessorFactory = fileProcessorFactory;
		this.connectionManager = connectionManager;
		this.executor = executor;
		this.logger = logger;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fileupload.file_upload_server.services.content.IService#process(org.
	 * springframework.web.multipart.MultipartFile)
	 */
	@Override
	public String process(MultipartFile file,String fileTypeStr) throws ContentParsingException {
		FileType fileType = FileType.valueOf(fileTypeStr);
		IFileProcessor fileProcessor = fileProcessorFactory.createProcessor(fileType);
		List<IMessage> records;
		try {
			records = fileProcessor.processFileContent(file.getInputStream());
		} catch (IOException e) {
			throw new ContentParsingException(e.getMessage());
		}
		logger.info("File received: " + file.getOriginalFilename());
		TimerTask task = new TimerTask(file.getOriginalFilename());
		task.start();
		executor.execute(new Runnable() {

			@Override
			public void run() {
				task.completeStage("Queued");
				Channel channel = null;
				try {
					Connection connection = connectionManager.getConnection();
					channel = connection.createChannel();
					String exchange = fileType.name();
					channel.exchangeDeclare(exchange, BuiltinExchangeType.FANOUT);
					String routingKey = System.getenv("routing_key");
					channel.queueDeclare(routingKey+"_"+exchange, false, false, false, null);
					for (IMessage record : records) {
						String serialized = record.toString();
						channel.basicPublish(exchange, routingKey, null, serialized.getBytes());
					}
				} catch (Exception e) {
					logger.error("Exception encountered: " + e.getMessage());
					connectionManager.closeConnection();
				} finally {
					try {
						channel.close();
					} catch (IOException | TimeoutException e) {
						logger.error("Exception encountered: " + e.getMessage());
					}
					logger.info(
							"File transferred: " + file.getOriginalFilename() + "\t" + "Records: " + records.size());
					task.processingComplete();
				}
			}
		});
		return StringUtils.cleanPath(file.getOriginalFilename()) + ":Records:" + records.size();

	}

}
