package com.fileupload.file_upload_server.services.upload;

import org.springframework.web.multipart.MultipartFile;

import com.fileupload.file_upload_server.exceptions.FileUploadException;

/**
 * The Interface IUploadService.
 * @author Rohan
 */
public interface IUploadService {

	/**
	 * Upload.
	 *
	 * @param file the file
	 * @return the string
	 * @throws FileUploadException the file upload exception
	 */
	public String upload(MultipartFile file) throws FileUploadException;
}
