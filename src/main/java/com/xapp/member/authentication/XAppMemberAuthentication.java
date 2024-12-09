package com.xapp.member.authentication;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

//@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
//@SpringBootApplication(exclude = {ReactiveUserDetailsServiceAutoConfiguration.class})
@ComponentScan("com.xapp.member.authentication")
@SpringBootApplication
@EnableJpaRepositories
@EnableWebFluxSecurity
public class XAppMemberAuthentication {

	public static void main(String[] args) {
		SpringApplication.run(XAppMemberAuthentication.class, args);
	}

}
