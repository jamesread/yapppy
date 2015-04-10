package com.example.yappy.checkers;

import hudson.Extension;
import hudson.model.Job;

import com.example.yappy.SimpleChecker;
import com.example.yappy.Issue;
import com.example.yappy.Issue.Severity;
import com.example.yappy.Scanner;

@Extension
public class CheckerProjectNameDoesntContainFoo extends SimpleChecker {
	@Override
	public void checkJob(Scanner scanner, Job job) {
		if (job.getName().contains("foo")) {
			Issue i = new Issue(this, job, "Project contains the word 'foo'", Severity.WARNING);

			scanner.addIssue(i);
		}
	}

}
