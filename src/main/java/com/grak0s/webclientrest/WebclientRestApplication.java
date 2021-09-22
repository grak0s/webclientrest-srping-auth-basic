package com.grak0s.webclientrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebclientRestApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(WebclientRestApplication.class, args);

        LoginClient loginClient = context.getBean(LoginClient.class);
        System.out.println(">> token = " + loginClient.getToken().block());

    }






}
