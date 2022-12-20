package com.seongmin.travel.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seongmin.travel.interceptor.JwtInterceptor;
import com.seongmin.travel.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	private JwtInterceptor jwtInterceptor;

	@Autowired
	public WebConfig(JwtInterceptor jwtInterceptor){
		this.jwtInterceptor = jwtInterceptor;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:8080", "http://localhost:8081", "http://localhost:9999")
				.allowedMethods("GET","POST","PUT","DELETE");
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
				.simpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.build();
		converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns(
						"/main-api/**",
						"/video-api/list",
						"/video-api/video/search/**",
						"/review-api/list/**",
						"/user-api/login",
						"/user-api/signin",
						"/*/detail/**",
						"/follow-api/**",
						"/swagger-resources/**",
						"/swagger-ui/**",
						"/v3/api-docs");
	}

}
