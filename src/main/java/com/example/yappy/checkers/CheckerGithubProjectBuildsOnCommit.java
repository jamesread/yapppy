package com.example.yappy.checkers;

import hudson.Extension;
import hudson.FilePath;
import hudson.model.FreeStyleBuild;
import hudson.model.FreeStyleProject;

import java.io.IOException;

import com.example.yappy.Issue;
import com.example.yappy.Issue.Severity;
import com.example.yappy.Scanner;
import com.example.yappy.SimpleChecker;

@Extension
public class CheckerGithubProjectBuildsOnCommit extends SimpleChecker {
	@Override
	public void checkProject(Scanner scanner, FreeStyleProject project) {
		FreeStyleBuild build = project.getLastBuild();

		System.out.println("buld on commit");

		if (build != null) {
			FilePath workspace = build.getWorkspace();
			FilePath gitconfig = new FilePath(workspace, ".git/config");

			try {
				if (gitconfig.exists()) {
					if (gitconfig.readToString().contains("github.com")) {
						String projectUrl = this.jobConfigXpath(project, "/project/properties/com.coravy.hudson.plugins.github.GithubProjectProperty/projectUrl/text()");

						if (projectUrl.isEmpty()) {
							new Issue(this, "This is a GitHub project, but the project URL is not configured. Should be: " + projectUrl, Severity.INFO).addTo(scanner);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}