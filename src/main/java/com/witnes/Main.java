package com.witnes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {
    public static void main (String[] args){
        //System.out.println("Hellow world");
        SpringApplication.run(Main.class, args);

    }

    @GetMapping("/greet")
    public String greeting(){
        return "Hello witnes this is my spring boot";
    }
}
