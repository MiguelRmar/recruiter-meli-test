package com.meli.recruiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RecruiterApplication extends SpringBootServletInitializer  {

    public static void main(String[] args) {
        SpringApplication.run(RecruiterApplication.class, args);
    }

}
