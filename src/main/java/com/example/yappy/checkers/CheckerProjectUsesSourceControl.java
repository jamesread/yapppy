package com.example.yappy.checkers;

import hudson.Extension;
import hudson.model.Job;

import com.example.yappy.SimpleChecker;
import com.example.yappy.Issue;
import com.example.yappy.Issue.Severity;
import com.example.yappy.Scanner;

@Extension
public class CheckerProjectUsesSourceControl extends SimpleChecker {
	@Override
	public void checkJob(Scanner scanner, Job job) {
		if (this.jobConfigXpath(job, "/maven2-moduleset/scm/@class").contains("Null")) {
			Issue i = new Issue(this, job, "This project has no form of source control.", Severity.WARNING);
			scanner.addIssue(i);
		}
	}

}
