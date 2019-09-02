package com.dgpalife.resourcemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringbootResourcemanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootResourcemanagementApplication.class, args);
	}

}
