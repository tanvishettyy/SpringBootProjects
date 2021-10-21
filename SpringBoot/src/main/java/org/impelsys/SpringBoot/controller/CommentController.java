package org.impelsys.SpringBoot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.impelsys.SpringBoot.exception.CommentNotFoundException;
import org.impelsys.SpringBoot.model.Comment;
import org.impelsys.SpringBoot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*@ResponseBody +
@Controller*/

@RequestMapping("/comments")
@RestController   //spring web service controller cant return modelandview  but can return response body
public class CommentController {

	@Autowired
	CommentService commentService;
	
	
	  @GetMapping(value="/list", produces="application/json") 
	  public List<Comment> getComments()
	  { 
		  System.out.println("In /comments/list");
		  List<Comment> commentsList = commentService.getAllComments();
		  return commentsList;
	  
	  }
	 
	
	@GetMapping(value="/list1", produces="application/json")
	public ResponseEntity<List<Comment>> getComments1()
	{
		List<Comment> commentsList = commentService.getAllComments();
		ResponseEntity<List<Comment>> responseEntity = new ResponseEntity<List<Comment>>(commentsList, HttpStatus.OK);
		return responseEntity;
				
	}
	
	@GetMapping(value="/comment/{id}", produces="application/json")
	public ResponseEntity<Comment> getComment(@PathVariable int id)
	{
		Comment comment = commentService.getComment(id);
		System.out.println("In getComment");
		System.out.println(comment);
		if(comment==null)
			throw new CommentNotFoundException(id);
		ResponseEntity<Comment> responseEntity = new ResponseEntity<Comment>(comment,HttpStatus.OK);
		return responseEntity;
	}
	
	/*
	 * @ExceptionHandler(CommentNotFoundException.class) public void
	 * handlerForCommentNotFound(HttpServletRequest req, HttpServletResponse rep) {
	 * System.out.println("Will look into this"); }
	 */
}
