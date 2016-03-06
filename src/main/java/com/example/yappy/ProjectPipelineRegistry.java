package com.example.yappy;

import java.util.LinkedList;
import java.util.TreeMap;

public class ProjectPipelineRegistry {
	private BogoTree<ProjectInsidePipeline> tree = new BogoTree<ProjectInsidePipeline>();

	public void register(ProjectInsidePipeline newProject) {
		tree.add(newProject);
	}

	public String asString() {
		StringBuilder builder = new StringBuilder();
		builder.append("*root* -> ");
		 
		for (ProjectInsidePipeline project : tree) {
			builder.append(project.project.getName());
			builder.append(" -> "); 
		}
		 
		return builder.toString();
	}

	public void clear() {
		list.clear();
	}

}
