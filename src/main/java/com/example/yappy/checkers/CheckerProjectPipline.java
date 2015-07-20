package com.example.yappy.checkers;

import java.util.logging.Logger;

import hudson.Extension;
import hudson.model.FreeStyleProject;

import com.example.yappy.ProjectInsidePipeline;
import com.example.yappy.Scanner;
import com.example.yappy.SimpleChecker;
import com.example.yappy.Dashboard;
import com.example.yappy.ProjectPipelineTypeProperty;

@Extension
public class CheckerProjectPipline extends SimpleChecker {
	Logger logger = Logger.getAnonymousLogger(); 
	
	@Override
	public void checkProject(Scanner scanner, FreeStyleProject i) {	
		ProjectPipelineTypeProperty type =  i.getProperty(ProjectPipelineTypeProperty.class);
		
		System.out.println("Checking project pipeline: " + i.getName());  
		
		if (type != null) {
			Dashboard.pipelineRegistery.register(new ProjectInsidePipeline(i));
		} 
	}
}  
