package com.example.yappy.checkers;

import hudson.model.Result;
import hudson.model.Job;

import com.example.yappy.Checker;
import com.example.yappy.Issue;
import com.example.yappy.Issue.Severity;
import com.example.yappy.Scanner;

public class CheckerLatestBuildIsSuccssful extends Checker {
	@Override
	public void checkJob(Scanner scanner, Job job) {
		Result lastResult = job.getLastBuild().getResult();

		if ((lastResult == Result.UNSTABLE) || (lastResult == Result.FAILURE)) {
			new Issue(this, job, "Job is not building successfully.", Severity.WARNING).addTo(scanner);
		}
	}
}
