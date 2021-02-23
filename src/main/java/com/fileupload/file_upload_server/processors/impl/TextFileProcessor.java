package com.fileupload.file_upload_server.processors.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.fileupload.file_upload_server.model.IMessage;
import com.fileupload.file_upload_server.model.TextMessage;
import com.fileupload.file_upload_server.processors.IFileProcessor;

public class TextFileProcessor implements IFileProcessor{

	@Override
	public List<IMessage> processFileContent(InputStream fileContent) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileContent));
		List<IMessage> messages = new ArrayList<>();
		String line=null;
		while((line=bufferedReader.readLine())!=null) {
			messages.add(new TextMessage(line));
		}
		return messages;
	}

}
