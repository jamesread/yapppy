package com.example.yappy;

import hudson.model.Job;

public class Issue {
	public enum Severity {
		INFO, WARNING, ERROR;
	}

	private String sourceName = "???";
	private String sourceUrl = "";

	private final String description;
	private Severity severity = Severity.WARNING;

	private final Checker checker;
	private String checkerName = "system";

	public Issue(Checker checker, Job job, String desc, Severity severity) {
		this(checker, desc, severity);

		this.sourceUrl = job.getUrl();
		this.sourceName = job.getName();
	}

	public Issue(Checker checker, String description, Severity severity) {
		this.checker = checker;

		this.description = description;
		this.severity = severity;
	}

	public Issue(String checkerName, String description, Severity severity) {
		this.checkerName = checkerName;
		this.checker = null;

		this.description = description;
		this.severity = severity;
	}

	public void addTo(Scanner scanner) {
		scanner.addIssue(this);
	}

	public String getDescription() {
		return this.description;
	}

	public String getInfoUrl() {
		return "";
	}

	public String getName() {
		if (this.checker == null) {
			return this.checkerName;
		} else {
			return Util.hypenatedClassName(this.checker).replace("Checker-", "");
		}
	}

	public Severity getSeverity() {
		return this.severity;
	}

	public String getSourceName() {
		return this.sourceName;
	}

	public String getSourceUrl() {
		return this.sourceUrl;
	}
}
