package com.fileupload.file_upload_server.model;

import java.io.Serializable;
import java.util.Map;

/**
 * The Interface IMessage.
 * @author Rohan
 */
public interface IMessage extends Serializable{

	/**
	 * Gets the contents.
	 *
	 * @return the contents
	 */
	public Map<String,String> getContents();
	
}
