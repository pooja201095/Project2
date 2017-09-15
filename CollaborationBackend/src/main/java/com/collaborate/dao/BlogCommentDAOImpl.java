package com.collaborate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.collaborate.model.BlogComment;

@Repository("BlogCommentDAO")
public class BlogCommentDAOImpl implements BlogCommentDAO {
	
	SessionFactory sessionFactory;
	public BlogCommentDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	@Override
	public boolean createBlogComment(BlogComment blogComment) {
		try{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.saveOrUpdate(blogComment);
		session.getTransaction().commit();
		System.out.println("Blog comment successfully created");
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public BlogComment getBlogComment(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogComment> getBlogComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean editBlogComment(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBlogComment(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
