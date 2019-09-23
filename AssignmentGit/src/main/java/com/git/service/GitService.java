/**
 * 
 */
package com.git.service;

import com.git.modal.Project;
import com.git.modal.Repository;

import reactor.core.publisher.Flux;

/**
 * @author Yash
 *
 */
public interface GitService {

	public Flux<Repository> fetchRepositories() ;

	public Flux<Project> fetchProjects(String owner,String repo);	

	public Flux<Repository> fetchRepoByUser(String userId);
	
}
