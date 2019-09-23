package com.git.modal;

public class Repository {

	    private String id;

	    private String name;

	    private String default_branch;

	    private String created_at;

	    private String has_projects;
	    
	    private String web_url;

	    private String html_url;
	    
	    
		public String getWeb_url() {
			return web_url;
		}

		public void setWeb_url(String web_url) {
			this.web_url = web_url;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDefault_branch() {
			return default_branch;
		}

		public void setDefault_branch(String default_branch) {
			this.default_branch = default_branch;
		}

		public String getCreated_at() {
			return created_at;
		}

		public void setCreated_at(String created_at) {
			this.created_at = created_at;
		}

		public String getHas_projects() {
			return has_projects;
		}

		public void setHas_projects(String has_projects) {
			this.has_projects = has_projects;
		}

		public String getHtml_url() {
			return html_url;
		}

		public void setHtml_url(String html_url) {
			this.html_url = html_url;
		}

	    
}
