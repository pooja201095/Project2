package com.collaborate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaborate.model.BlogComment;

@Repository("BlogCommentDAO")
public class BlogCommentDAOImpl implements BlogCommentDAO {
	
	SessionFactory sessionFactory;
	public BlogCommentDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	@Transactional
	@Override
	public boolean createBlogComment(BlogComment blogComment) {
		try{
		sessionFactory.getCurrentSession().saveOrUpdate(blogComment);
		/*session.beginTransaction();
		session.saveOrUpdate(blogComment);
		/*session.close();
		session.getTransaction().commit();*/
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
		try{
		Session session= sessionFactory.getCurrentSession();
		BlogComment blogComment= (BlogComment) session.get(BlogComment.class,id);
		System.out.println(id);
		return blogComment;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BlogComment> getBlogComments() {
		Session session= sessionFactory.openSession();
		Query query= session.createQuery("from BlogComment");
		List<BlogComment> listBlogComment=query.list();
		return listBlogComment;
	}

	@Override
	public boolean editBlogComment(int id) {
		try{
			Session session= sessionFactory.openSession();
			BlogComment blogComment= (BlogComment) session.get(BlogComment.class,id);
			blogComment.setComments("Correct comment");
			session.flush();
			session.close();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteBlogComment(int id) {
		try{
		Session session= sessionFactory.openSession();
		BlogComment blogComment= (BlogComment) session.get(BlogComment.class,id);
		session.delete(blogComment);
		session.flush();
		session.close();
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
