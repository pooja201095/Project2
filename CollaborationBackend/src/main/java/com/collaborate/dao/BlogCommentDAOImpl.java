package com.collaborate.dao;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaborate.model.BlogComment;

@Repository("BlogCommentDAO")
@Transactional
public class BlogCommentDAOImpl implements BlogCommentDAO {
	
	SessionFactory sessionFactory;
	public BlogCommentDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	public void addBlogComment(BlogComment blogComment) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(blogComment);
		System.out.println("BlogComment created succesfully.!");
		session.getTransaction().commit();
		
	}
	public List<BlogComment> getBlogComments(int blogId) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("From BlogComment where blog.id="+blogId);
		System.out.println("Getting blogcomment for blogid: "+blogId+" Successfully...!");
		return query.list();
	}

	
}
