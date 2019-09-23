package com.git.modal;

import reactor.core.publisher.Flux;

public class DefaultResponse {
	
	private Flux<Repository> flux;
	
	private  String responseType;

	public Flux<Repository> getFlux() {
		return flux;
	}

	public void setFlux(Flux<Repository> flux) {
		this.flux = flux;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	
	

}
