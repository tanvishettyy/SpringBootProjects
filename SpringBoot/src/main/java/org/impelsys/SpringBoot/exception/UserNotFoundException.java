package org.impelsys.SpringBoot.exception;

public class UserNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 public UserNotFoundException(int id)
	 {
		 super(String.format("User with %d not found", id));
	 }
	
	

}
