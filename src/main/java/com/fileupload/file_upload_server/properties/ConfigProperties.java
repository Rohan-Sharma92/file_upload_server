package com.fileupload.file_upload_server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="config")
public class ConfigProperties implements IConfigProperties {

	private String uploadDir;

	@Override
	public String getUploadDir() {
		return uploadDir;
	}

	@Override
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

}
