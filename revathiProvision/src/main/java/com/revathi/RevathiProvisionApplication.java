 package com.revathi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
public class RevathiProvisionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevathiProvisionApplication.class, args);
	}

}
