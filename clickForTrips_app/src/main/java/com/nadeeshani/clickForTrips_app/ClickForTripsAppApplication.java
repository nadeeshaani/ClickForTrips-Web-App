package com.nadeeshani.clickForTrips_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class ClickForTripsAppApplication {
	@RequestMapping("/")
	@ResponseBody
	String text(){
		return "Hello";
	}

	public static void main(String[] args) {
		SpringApplication.run(ClickForTripsAppApplication.class, args);
	}

}
