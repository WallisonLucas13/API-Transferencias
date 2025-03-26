package com.example.transfer.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API de Transferências", version = "1.0", description = "API de gerenciamento e transferências financeiras"))
public class TransferApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransferApiApplication.class, args);
	}

}
