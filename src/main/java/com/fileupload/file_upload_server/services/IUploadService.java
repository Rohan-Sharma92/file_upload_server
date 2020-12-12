package com.fileupload.file_upload_server.services;

import org.springframework.web.multipart.MultipartFile;

import com.fileupload.file_upload_server.exceptions.FileUploadException;

public interface IUploadService {

	public String upload(MultipartFile file) throws FileUploadException;
}
