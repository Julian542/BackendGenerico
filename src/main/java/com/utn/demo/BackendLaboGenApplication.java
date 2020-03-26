package com.utn.demo;

import java.io.File;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendLaboGenApplication {
	
	private static File home;
	
	public static File getHome() {
		ApplicationHome home = new ApplicationHome(BackendLaboGenApplication.class);
		return home.getSource();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BackendLaboGenApplication.class, args);
	}

}
