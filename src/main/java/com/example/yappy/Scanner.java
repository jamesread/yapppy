package com.example.yappy;

import hudson.ExtensionList;
import hudson.model.TopLevelItem;
import hudson.model.FreeStyleProject;
import hudson.model.Job;

import java.util.Vector;

import jenkins.model.Jenkins;

import com.example.yappy.checkers.CheckerBase;

public class Scanner {
	private final Vector<CheckerBase> checkers = new Vector<CheckerBase>();

	private final Vector<Issue> issues = new Vector<Issue>();

	public Scanner() {
		ExtensionList<CheckerBase> extensions = Jenkins.getInstance().getExtensionList(CheckerBase.class);
		this.checkers.addAll(extensions);
	}

	public void addIssue(Issue i) {
		this.issues.add(i);
	}

	public Vector<CheckerBase> getCheckers() {
		return this.checkers;
	}

	public Vector<Issue> scan(Dashboard dashboard) throws Exception {
		for (CheckerBase checker : this.checkers) {
			checker.checkRoot(this, dashboard);

			for (TopLevelItem i : dashboard.getItems()) {
				if (i instanceof FreeStyleProject) {
					checker.checkProject(this, (FreeStyleProject) i);
				} else if (i instanceof Job) {
					checker.checkJob(this, (Job) i);
				} else {
					checker.check(this, i);
				}

			}
		}

		return this.issues;
	}
}
