package com.example.yappy.checkers;

import hudson.model.Job;

import com.example.yappy.Checker;
import com.example.yappy.Issue;
import com.example.yappy.Issue.Severity;
import com.example.yappy.Scanner;

public class CheckerProjectNameDoesntContainFoo extends Checker {
	@Override
	public void checkJob(Scanner scanner, Job job) {
		if (job.getName().contains("foo")) {
			Issue i = new Issue(this, job, "Project contains the word 'foo'", Severity.WARNING);

			scanner.addIssue(i);
		}
	}

}
