package com.collaborate.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaborate.model.Job;

@Repository
@Transactional
public class JobDAOImpl implements JobDAO{

	
	@Autowired
	SessionFactory sessionFactory;
	
	public JobDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
		
	}
	public void addJob(Job job) {
		Session session=sessionFactory.getCurrentSession();
		//Session session=sessionFactory.openSession();
		//session.beginTransaction();
		session.save(job);
		//session.getTransaction().commit();
		System.out.println("Job added successfully...!!");
		
	}

	public List<Job> getAllJobs() {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("From Job");
		System.out.println("Got list of all Jobs....!!");
		return query.list();
	}
	@Override
	public Job getJob(int jobId) {
		Session session=sessionFactory.getCurrentSession();
		Job job=(Job) session.get(Job.class, jobId);
		return job;
	}

}
