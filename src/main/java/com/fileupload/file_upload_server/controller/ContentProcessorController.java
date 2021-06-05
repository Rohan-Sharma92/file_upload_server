package com.fileupload.file_upload_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.file_upload_server.exceptions.ContentParsingException;
import com.fileupload.file_upload_server.model.ErrorResponse;
import com.fileupload.file_upload_server.model.FileUploadResponse;
import com.fileupload.file_upload_server.services.content.IService;

/**
 * The Class ContentProcessorController.
 */
@RestController
public class ContentProcessorController {

	/** The content processor service. */
	private IService contentProcessorService;

	/**
	 * Instantiates a new content processor controller.
	 *
	 * @param contentProcessorService the content processor service
	 */
	@Autowired
	public ContentProcessorController(@Qualifier("Processor") final IService contentProcessorService) {
		this.contentProcessorService = contentProcessorService;
	}

	/**
	 * Process file.
	 *
	 * @param file the file
	 * @return the file upload response
	 */
	@PostMapping(path = "/process")
	public FileUploadResponse processFile(@RequestParam("file") MultipartFile file, @RequestParam("filetype") String fileType) {
		FileUploadResponse response;
		try {
			response = processContent(file,fileType);
		} catch (ContentParsingException e) {
			response = generateErrorResponse(file, e);
		}
		return response;
	}

	/**
	 * Process content.
	 *
	 * @param file the file
	 * @param fileType 
	 * @return the file upload response
	 * @throws ContentParsingException the content parsing exception
	 */
	private FileUploadResponse processContent(MultipartFile file, String fileType) throws ContentParsingException {
		String fileURL = contentProcessorService.process(file,fileType);
		FileUploadResponse response = new FileUploadResponse(file.getOriginalFilename(), fileURL,
				file.getContentType());
		return response;
	}

	/**
	 * Generate error response.
	 *
	 * @param file the file
	 * @param e    the e
	 * @return the error response
	 */
	private ErrorResponse generateErrorResponse(MultipartFile file, ContentParsingException e) {
		return new ErrorResponse(file.getOriginalFilename(), HttpStatus.BAD_REQUEST, e.getMessage());
	}
}
