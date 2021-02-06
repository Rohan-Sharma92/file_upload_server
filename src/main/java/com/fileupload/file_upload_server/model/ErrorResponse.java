package com.fileupload.file_upload_server.model;

import org.springframework.http.HttpStatus;

/**
 * The Class ErrorResponse.
 */
public class ErrorResponse extends FileUploadResponse {

	/**
	 * Instantiates a new error response.
	 *
	 * @param fileName the file name
	 * @param status the status
	 * @param message the message
	 */
	public ErrorResponse(String fileName,final HttpStatus status,final String message) {
		super(fileName, "","",status,message);
	}

}
