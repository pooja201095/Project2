package com.collaborate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaborate.model.Friend;
import com.collaborate.model.Users;

@Repository
@Transactional
public class FriendDAOImpl implements FriendDAO{
	@Autowired
	SessionFactory sessionFactory;
	

	@Override
	public List<Users> listOfSuggestedUsers(String userid) {
		Session session=sessionFactory.getCurrentSession();
		SQLQuery query=session.createSQLQuery("select * from Users where userid IN(select userid from Users where userid!=? minus (select fromId from Friend where toId=? union select toId from Friend where fromId=?))");
		System.out.println("get list of suggested users for "+userid+" successfully...!!");
		query.setString(0, userid);
		query.setString(1, userid);
		query.setString(2, userid);
		query.addEntity(Users.class);
		List<Users> suggestedUsers=query.list();
		System.out.println("List of suggested users...........");
		return suggestedUsers;
		
	}

	@Override
	public void friendRequest(Friend friend) {
		System.out.println("in fnd req.....");
		Session session=sessionFactory.getCurrentSession();
		session.save(friend);
		
	}

	@Override
	public List<Friend> pendingRequests(String toId) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from Friend where toId=? and status='P'");
		query.setString(0, toId);
		return query.list();
	}

	@Override
	public void updatePendingRequests(Friend friend) {
		Session session=sessionFactory.getCurrentSession();
		if(friend.getStatus()=='A')
			session.update(friend);
		else
			session.delete(friend);
		
	}

	@Override
	public List<String> listOfFriends(String userid) {
		Session session=sessionFactory.getCurrentSession();
		SQLQuery sqlQuery1=session.createSQLQuery("select fromId from Friend where toId=? and status='A'")
				.addScalar("fromId",StandardBasicTypes.STRING);
		sqlQuery1.setString(0, userid);
		List<String> list1=sqlQuery1.list();
		System.out.println("list of first query "+list1);
		
		
		SQLQuery sqlQuery2=session.createSQLQuery("select toId from Friend where fromId=? and status='A'")
				.addScalar("toId",StandardBasicTypes.STRING);
		sqlQuery2.setString(0, userid);
		List<String> list2=sqlQuery2.list();
		System.out.println("list of first query "+list2);
		
		list1.addAll(list2);
		System.out.println("Result of lis1+list2 "+list1);
		return list1;
		
	}

}
