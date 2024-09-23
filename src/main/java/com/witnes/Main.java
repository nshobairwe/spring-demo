package com.witnes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@Configuration
@RestController
public class Main {
    public static void main (String[] args){
        //System.out.println("Hellow world");
        SpringApplication.run(Main.class, args);

    }

    @GetMapping("/greet")
    public GreetingData greeting(){
        //return "Hello witnes this is my spring boot";
        GreetingData response = new GreetingData(
                "Hellow world",
                List.of("java","javascript","python"),
                new Person(
                        "Innocent kipilipili",
                        26,
                        3000)
                );
        return response;
    }

    record GreetingData(
            String greet,
            List<String> programmingLanguages,
            Person person){
    }

    record Person(
            String person,int age, int savings
    ){}
}
