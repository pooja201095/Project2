package com.collaborate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.collaborate.model.Forum;

@Repository("ForumDAO")
public class ForumDAOImpl implements ForumDAO{

	
	@Autowired
	SessionFactory sessionFactory;
	
	public ForumDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	@Override
	public boolean createForum(Forum forum) {
		
		try
		{
			Session session= sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(forum);
			session.getTransaction().commit();
			/*sessionFactory.getCurrentSession().saveOrUpdate(forum);*/
			System.out.println("Forum Created.......");
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception:"+e);
			return false;
		}
	}

	
	@Override
	public Forum getForum(int forumid) {
		try
		{
		Session session=sessionFactory.openSession();
		Forum forum=(Forum) session.get(Forum.class,forumid);
		System.out.println(forum.getForumId());
		return forum;
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
	public List<Forum> getForums() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Forum");
		List<Forum> listforum= query.list();
		session.close();
		return listforum;
	}
	
	@Transactional
	@Override
	public List<Forum> getApprovedForums() {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Forum where status='A'");
		List<Forum> listforum= query.list();
		session.close();
		return listforum;
	}

	@Transactional
	@Override
	public boolean approveForum(Forum forum) {

		try{
			forum.setStatus("A");
			sessionFactory.getCurrentSession().saveOrUpdate(forum);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception:"+e);
			return false;
		}
	}

	
	@Override
	public boolean editForum(int forumid) {
		try{
			Session session=sessionFactory.openSession();
			Forum forum=(Forum) session.get(Forum.class,forumid);
			forum.setForumName("Java");
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
	public boolean deleteForum(int forumid) {
		try
		{
		Session session=sessionFactory.openSession();
		Forum forum=(Forum) session.get(Forum.class,forumid);
		session.delete(forum);
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
