package com.fileupload.file_upload_server.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.file_upload_server.exceptions.FileUploadException;
import com.fileupload.file_upload_server.model.ErrorResponse;
import com.fileupload.file_upload_server.model.FileUploadResponse;
import com.fileupload.file_upload_server.services.upload.IUploadService;

/**
 * The Class FileUploadController.
 * 
 * @author Rohan
 */
@RestController
public class FileUploadController {

	/** The upload service. */
	private final IUploadService uploadService;

	/**
	 * Instantiates a new file upload controller.
	 *
	 * @param uploadService           the upload service
	 * @param contentProcessorService the content processor service
	 */
	@Autowired
	public FileUploadController(@Qualifier("Upload") final IUploadService uploadService) {
		this.uploadService = uploadService;
	}

	/**
	 * Generate error response.
	 *
	 * @param file the file
	 * @param e    the e
	 * @return the error response
	 */
	private ErrorResponse generateErrorResponse(MultipartFile file, FileUploadException e) {
		return new ErrorResponse(file.getOriginalFilename(), HttpStatus.BAD_REQUEST, e.getMessage());
	}

	/**
	 * Upload.
	 *
	 * @param file the file
	 * @return the file upload response
	 * @throws FileUploadException the file upload exception
	 */
	private FileUploadResponse upload(MultipartFile file) throws FileUploadException {
		String fileURL = uploadService.upload(file);
		FileUploadResponse response = new FileUploadResponse(file.getOriginalFilename(), fileURL,
				file.getContentType());
		return response;
	}

	/**
	 * Upload files.
	 *
	 * @param files the files
	 * @return the list
	 */
	@PostMapping(path = "/uploads")
	public List<FileUploadResponse> uploadFiles(@RequestParam("file") MultipartFile[] files) {
		return Arrays.stream(files).map(file -> {
			try {
				return upload(file);
			} catch (FileUploadException e) {
				return generateErrorResponse(file, e);
			}
		}).collect(Collectors.toList());
	}

}
