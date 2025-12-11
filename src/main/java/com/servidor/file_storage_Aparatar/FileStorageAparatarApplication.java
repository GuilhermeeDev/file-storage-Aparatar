package com.servidor.file_storage_Aparatar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileStorageAparatarApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileStorageAparatarApplication.class, args);
		System.out.printf(" ");
		System.out.printf("Acesse em: http://localhost:8080/");
	}
}
