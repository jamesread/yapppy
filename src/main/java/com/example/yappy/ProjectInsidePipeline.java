package com.example.yappy;

import hudson.model.FreeStyleProject;

public class ProjectInsidePipeline {
	public FreeStyleProject project; 
	public ProjectPipelineTypeProperty type; 
	  
	public ProjectInsidePipeline(FreeStyleProject project) {
		this.project = project;
		this.type = project.getProperty(ProjectPipelineTypeProperty.class);
	}
}
