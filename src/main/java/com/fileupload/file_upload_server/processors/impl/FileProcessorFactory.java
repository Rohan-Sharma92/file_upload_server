package com.fileupload.file_upload_server.processors.impl;

import org.springframework.stereotype.Component;

import com.fileupload.file_upload_server.model.FileType;
import com.fileupload.file_upload_server.processors.IFileProcessor;
import com.fileupload.file_upload_server.processors.IFileProcessorFactory;

@Component
public class FileProcessorFactory implements IFileProcessorFactory {

	@Override
	public IFileProcessor createProcessor(FileType fileType) {
		switch(fileType) {
		case CSV:
			return new CSVFileProcessor();
		case PLAIN:
			return new TextFileProcessor();
		default:
			return null;
		}
	}

}
