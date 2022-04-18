package br.com.boaentrega.berouteplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class BeRoutePlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeRoutePlannerApplication.class, args);
	}

}
