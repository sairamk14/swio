package com.stu.stuCourse;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.twilio.Twilio;

@EnableJpaRepositories(basePackages="com.dao")
@EntityScan(basePackages="com.model")
@SpringBootApplication(scanBasePackages="com")


public class StuCourseApplication {

	 public static final String ACCOUNT_SID = "add your twillio sid";
     public static final String AUTH_TOKEN = "add your twillio token";
 
	public static void main(String[] args) {
		SpringApplication.run(StuCourseApplication.class, args);
	}
	
	@PostConstruct
	public void initTwilio(){
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}

}
