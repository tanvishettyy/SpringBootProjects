package org.impelsys.SpringBoot.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.impelsys.SpringBoot.exception.CommentNotFoundException;
import org.impelsys.SpringBoot.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice  //Specialization of component annotation
					//Intercepter for exceptions occurring in all controllers
					//globally shared
@ResponseBody       
public class ControllerExceptionHandler {
	
	@ExceptionHandler(CommentNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)       //
	public String handlerForCommentNotFound(CommentNotFoundException ce)
	{
		System.out.println();
		return "Will look into this!!"+ ce.getMessage();
	}

	//both are same
	
	/*  @ExceptionHandler(UserNotFoundException.class) 
	  @ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "Unable to find User")   
	  public String  handlerForUserNotFound(UserNotFoundException ue) 
	  {
		  //String msg = "Will look into this!!"+ ue.getMessage();
		  System.out.println("In exceptionHandler");
		  return "Will look into this!!"+ ue.getMessage();
	}
	*/
	 @ExceptionHandler(Exception.class)
	 @ResponseStatus(value=HttpStatus.NOT_FOUND)
	 public String handleAllException(Exception e)
	 {
		 System.out.println("in exception");
		 return e.getMessage();
	 }
}
