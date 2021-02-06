package com.fileupload.file_upload_server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="config")
public class ConfigProperties implements IConfigProperties {

	private String uploadDir;
	
	private String connectionURL;
	
	private String exchange;
	
	@Override
	public String getUploadDir() {
		return uploadDir;
	}

	@Override
	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	@Override
	public String getConnectionURL() {
		return connectionURL;
	}

	@Override
	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}

	@Override
	public String getExchange() {
		return exchange;
	}

	@Override
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

}
