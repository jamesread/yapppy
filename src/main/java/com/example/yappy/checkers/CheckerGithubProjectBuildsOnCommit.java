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

		if (build != null) {
			FilePath workspace = build.getWorkspace();
			FilePath gitconfig = new FilePath(workspace, ".git/config");

			try {
				if (gitconfig.exists()) {
					if (gitconfig.readToString().contains("github.com")) {
						String projectUrl = this.jobConfigXpath(project, "/project/properties/com.coravy.hudson.plugins.github.GithubProjectProperty/projectUrl/text()");

						if (projectUrl.isEmpty()) {
							new Issue(this, project, "This is a GitHub project, but the project URL is not configured.", Severity.WARNING).addTo(scanner);
						}

						String buildOnCommit = this.jobConfigXpath(project, "boolean(/project/triggers/com.cloudbees.jenkins.GitHubPushTrigger)");

						if (buildOnCommit.equals("false")) {
							new Issue(this, project, "This GitHub project, but is not set to build on commits to GitHub.", Severity.INFO).addTo(scanner);
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
