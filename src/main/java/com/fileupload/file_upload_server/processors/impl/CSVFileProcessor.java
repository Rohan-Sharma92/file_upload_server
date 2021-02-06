package com.fileupload.file_upload_server.processors.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import com.fileupload.file_upload_server.model.CSVMessage;
import com.fileupload.file_upload_server.model.IMessage;
import com.fileupload.file_upload_server.processors.IFileProcessor;

public class CSVFileProcessor implements IFileProcessor {

	@Override
	public List<IMessage> processFileContent(final InputStream fileContent) throws IOException {
		CSVParser parser = new CSVParser(new InputStreamReader(fileContent),CSVFormat.EXCEL.withFirstRecordAsHeader().withSkipHeaderRecord());
		List<IMessage> records = parser.getRecords().stream().map(line -> new CSVMessage(line.toMap())).collect(Collectors.toList());
		parser.close();
		return records;
	}

}
