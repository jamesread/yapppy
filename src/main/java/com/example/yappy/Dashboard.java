package com.example.yappy;

import hudson.Extension;
import hudson.model.ListView;

import java.util.Vector;

import jenkins.model.ModelObjectWithContextMenu.ContextMenu;

import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import com.example.yappy.Issue.Severity;

@Extension
public class Dashboard extends ListView {
	public Dashboard() {
		super("Yappy");
	}

	@Override
	public ContextMenu doChildrenContextMenu(StaplerRequest arg0, StaplerResponse arg1) throws Exception {
		ContextMenu menu = super.doChildrenContextMenu(arg0, arg1);

		menu.add(new Button());

		return menu;
	}

	public Vector<Issue> getIssues() {
		Scanner scanner = new Scanner();
		Vector<Issue> issues = scanner.scan(this);

		issues.add(new Issue("quality-warning", "Yappy is alpha quality software at the moment.", Severity.INFO));

		return issues;
	}
}
