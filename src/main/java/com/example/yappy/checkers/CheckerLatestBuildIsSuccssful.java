package com.example.yappy.checkers;

import hudson.Extension;
import hudson.model.Result;
import hudson.model.Job;

import com.example.yappy.SimpleChecker;
import com.example.yappy.Issue;
import com.example.yappy.Issue.Severity;
import com.example.yappy.Scanner;
import hudson.model.Run;

@Extension
public class CheckerLatestBuildIsSuccssful extends SimpleChecker {
	@Override
	public void checkJob(Scanner scanner, Job job) {
		Run lastBuild = job.getLastBuild();

        if (lastBuild == null) {
            new Issue(this, job, "Job has never been built.", Severity.INFO);
            return;
        }

        if (lastBuild.isBuilding()) {
            return;
        }

        Result lastResult = lastBuild.getResult();

		if ((lastResult == Result.UNSTABLE) || (lastResult == Result.FAILURE)) {
			new Issue(this, job, "Job is not building successfully.", Severity.WARNING).addTo(scanner);
		}
	}
}
