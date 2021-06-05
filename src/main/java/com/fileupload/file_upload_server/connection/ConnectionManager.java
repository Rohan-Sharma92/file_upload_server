package com.fileupload.file_upload_server.connection;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * The Class ConnectionManager.
 * @author Rohan
 */
@Component
public class ConnectionManager implements IConnectionManager {

	/** The connection. */
	private Connection connection;
	
	/** The object lock. */
	private Object objectLock= new Object();

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
					String url = System.getenv("url");
					System.out.println("URL:"+url);
					ConnectionFactory connectionFactory = new ConnectionFactory();
					connectionFactory.setUri(url);
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
