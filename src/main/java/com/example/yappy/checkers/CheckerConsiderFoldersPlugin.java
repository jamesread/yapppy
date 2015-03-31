package com.example.yappy.checkers;

import com.example.yappy.Checker;
import com.example.yappy.Issue;
import com.example.yappy.Issue.Severity;
import com.example.yappy.Scanner;
import com.example.yappy.Dashboard;

public class CheckerConsiderFoldersPlugin extends Checker {
	@Override
	public void checkRoot(Scanner scanner, Dashboard dashboard) {
		if (dashboard.getItems().size() >= 5) {
			scanner.addIssue(new Issue(this, "This view can see over 5 jobs, which is quite a lot to look at. Consider using the folders plugin to better organize your jobs. ", Severity.INFO));
		}
	}
}
