package com.niit.chattzz.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.chattzz.dao.JobDao;
import com.niit.chattzz.domain.Job;
import com.niit.chattzz.domain.User;
import com.niit.chattzz.domain.Error;
@RestController
public class JobRestService {
	@Autowired
	private JobDao jobDao;

	// Logger logger = LoggerFactory.getLogger(this.getClass());

	// to post a job
	@RequestMapping(value = "/postJob", method = RequestMethod.POST)
	public ResponseEntity<?> postJob(@RequestBody Job job, HttpSession session) {
		//logger.debug("Entering JOBCONTROLLER : postJob");
		User user = (User) session.getAttribute("user");
		if (user == null) {
			Error error = new Error(1, "Unauthorized user...login using correct credentials");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);
		} else {
			job.setPostedOn(new Date());
			jobDao.postJob(job);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// to get all jobs
	@RequestMapping(value = "/getAllJobs", method = RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			//logger.debug("USER is null");
			Error error = new Error(1, "Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		}
		System.out.println("USER OBJECT " + user.getRole());
		List<Job> jobs = jobDao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK); // 200
	}

	// to get job detail
	@RequestMapping(value = "/getJobDetail/{jobId}", method = RequestMethod.GET)
	public ResponseEntity<?> getJobDetail(@PathVariable(value = "jobId") int jobId, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			//logger.debug("USER is null");
			Error error = new Error(1, "Unauthorized user.. login using valid credentials");
			return new ResponseEntity<Error>(error, HttpStatus.UNAUTHORIZED);// 401
		}
		//logger.debug("JOBID = " + jobId);
		Job jobDetail = jobDao.getJobByID(jobId);
		//logger.debug("jobDetail = " + jobDetail);
		return new ResponseEntity<Job>(jobDetail, HttpStatus.OK); // 200
	}

}
