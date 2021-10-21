package org.impelsys.SpringBoot.dao.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import org.impelsys.SpringBoot.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Repository
public class CommentDaoImpl {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public List<Comment> listComment()
	{
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Comment", Comment.class);
		List<Comment> commentsList = query.list();
		session.close();
		return commentsList;
	}
	
	public Comment getComment(int id)
	{
		Session session = sessionFactory.openSession();
		Comment comment = session.get(Comment.class, new Integer(id));
		session.close();
		return comment;
	}
	
	

}
