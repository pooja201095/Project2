package com.collaborate.dao;

import java.util.List;







import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import org.springframework.transaction.annotation.Transactional;

import com.collaborate.model.Blog;


@Repository("BlogDAO")
public class BlogDAOImpl implements BlogDAO {
	
	
	@Autowired
	SessionFactory sessionFactory;
	
	public BlogDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	
	@Transactional
	@Override
	public boolean createBlog(Blog blog) {
		try
		{
			Session session= sessionFactory.openSession();
			
			session.beginTransaction();
			session.saveOrUpdate(blog);
			/*sessionFactory.getCurrentSession().saveOrUpdate(blog);*/
			
			System.out.println("Blog Created.......");
			session.getTransaction().commit();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception:"+e);
			return false;
		}
	}

	@Transactional
	@Override
	public Blog getBlog(int blogid) {
		try
		{
		Session session=sessionFactory.openSession();
		Blog blog=(Blog) session.get(Blog.class,blogid);
		System.out.println(blog.getBlogId());
		return blog;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception:"+e);
			return null;
		}
	}

	@Transactional
	@Override
	public List<Blog> getBlogs() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Blog");
		List<Blog> listblog= query.list();
		session.close();
		return listblog;
	}
	
	@Transactional
	@Override
	public List<Blog> getApprovedBlogs() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Blog where status='A'");
		List<Blog> listblog= query.list();
		session.close();
		return listblog;
	}

	@Transactional
	@Override
	public boolean approveBlog(Blog blog) {
		
		try{
			blog.setStatus("A");
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception:"+e);
			return false;
		}
		
	}

	@Transactional
	@Override
	public boolean editBlog(int blogid) {
		try{
		Session session=sessionFactory.openSession();
		Blog blog=(Blog) session.get(Blog.class,blogid);
		blog.setBlogName("Unittest");
		session.flush();
		session.close();
		return true;
		}
		catch(Exception e){
			System.out.println("Exception:"+e);
			return false;
		}
	}

	@Override
	public boolean deleteBlog(int blogid) {
		try
		{
		Session session=sessionFactory.openSession();
		Blog blog=(Blog) session.get(Blog.class,blogid);
		session.delete(blog);
		session.flush();
		session.close();
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception :"+e);
			return false;
		}
	}

}
