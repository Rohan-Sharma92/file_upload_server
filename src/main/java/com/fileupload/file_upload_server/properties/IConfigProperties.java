package com.fileupload.file_upload_server.properties;

public interface IConfigProperties {

	public String getUploadDir();
	
	public void setUploadDir(String uploadDir);

	String getConnectionURL();

	void setConnectionURL(String connectionURL);

	void setExchange(String exchange);

	String getExchange();
}
