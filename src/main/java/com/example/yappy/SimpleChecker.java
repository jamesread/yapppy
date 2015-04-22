package com.example.yappy;

import hudson.model.TopLevelItem;
import hudson.model.FreeStyleProject;
import hudson.model.Job;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.tools.ant.filters.StringInputStream;
import org.w3c.dom.Document;

import com.example.yappy.checkers.CheckerBase;

public abstract class SimpleChecker implements CheckerBase {
	@Override
	public void check(Scanner scanner, TopLevelItem i) {

	}

	@Override
	public void checkJob(Scanner scanner, Job job) {

	}

	@Override
	public void checkProject(Scanner scanner, FreeStyleProject i) {
	}

	@Override
	public void checkRoot(Scanner scanner, Dashboard dashboard) {

	}

	protected String getJobConfig(Job job) {
		try {
			return job.getConfigFile().asString();
		} catch (IOException e) {
			return "";
		}
	}

	protected String jobConfigXpath(Job job, String query) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc;

			doc = builder.parse(new StringInputStream(this.getJobConfig(job)));

			XPath xpath = XPathFactory.newInstance().newXPath();
			String res = xpath.compile(query).evaluate(doc);

			return res;
		} catch (Exception e) {

		}

		return "";
	}
}
