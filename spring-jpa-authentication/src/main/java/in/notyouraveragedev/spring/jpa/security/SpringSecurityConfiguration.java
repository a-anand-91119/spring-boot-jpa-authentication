package in.notyouraveragedev.spring.jpa.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		// In memory Authentication
		auth.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER")
				.and()
				.withUser("admin") .password("admin").roles("ADMIN");
		 
		// Using Datasource with Default Schema
		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
				.withUser("user").password("password").roles("USER")
				.and()
				.withUser("admin").password("admin").roles("ADMIN");
		
		
		// Datasource with schema and data from sql files
		auth.jdbcAuthentication().dataSource(dataSource);
		
		// the authority of users must begin with "ROLE_"  Example: ROLE_USER, ROLE_ADMIN
		
		// Datasources With Custom Schema
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username, password, enabled from users where username = ?")
			.authoritiesByUsernameQuery("select username, authority from authorities where username = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Authorisation / granted authority
		http.authorizeRequests()
				.antMatchers("/admin").hasRole("ADMIN")
				.antMatchers("/user").hasAnyRole("USER", "ADMIN")
				.antMatchers("/").permitAll()
				.and()
				.formLogin();
	}

}
