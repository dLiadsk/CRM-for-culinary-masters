package com.example.crm;

import com.example.crm.configurations.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication()
public class CrmForCulinaryMastersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmForCulinaryMastersApplication.class, args);
	}

}
