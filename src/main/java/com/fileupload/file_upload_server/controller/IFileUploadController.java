package com.fileupload.file_upload_server.controller;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fileupload.file_upload_server.model.FileUploadResponse;

public interface IFileUploadController {

	public FileUploadResponse uploadFile(MultipartFile file);

	public List<FileUploadResponse> uploadFiles(MultipartFile[] file);
}
