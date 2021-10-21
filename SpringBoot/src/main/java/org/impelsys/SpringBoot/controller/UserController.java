package org.impelsys.SpringBoot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.impelsys.SpringBoot.exception.CommentNotFoundException;
import org.impelsys.SpringBoot.exception.UserNotFoundException;
import org.impelsys.SpringBoot.model.Comment;
import org.impelsys.SpringBoot.model.User;
import org.impelsys.SpringBoot.service.CommentService;
import org.impelsys.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*@ResponseBody +
@Controller*/

@RequestMapping("/users")
@RestController   //spring web service controller cant return modelandview  but can return response body
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value="/list", produces="application/json")
	public ResponseEntity<List<User>> getUsers()
	{
		List<User> usersList = userService.getAllUsers();
		ResponseEntity<List<User>> responseEntity = new ResponseEntity<List<User>>(usersList, HttpStatus.OK);
		return responseEntity;
				
	}
	
	@GetMapping(value="/user/{id}", produces="application/json")
	public ResponseEntity<User> getUser(@PathVariable int id) 
	{
		User user = userService.getUser(id);
		System.out.println("In getUser");
		System.out.println(user);
		
		ResponseEntity<User> responseEntity = new ResponseEntity<User>(user,HttpStatus.OK);
		return responseEntity;
	}
	
	@PostMapping(value="/user", consumes=MediaType.APPLICATION_JSON , produces=MediaType.APPLICATION_JSON)
	
	public User saveUser(@RequestBody User user)
	{
		System.out.println(user);
		return userService.saveUser(user);
	}
	//handle exception thrown by this controller(local to this controller)
	/*
	 * @ExceptionHandler(UserNotFoundException.class) public void
	 * handlerForUserNotFound(HttpServletRequest req, HttpServletResponse rep) {
	 * System.out.println("Will look into this"); }
	 */
}
