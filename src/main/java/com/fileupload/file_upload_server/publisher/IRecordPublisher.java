package com.fileupload.file_upload_server.publisher;

public interface IRecordPublisher<T> {

	public void publish(T record);
	
	default void meth() {
		System.out.println("1");
	}
}
