package com.fileupload.file_upload_server.services.upload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.file_upload_server.exceptions.FileUploadException;
import com.fileupload.file_upload_server.properties.IConfigProperties;

@Service
@Component("Upload")
public class UploadService implements IUploadService {

	private final Path uploadPath;

	@Autowired
	public UploadService(final IConfigProperties configProperties) throws FileUploadException {
		this.uploadPath = Paths.get(configProperties.getUploadDir()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(uploadPath);
		} catch (IOException e) {
			throw new FileUploadException(e.getMessage());
		}
	}

	@Override
	public String upload(MultipartFile file) throws FileUploadException{
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			throw new FileUploadException("Could not resolve path due to incorrect symbols");
		}
		Path resolvedPath = uploadPath.resolve(fileName);
		try {
			Files.copy(file.getInputStream(), resolvedPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new FileUploadException(e.getMessage());
		}
		return fileName;
	}

}
