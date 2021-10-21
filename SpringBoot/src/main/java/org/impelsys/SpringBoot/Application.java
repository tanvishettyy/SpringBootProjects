package org.impelsys.SpringBoot;

import org.impelsys.SpringBoot.controller.CommentController;
import org.impelsys.SpringBoot.dao.impl.CommentDaoImpl;
import org.impelsys.SpringBoot.exception.handler.ControllerExceptionHandler;
import org.impelsys.SpringBoot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration(exclude= {
		SecurityAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class
		//SecurityFilterAutoConfiguration.class
})
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(basePackageClasses= {CommentController.class,CommentService.class,CommentDaoImpl.class,ControllerExceptionHandler.class})
public class Application {
	
	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(Application.class, args);
		System.out.println("Hello to the world of SpringBoot");

	}

}
//@Autowired
//private static Environment environment;

//System.setProperty("spring.profiles.active","dev");
//ApplicationContext context = new SpringApplicationBuilder(Application.class).profiles("dev").run(args);  //using spring context
//if(environment==null) environment=(Environment)
//context.getBean("environment"); 
//String[] profilesList = environment.getActiveProfiles(); 
//for(String s:profilesList)
//	  System.out.println(s);
//
//
//CommentController controller = (CommentController) context.getBean("commentController");  //to chk if its picking up the bean
//if(controller==null)
//	System.out.println("Comment controller not created");
////SpringApplication.run(Application.class, args);
//
