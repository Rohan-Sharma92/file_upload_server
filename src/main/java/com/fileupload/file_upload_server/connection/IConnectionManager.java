package com.fileupload.file_upload_server.connection;

import com.rabbitmq.client.Connection;

/**
 * The Interface IConnectionManager.
 * @author Rohan
 */
public interface IConnectionManager {

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public Connection getConnection();
	
	/**
	 * Close connection.
	 */
	public void closeConnection();
}
