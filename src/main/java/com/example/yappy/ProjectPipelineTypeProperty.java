package com.example.yappy;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import hudson.Extension;
import hudson.model.JobProperty;
import hudson.model.AbstractProject; 
import hudson.model.JobPropertyDescriptor;

import java.util.logging.Logger; 

public class ProjectPipelineTypeProperty extends JobProperty<AbstractProject<?,?>> {
	private ProjectPipelineType projectUrl; 
	
	@DataBoundConstructor
	public ProjectPipelineTypeProperty(String projectUrl) {
		this.projectUrl = new ProjectPipelineType(projectUrl);
	}
	
	public String getProjectPipelineType() { 
		return projectUrl.get();    
	}   
	  
	@Extension
	public static final class DescriptorImpl extends JobPropertyDescriptor {
		public DescriptorImpl() {
            super(ProjectPipelineTypeProperty.class);
            load();
        }
	         
		@Override
		public String getDisplayName() {
			return "Project Pipeline Type";
		}
		  
        @Override
        public JobProperty<?> newInstance(StaplerRequest req, JSONObject formData) throws FormException {
        	ProjectPipelineTypeProperty tpp = req.bindJSON(ProjectPipelineTypeProperty.class, formData);

            if (tpp == null) {
                LOGGER.fine("Couldn't bind JSON");
                return null;
            }
            if (tpp.projectUrl == null) { 
                tpp = null; // not configured
                LOGGER.fine("projectUrl not found, nullifying GithubProjectProperty");
            }
            
            return tpp;
        }
		
        private static final Logger LOGGER = Logger.getLogger(ProjectPipelineTypeProperty.class.getName());
	}
}
