package com.cc.downunder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DownUnderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DownUnderApplication.class, args);
	}

}

@Controller
class IndexController {
	@RequestMapping("/index")
	public String getHomePage() {
		return "index";
	}
}