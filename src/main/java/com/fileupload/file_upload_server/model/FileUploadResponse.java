package com.fileupload.file_upload_server.model;

import org.springframework.http.HttpStatus;

public class FileUploadResponse {

	private String fileName;
	private String fileURL;
	private String fileType;
	private HttpStatus status;
	private String message;

	public FileUploadResponse(final String fileName, final String fileURL, final String fileType) {
		this(fileName,fileURL,fileType,HttpStatus.OK,"Success");
	}
	
	public FileUploadResponse(final String fileName, final String fileURL, final String fileType,
			final HttpStatus status, final String message) {
		this.fileName = fileName;
		this.fileURL = fileURL;
		this.fileType = fileType;
		this.status = status;
		this.message = message;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileURL() {
		return fileURL;
	}

	public String getFileType() {
		return fileType;
	}
	
	public String getMessage() {
		return message;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
}
