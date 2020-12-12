package com.fileupload.file_upload_server.model;

import java.util.HashMap;
import java.util.Map;

public enum FileType {

	PDF("PDF"),DOCX("DOCX"),MP4("MP4");
	
	private String displayName;
	
	private final Map<String,FileType> fileTypeMap = new HashMap<>();
	private FileType(String name) {
		this.displayName = name;
		fileTypeMap.put(displayName, this);
	}
	
	public String getDisplayString() {
		return this.displayName;
	}
	
	public FileType valueOfDisplayString(String name) {
		return fileTypeMap.get(name);
	}
}
