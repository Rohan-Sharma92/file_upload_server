package com.fileupload.file_upload_server.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The Class GlobalExceptionHandler.
 * @author Rohan
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Handle the {@link FileUploadException}.
	 *
	 * @param ex the ex
	 * @param request the request
	 * @return {@link ResponseEntity} with status code 400
	 */
	@ExceptionHandler(FileUploadException.class)
	public ResponseEntity<Object> appExceptionHandler(FileUploadException ex, WebRequest request) {
		return handleExceptionInternal(ex, null, null, BAD_REQUEST, request);
	}
	
	/**
	 * App exception handler.
	 *
	 * @param ex the ex
	 * @param request the request
	 * @return the response entity
	 */
	@ExceptionHandler(ContentParsingException.class)
	public ResponseEntity<Object> appExceptionHandler(ContentParsingException ex, WebRequest request) {
		return handleExceptionInternal(ex, null, null, BAD_REQUEST, request);
	}

}