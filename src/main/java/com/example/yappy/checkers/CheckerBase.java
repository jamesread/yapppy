package com.example.yappy.checkers;

import hudson.ExtensionPoint;
import hudson.model.TopLevelItem;
import hudson.model.FreeStyleProject;
import hudson.model.Job;

import com.example.yappy.Scanner;
import com.example.yappy.Dashboard;

public interface CheckerBase extends ExtensionPoint {
	public void check(Scanner scanner, TopLevelItem i);

	public void checkJob(Scanner scanner, Job job);

	public void checkProject(Scanner scanner, FreeStyleProject i);

	public void checkRoot(Scanner scanner, Dashboard dashboard);
}
