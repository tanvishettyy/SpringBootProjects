package org.impelsys.SpringBoot.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.impelsys.SpringBoot.controller.CommentController;
import org.impelsys.SpringBoot.controller.HomeController;
import org.impelsys.SpringBoot.resources.MessageResource;
import org.impelsys.SpringBoot.resources.UserResource;
import org.springframework.context.annotation.Configuration;


@Configuration
//@ApplicationPath("/rest")
public class MyConfig extends ResourceConfig{
	
	public MyConfig()
	{
		register(MessageResource.class);
		register(UserResource.class);
		
	}

}
