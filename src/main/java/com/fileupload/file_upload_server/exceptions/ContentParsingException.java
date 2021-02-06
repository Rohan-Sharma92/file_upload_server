package com.fileupload.file_upload_server.exceptions;

/**
 * The Class FileUploadException.
 */
public class ContentParsingException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new file upload exception.
	 *
	 * @param msg the msg
	 */
	public ContentParsingException(String msg) {
		super(msg);
	}

}
