package in.notyouraveragedev.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringJpaAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaAuthenticationApplication.class, args);
	}
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		// this should not be used ideally. which is why it is marked as deprecated
		// for testing purposes, this would do
		return NoOpPasswordEncoder.getInstance();
	}
	
}
