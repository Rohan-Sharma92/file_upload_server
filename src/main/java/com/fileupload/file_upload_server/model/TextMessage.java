package com.fileupload.file_upload_server.model;

public class TextMessage implements IMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8805685430527141588L;
	private final String message;

	public TextMessage(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return this.message;
	}
}
