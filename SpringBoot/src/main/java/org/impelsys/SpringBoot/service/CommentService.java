package org.impelsys.SpringBoot.service;

import java.util.List;

import org.hibernate.Session;
import org.impelsys.SpringBoot.dao.impl.CommentDaoImpl;
import org.impelsys.SpringBoot.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@Autowired
	CommentDaoImpl commentDao;
	
	public List<Comment> getAllComments()
	{
		return commentDao.listComment();
	}
	
	public Comment getComment(int id)
	{
		return commentDao.getComment(id);
	}
	

}
