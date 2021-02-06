package com.fileupload.file_upload_server.connection;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fileupload.file_upload_server.properties.IConfigProperties;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * The Class ConnectionManager.
 * @author Rohan
 */
@Component
public class ConnectionManager implements IConnectionManager {

	/** The config properties. */
	private final IConfigProperties configProperties;

	/** The connection. */
	private Connection connection;
	
	/** The object lock. */
	private Object objectLock= new Object();

	/**
	 * Instantiates a new connection manager.
	 *
	 * @param configProperties the config properties
	 */
	@Autowired
	public ConnectionManager(final IConfigProperties configProperties) {
		this.configProperties = configProperties;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Connection getConnection() {
		if (connection == null) {
			synchronized (objectLock) {
				if(connection!=null)
					return connection;
				try {
					ConnectionFactory connectionFactory = new ConnectionFactory();
					connectionFactory.setUri(configProperties.getConnectionURL());
					connection = connectionFactory.newConnection();
				} catch (KeyManagementException | NoSuchAlgorithmException | URISyntaxException | IOException
						| TimeoutException e) {
					e.printStackTrace();
				}
				return connection;
			}
		}
		return connection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
