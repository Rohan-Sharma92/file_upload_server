package com.fileupload.file_upload_server.processors;

import com.fileupload.file_upload_server.model.FileType;

/**
 * A factory for creating IFileProcessor objects.
 * @author Rohan
 */
public interface IFileProcessorFactory {

	/**
	 * Creates a new IFileProcessor object.
	 *
	 * @param fileType the file type
	 * @return the i file processor
	 */
	public IFileProcessor createProcessor(final FileType fileType);
}
