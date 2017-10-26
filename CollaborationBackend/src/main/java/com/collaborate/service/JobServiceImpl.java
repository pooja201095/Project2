package com.collaborate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.collaborate.dao.JobDAO;
import com.collaborate.model.Job;
@Service
public class JobServiceImpl implements JobService{

	@Autowired
	JobDAO jobDAO;
	public void addJob(Job job) {
		jobDAO.addJob(job);
		
	}

	public List<Job> getAllJobs() {
		
		return jobDAO.getAllJobs();
	}

	@Override
	public Job getJob(int jobId) {
		
		return jobDAO.getJob(jobId);
	}

}
