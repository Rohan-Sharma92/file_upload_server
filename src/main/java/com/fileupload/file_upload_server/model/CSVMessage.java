package com.fileupload.file_upload_server.model;

import java.util.Map;

/**
 * The Class CSVMessage.
 * @author Rohan
 */
public class CSVMessage implements IMessage {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content. */
	private final Map<String, String> content;

	/**
	 * Instantiates a new CSV message.
	 *
	 * @param content the content
	 */
	public CSVMessage(final Map<String, String> content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		content.entrySet().stream().forEach(e-> sb.append(e.getKey()).append(":").append(e.getValue().trim()));
		return sb.toString();
	}
}
