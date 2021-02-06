package com.fileupload.file_upload_server.services.content;

import org.springframework.web.multipart.MultipartFile;

import com.fileupload.file_upload_server.exceptions.ContentParsingException;

/**
 * The Interface IService.
 * @author Rohan
 */
public interface IService {

	/**
	 * Process.
	 *
	 * @param file the file
	 * @return the string
	 * @throws ContentParsingException the content parsing exception
	 */
	public String process(MultipartFile file) throws ContentParsingException;
}
