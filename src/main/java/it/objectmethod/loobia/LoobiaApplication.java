package it.objectmethod.loobia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class LoobiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoobiaApplication.class, args);
	}

}
