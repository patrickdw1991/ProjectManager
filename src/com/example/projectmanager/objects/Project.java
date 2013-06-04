package com.example.projectmanager.objects;

import java.io.Serializable;

public class Project implements Serializable {
	  private long id;
	  private String project;
	  private String description;
      private String sDescription;
      private long date;

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

    public String getsDescription() {
        return sDescription;
    }

    public void setsDescription(String sDescription) {
        this.sDescription = sDescription;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}