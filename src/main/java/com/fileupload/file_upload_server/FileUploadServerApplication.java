package com.fileupload.file_upload_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.fileupload.file_upload_server.properties.ConfigProperties;

/**
 * The Class FileUploadServerApplication.
 */
@SpringBootApplication
@EnableConfigurationProperties(value = ConfigProperties.class)
public class FileUploadServerApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(FileUploadServerApplication.class, args);
	}

}
