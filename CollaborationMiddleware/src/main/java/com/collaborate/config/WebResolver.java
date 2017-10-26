package com.collaborate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan("com.collaborate")
public class WebResolver{
	
	@Bean
	public InternalResourceViewResolver getViewReolver()
	{
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/Users");
		internalResourceViewResolver.setSuffix(".html");
		System.out.println("Resolver Created..........");
		return internalResourceViewResolver;
		
	}
	
	@Bean(name="multiPartResolver")
    public  CommonsMultipartResolver getCommonsMultipartResolver()
    {
        CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(20971520);//20MB
        commonsMultipartResolver.setMaxInMemorySize(1048576);//1MB
        return commonsMultipartResolver;
    }


}
