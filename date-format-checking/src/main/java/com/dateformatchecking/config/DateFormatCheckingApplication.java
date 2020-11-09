package com.dateformatchecking.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages = {"com.dateformatchecking"})
@Controller
public class DateFormatCheckingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DateFormatCheckingApplication.class, args);
	}

	@GetMapping("/index")
	public String getS(){
		return "index";
	}
}
