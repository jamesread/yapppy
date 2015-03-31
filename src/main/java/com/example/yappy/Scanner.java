package com.example.yappy;

import hudson.model.TopLevelItem;
import hudson.model.Job;

import java.util.Vector;

import com.example.yappy.checkers.CheckerConsiderFoldersPlugin;
import com.example.yappy.checkers.CheckerProjectNameDoesntContainFoo;
import com.example.yappy.checkers.CheckerProjectUsesSourceControl;

public class Scanner {
	private final Vector<Checker> checkers = new Vector<Checker>();

	private final Vector<Issue> issues = new Vector<Issue>();

	public Scanner() {
		this.checkers.add(new CheckerConsiderFoldersPlugin());
		this.checkers.add(new CheckerProjectNameDoesntContainFoo());
		this.checkers.add(new CheckerProjectUsesSourceControl());
	}

	public void addIssue(Issue i) {
		this.issues.add(i);
	}

	public Vector<Issue> scan(Dashboard dashboard) {
		for (Checker checker : this.checkers) {
			checker.checkRoot(this, dashboard);

			for (TopLevelItem i : dashboard.getItems()) {
				if (i instanceof Job) {
					checker.checkJob(this, (Job) i);
				} else {
					checker.check(this, i);
				}

			}
		}

		return this.issues;
	}
}
