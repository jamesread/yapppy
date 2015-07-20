package com.example.yappy;

import hudson.Extension;
import hudson.model.Action;

@Extension
public class Button implements Action {

	public Button() {
	}

	@Override
	public String getDisplayName() {
		return "Yappy"; 
	}

	@Override
	public String getIconFileName() {
		return "";
	}

	@Override
	public String getUrlName() {
		return ""; 
	}
}
