/**
 * 
 */
package com.git.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.git.modal.Project;
import com.git.modal.Repository;
import com.git.service.GitService;

import reactor.core.publisher.Flux;

/**
 * @author Yash
 * main controller to perform github and git lab operations.
 * 3 endpoints define
 */
@RestController
@EnableWebMvc
@RequestMapping(path = "/git")
public class GitController {

	private static final Logger logger = LogManager.getLogger(GitController.class);

	@Autowired
	private GitService service;

	/*
	 * Spring based exception handling using ExceptionHandller Api for fetching all
	 * the repositories of github and gitlab
	 * 
	 */
	@ExceptionHandler(WebClientResponseException.class)
	@RequestMapping(path = "/repos", method = RequestMethod.GET)
	public ResponseEntity<Flux<Repository>> getAllRepositories() {
		logger.info("Entering into the fetch repository method");
		return new ResponseEntity<Flux<Repository>>(service.fetchRepositories(), HttpStatus.OK);
	}

	/*
	 * Method for ffetching the github  projects
	 * @param String owner
	 * @param String repository name
	 */
	@RequestMapping(path = "/projects", method = RequestMethod.GET)
	public ResponseEntity<Flux<Project>> getGitHubProject(@RequestParam(name = "owner") String owner,
			@RequestParam(name = "repos") String repo) {

		logger.info("Entering into the fetch github projects" + owner + "and repo name" + repo);
		return new ResponseEntity<Flux<Project>>(service.fetchProjects(owner, repo), HttpStatus.OK);
	}

	/*
	 * Method to fetch projects for particular user
	 * @param username
	 */
	@RequestMapping(path = "/repos/user", method = RequestMethod.GET)
	public ResponseEntity<Flux<Repository>> getProjectById(@RequestParam(name = "username") String username) {
		logger.info("Entering into the repositories fron  github for" + username);
		return new ResponseEntity<Flux<Repository>>(service.fetchRepoByUser(username), HttpStatus.OK);
	}

}
