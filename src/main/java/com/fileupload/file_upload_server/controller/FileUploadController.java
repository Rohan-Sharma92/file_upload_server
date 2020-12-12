package com.fileupload.file_upload_server.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.file_upload_server.exceptions.FileUploadException;
import com.fileupload.file_upload_server.model.ErrorResponse;
import com.fileupload.file_upload_server.model.FileUploadResponse;
import com.fileupload.file_upload_server.services.IUploadService;

@RestController
public class FileUploadController implements IFileUploadController {

	private final IUploadService uploadService;

	@Autowired
	public FileUploadController(final IUploadService uploadService) {
		this.uploadService = uploadService;
	}

	@PostMapping(path = "/upload")
	@Override
	public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
		FileUploadResponse response;
		try {
			response = upload(file);
		} catch (FileUploadException e) {
			response = generateErrorResponse(file, e);
		}
		return response;
	}

	private ErrorResponse generateErrorResponse(MultipartFile file, FileUploadException e) {
		return new ErrorResponse(file.getOriginalFilename(),HttpStatus.BAD_REQUEST ,e.getMessage());
	}

	private FileUploadResponse upload(MultipartFile file) throws FileUploadException {
		String fileURL = uploadService.upload(file);
		FileUploadResponse response = new FileUploadResponse(file.getOriginalFilename(), fileURL,
				file.getContentType());
		return response;
	}

	@PostMapping(path = "/uploadMultiple")
	@Override
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
