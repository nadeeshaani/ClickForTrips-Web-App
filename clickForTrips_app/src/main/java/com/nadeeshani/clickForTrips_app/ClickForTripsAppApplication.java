package com.nadeeshani.clickForTrips_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@SpringBootApplication
public class ClickForTripsAppApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(ClickForTripsAppApplication.class);
	}

	public static void main(String[] args)
	{
		SpringApplication.run(ClickForTripsAppApplication.class, args);
	}

}

