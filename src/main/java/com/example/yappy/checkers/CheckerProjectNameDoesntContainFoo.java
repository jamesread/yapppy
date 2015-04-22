package com.example.yappy.checkers;

import hudson.Extension;
import hudson.model.FreeStyleProject;

import com.example.yappy.Issue;
import com.example.yappy.Issue.Severity;
import com.example.yappy.Scanner;
import com.example.yappy.SimpleChecker;

@Extension
public class CheckerProjectNameDoesntContainFoo extends SimpleChecker {
	@Override
	public void checkProject(Scanner scanner, FreeStyleProject project) {
		if (project.getName().contains("foo")) {
			Issue i = new Issue(this, project, "Project contains the word 'foo'", Severity.WARNING);

			scanner.addIssue(i);
		}
	}

}
