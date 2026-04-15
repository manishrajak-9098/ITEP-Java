package com.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.spring.controller")
@PropertySource("classpath.application.properties")
public class AppConfig implements WebMvcConfigurer {
	
	@Value("${upload.filepath}")
	String filepath;
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/views/");
		irvr.setSuffix(".jsp");
		return irvr;
		
		
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registery) {
	 registery.addResourceHandler("/css/**").addResourceLocations("/css");
	 
	 registery.addResourceHandler("/images**").addResourceLocations("/images");
	 
	 registery.addResourceHandler("/js**").addResourceLocations("/js");
	 
	 registery.addResourceHandler("/uploads/**").addResourceLocations("/file:///"+filepath);
	 
	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	
}
