package com.xapp.member.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
public class XAppMemberAuthentication {

	public static void main(String[] args) {
		SpringApplication.run(XAppMemberAuthentication.class, args);
	}

}
