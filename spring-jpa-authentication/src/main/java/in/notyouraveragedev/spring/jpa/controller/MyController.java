package in.notyouraveragedev.spring.jpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@GetMapping("/")
	String guest() {
		return "<h1>Welcome Guest</h1>";
	}

	@GetMapping("/user")
	String user() {
		return "<h1>Welcome User</h1>";

	}

	@GetMapping("/admin")
	String admin() {
		return "<h1>Welcome Admin</h1>";

	}
}
