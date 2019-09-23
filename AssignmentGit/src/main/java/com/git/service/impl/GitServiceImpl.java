/**
 * 
 */
package com.git.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.git.modal.Project;
import com.git.modal.Repository;
import com.git.service.GitService;
import com.git.util.Constants;

import io.netty.util.Constant;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Yash
 *
 */
@Service
public class GitServiceImpl implements GitService {

	public Flux<Repository> fetchRepositories() {

		Flux<Repository> gitFlux = getWebClent().get().uri("user/repos")

				.retrieve().onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new Exception()))
				.onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new Exception()))
				.bodyToFlux(Repository.class);

		Flux<Repository> gitlabFlux = getWebClentForGitLab().get().uri("users/"+Constants.GITLAB_USERNAME+"/projects").retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new Exception()))
				.onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new Exception()))
				.bodyToFlux(Repository.class);

		return Flux.merge(gitFlux, gitlabFlux);
	}

	public Flux<Project> fetchProjects(String owner, String repo) {

		return getWebClent().get().uri("repos/" + owner + "/" + repo + "/projects").retrieve()
				.bodyToFlux(Project.class);

	}

	/*
	 * @param projectId
	 */
	public Flux<Repository> fetchRepoByUser(String userId) {
		return getWebClent().get().uri("user/" + userId + "repos").retrieve().bodyToFlux(Repository.class);

	}

	private WebClient getWebClent() {
		return WebClient.builder().baseUrl("https://api.github.com/")
				.defaultHeaders(header -> header.setBasicAuth(Constants.GIT_USERNAME, Constants.GIT_PASSWORD))
				.defaultHeader(HttpHeaders.ACCEPT, "application/vnd.github.inertia-preview+json").build();
	}

	private WebClient getWebClentForGitLab() {
		return WebClient.builder().baseUrl("https://gitlab.com/api/v4/")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json").build();
	}

}
