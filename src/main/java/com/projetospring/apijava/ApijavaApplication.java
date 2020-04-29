package com.projetospring.apijava;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class ApijavaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApijavaApplication.class, args);
		System.out.println("\nWelcome " +
				"\n  _____" +
				"\n  |0V0|" +
				"\n /| O |\\" +
				"\n  |_|_|");
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
