package com.fileupload.file_upload_server.model;

import org.springframework.http.HttpStatus;

/**
 * The Class FileUploadResponse.
 * @author Rohan
 */
public class FileUploadResponse {

	/** The file name. */
	private String fileName;
	
	/** The file URL. */
	private String fileURL;
	
	/** The file type. */
	private String fileType;
	
	/** The status. */
	private HttpStatus status;
	
	/** The message. */
	private String message;

	/**
	 * Instantiates a new file upload response.
	 *
	 * @param fileName the file name
	 * @param fileURL the file URL
	 * @param fileType the file type
	 */
	public FileUploadResponse(final String fileName, final String fileURL, final String fileType) {
		this(fileName,fileURL,fileType,HttpStatus.OK,"Success");
	}
	
	/**
	 * Instantiates a new file upload response.
	 *
	 * @param fileName the file name
	 * @param fileURL the file URL
	 * @param fileType the file type
	 * @param status the status
	 * @param message the message
	 */
	public FileUploadResponse(final String fileName, final String fileURL, final String fileType,
			final HttpStatus status, final String message) {
		this.fileName = fileName;
		this.fileURL = fileURL;
		this.fileType = fileType;
		this.status = status;
		this.message = message;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Gets the file URL.
	 *
	 * @return the file URL
	 */
	public String getFileURL() {
		return fileURL;
	}

	/**
	 * Gets the file type.
	 *
	 * @return the file type
	 */
	public String getFileType() {
		return fileType;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}
}
