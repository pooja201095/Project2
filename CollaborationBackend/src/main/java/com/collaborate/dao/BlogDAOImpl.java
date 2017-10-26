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
@Transactional
public class BlogDAOImpl implements BlogDAO {
	
	
	@Autowired
	SessionFactory sessionFactory;
	
	public BlogDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	
	public void addBlog(Blog blog) {
		try
		{
			Session session= sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(blog);
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			
			System.out.println("Blog Created.......");
			session.getTransaction().commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception:"+e);
		}
	}


	public List<Blog> getBlogs(int approved) {
		Session session=sessionFactory.getCurrentSession();
		String querystring="";
		if(approved==1){
			querystring="From Blog where approved="+approved;
		}
		else{
			querystring="From Blog where rejectionReason is null and approved="+approved;
		}
		Query query=session.createQuery(querystring);
		System.out.println("Got list of all blogs successfully....!!!");
		return query.list();
	}

	public Blog getBlogById(int id) {
		Session session =sessionFactory.openSession();
		session.beginTransaction();
		Blog blog= (Blog) session.get(Blog.class,id);
		session.getTransaction().commit();
		return blog;
	}


	public void updateBlog(Blog blog) {
		Session session =sessionFactory.getCurrentSession();
		session.update(blog);
		
	}

}
