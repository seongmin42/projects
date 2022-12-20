package com.seongmin.travel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ServletComponentScan
@SpringBootApplication
@MapperScan(basePackages="com.seongmin.travel.model.dao")
@EnableSwagger2
public class TravelApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

}
