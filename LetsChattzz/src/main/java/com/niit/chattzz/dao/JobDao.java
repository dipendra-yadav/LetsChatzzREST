package com.niit.chattzz.dao;

import java.util.List;

import com.niit.chattzz.domain.Job;

public interface JobDao {

	public List<Job> getAllJobs();

	public void postJob(Job job);

	public boolean update(Job job);

	public boolean delete(Job job);

	public Job getJobByID(int jobid);

	public Job getJobByTitle(String jobTitle);

}
