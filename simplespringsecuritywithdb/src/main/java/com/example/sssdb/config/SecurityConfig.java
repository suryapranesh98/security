package com.example.sssdb.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	DataSource datasource()
	{
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.setName("dashboard")
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
				.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	JdbcUserDetailsManager users(DataSource datasource,PasswordEncoder passwordEncoder)
	{
		UserDetails admin=User.builder().username("admin")
				.password(passwordEncoder.encode("secretpass@1"))
				.roles("ADMIN").build();
		JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(datasource);
		jdbcUserDetailsManager.createUser(admin);
		return jdbcUserDetailsManager;
	}
	
	
	/*@Bean
	InMemoryUserDetailsManager users()
	{
		return new InMemoryUserDetailsManager(
				User.withUsername("surya")
				.password("{noop}pass")
				.roles("ADMIN")
				.build());
	}*/
	
	
	
	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http.csrf(csrf->csrf.ignoringAntMatchers("/h2-console/**"))
				.authorizeRequests(auth->auth.antMatchers("/h2-console/**").permitAll()
						.anyRequest().authenticated())
				.headers(headers->headers.frameOptions().sameOrigin())
				.formLogin(Customizer.withDefaults())
				.build();
	}

}
