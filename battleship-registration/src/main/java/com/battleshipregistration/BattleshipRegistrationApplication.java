package com.battleshipregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class BattleshipRegistrationApplication extends SpringBootServletInitializer {
	
	private static Class<BattleshipRegistrationApplication> applicationClass = BattleshipRegistrationApplication.class;

	public static void main(String[] args) {
		SpringApplication.run(BattleshipRegistrationApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

}
