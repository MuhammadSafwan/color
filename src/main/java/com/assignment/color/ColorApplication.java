package com.assignment.color;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.assignment.color"})
public class ColorApplication {

	public static void main(String[] args)  {
		SpringApplication.run(ColorApplication.class, args);
		//System.out.println("Classpath: "+ ColorApplication.class.getClassLoader().getResource("sample-input.csv").getFile());


	}

}
