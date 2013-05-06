package com.example.projectmanager;

public class Project {
	  private long id;
	  private String project;
	  private String description;

	  public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
	    return id;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public String getProject() {
	    return project;
	  }

	  public void setProject(String project) {
	    this.project = project;
	  }

	  // Will be used by the ArrayAdapter in the ListView
	  @Override
	  public String toString() {
	    return project;
	  }
	} 