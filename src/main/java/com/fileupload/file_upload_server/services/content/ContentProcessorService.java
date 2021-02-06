package com.fileupload.file_upload_server.services.content;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.file_upload_server.connection.IConnectionManager;
import com.fileupload.file_upload_server.exceptions.ContentParsingException;
import com.fileupload.file_upload_server.model.FileType;
import com.fileupload.file_upload_server.model.IMessage;
import com.fileupload.file_upload_server.processors.IFileProcessor;
import com.fileupload.file_upload_server.processors.IFileProcessorFactory;
import com.fileupload.file_upload_server.properties.IConfigProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Return;
import com.rabbitmq.client.ReturnCallback;

/**
 * The Class ContentProcessorService.
 * @author Rohan
 */
@Service
@Component("Processor")
public class ContentProcessorService implements IService {

	/** The file processor factory. */
	private final IFileProcessorFactory fileProcessorFactory;
	
	/** The connection manager. */
	private final IConnectionManager connectionManager;
	
	/** The config properties. */
	private final IConfigProperties configProperties;
	
	/** The executor. */
	private final ExecutorService executor;

	/**
	 * Instantiates a new content processor service.
	 *
	 * @param fileProcessorFactory the file processor factory
	 * @param connectionManager the connection manager
	 * @param configProperties the config properties
	 */
	@Autowired
	public ContentProcessorService(final IFileProcessorFactory fileProcessorFactory,
			final IConnectionManager connectionManager, final IConfigProperties configProperties) {
		this.fileProcessorFactory = fileProcessorFactory;
		this.connectionManager = connectionManager;
		this.configProperties = configProperties;
		this.executor=Executors.newFixedThreadPool(5);
	}

	/* (non-Javadoc)
	 * @see com.fileupload.file_upload_server.services.content.IService#process(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public String process(MultipartFile file) throws ContentParsingException {
		IFileProcessor fileProcessor = fileProcessorFactory.createProcessor(FileType.valueOf(file.getContentType()
				.substring(file.getContentType().lastIndexOf("/") + 1, file.getContentType().length()).toUpperCase()));
		List<IMessage> records;
		try {
			records = fileProcessor.processFileContent(file.getInputStream());
		} catch (IOException e) {
			throw new ContentParsingException(e.getMessage());
		}
		executor.execute(new Runnable() {

			@Override
			public void run() {
				try {
					Connection connection = connectionManager.getConnection();
					Channel channel = connection.createChannel();
					String exchange = configProperties.getExchange();
					channel.exchangeDeclare(exchange, BuiltinExchangeType.FANOUT);
					String routingKey = RandomStringUtils.randomAlphanumeric(5);
					channel.queueDeclare(routingKey, false, false, false, null);
					channel.addReturnListener(new ReturnCallback() {

						@Override
						public void handle(Return returnMessage) {
							System.out.println("Returned:" + returnMessage);
						}
					});
					for (IMessage record : records) {
						String serialized = record.toString();
						System.out.println(serialized);
						channel.basicPublish(exchange, routingKey, null, serialized.getBytes());
					}
				} catch (Exception e) {
					e.printStackTrace();
					connectionManager.closeConnection();
				} finally {
				}
			}
		});
		return StringUtils.cleanPath(file.getOriginalFilename()) + ":Records:" + records.size();

	}

}
