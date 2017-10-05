package com.collaborate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.collaborate.model.ForumComment;

@Repository("ForumCommentDAO")
public class ForumCommentDAOImpl implements ForumCommentDAO {

	
	SessionFactory sessionFactory;
	 public ForumCommentDAOImpl(SessionFactory sessionFactory)
	 {
		 this.sessionFactory=sessionFactory;
	 }
	@Override
	public boolean createForumComment(ForumComment forumComment) {
		
		try{
			Session session= sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(forumComment);
			session.getTransaction().commit();
			System.out.println("ForumComment created");
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ForumComment getForumComment(int id) {
		try{
		Session session= sessionFactory.openSession();
		ForumComment forumComment=(ForumComment) session.get(ForumComment.class,id);
		System.out.println("Got forum comment by id");
		return forumComment;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<ForumComment> getForumComments() {
		Session session= sessionFactory.openSession();
		Query query=session.createQuery("from ForumComment");
		List<ForumComment> listForumComment= query.list();
		return listForumComment;
	}

	@Override
	public boolean editForumComment(int id) {
		try{
			Session session= sessionFactory.openSession();
			ForumComment forumComment= (ForumComment) session.get(ForumComment.class,id);
			forumComment.setComment("Correct comment");
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
	public boolean deleteForumComment(int id) {
		try{
			Session session= sessionFactory.openSession();
			ForumComment forumComment= (ForumComment) session.get(ForumComment.class,id);
			session.delete(forumComment);
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
