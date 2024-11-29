package com.xapp.member.authentication;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
//@SpringBootApplication(exclude = {ReactiveUserDetailsServiceAutoConfiguration.class})
@SpringBootApplication
@EnableJpaRepositories
public class XAppMemberAuthentication {

	public static void main(String[] args) {
		SpringApplication.run(XAppMemberAuthentication.class, args);
	}

}
