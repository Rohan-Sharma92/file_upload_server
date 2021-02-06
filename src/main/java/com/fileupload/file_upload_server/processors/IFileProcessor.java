package com.fileupload.file_upload_server.processors;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fileupload.file_upload_server.model.IMessage;

/**
 * The Interface IFileProcessor.
 * @author Rohan
 */
public interface IFileProcessor {

	/**
	 * Process file content.
	 *
	 * @param fileContent the file content
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<IMessage> processFileContent(final InputStream fileContent) throws IOException;
}

