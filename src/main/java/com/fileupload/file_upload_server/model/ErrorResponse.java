package com.fileupload.file_upload_server.model;

import org.springframework.http.HttpStatus;

public class ErrorResponse extends FileUploadResponse {

	public ErrorResponse(String fileName,final HttpStatus status,final String message) {
		super(fileName, "","",status,message);
	}

}
