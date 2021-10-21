package org.impelsys.SpringBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component          //bean is not aspect can't be applied
@Aspect
public class UserServiceAspect {
	
	@Pointcut("execution(* org.impelsys.SpringBoot.dao.impl.UserDaoImpl.*(..))") 
	public void userDaoMapping()
	{
		
	}

	//Advice -> what is the functionality to be done
	//pointcut -> the condition to search for 
	//JoinPoint -> it is a point during the execution of the program
	//UserDaoImpl *-> for any return type of userdaoimpl  (..) -> any number of arguments
	@Before("userDaoMapping()")  //pointcut 
	public void beforeAdvice(JoinPoint joinPoint)                                //on tat condition this will be executed
	{
		System.out.println("Before method : "+ joinPoint.getSignature());
	}
	
	@After("userDaoMapping()")
	public void afterAdvice(JoinPoint joinPoint)
	{
		System.out.println("After method : "+ joinPoint.getSignature());
	}
	
}
