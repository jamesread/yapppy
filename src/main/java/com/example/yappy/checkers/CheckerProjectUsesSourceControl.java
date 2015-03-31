package com.example.yappy.checkers;

import hudson.model.Job;

import com.example.yappy.Checker;
import com.example.yappy.Issue;
import com.example.yappy.Issue.Severity;
import com.example.yappy.Scanner;

public class CheckerProjectUsesSourceControl extends Checker {
	@Override
	public void checkJob(Scanner scanner, Job job) {
		if (this.jobConfigXpath(job, "/maven2-moduleset/scm/@class").contains("Null")) {
			Issue i = new Issue(this, job, "This project has no form of source control.", Severity.WARNING);
			scanner.addIssue(i);
		}
	}

}
