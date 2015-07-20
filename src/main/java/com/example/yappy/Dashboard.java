package com.example.yappy;

import hudson.Extension;
import hudson.model.Item;
import hudson.model.Items;
import hudson.model.TopLevelItem;
import hudson.model.Descriptor.FormException;
import hudson.model.View;
import hudson.model.ViewDescriptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import com.example.yappy.Issue.Severity;
import com.example.yappy.checkers.CheckerBase;

@Extension 
public class Dashboard extends View {
	public static ProjectPipelineRegistry pipelineRegistery = new ProjectPipelineRegistry();  

	@Extension
	public static final class DescriptorImpl extends ViewDescriptor {
		@Override
		public String getDisplayName() {
			return "Yappy";
		}
	}

	private Exception exception;
	private Vector<Issue> issues = new Vector<Issue>();

	private Scanner scanner = new Scanner();

	public Dashboard() {
		super("Yappy");

		this.init();
	}

	@DataBoundConstructor
	public Dashboard(String name) {
		super(name);

		this.init();
	}

	@Override
	public boolean contains(TopLevelItem arg0) {
		return false;
	}

	@Override
	public Item doCreateItem(StaplerRequest arg0, StaplerResponse arg1) throws IOException, ServletException {
		return null;
	}

	public String getCheckerList() {
		StringBuilder sb = new StringBuilder();

		for (CheckerBase checker : this.getCheckers()) {
			sb.append(Util.checkerName(checker));
			sb.append("\n");
		}

		return sb.toString();
	}

	public Vector<CheckerBase> getCheckers() {
		return this.scanner.getCheckers();
	}

	public String getException() {
		if (this.exception == null) {
			return "";
		} else {
			this.exception.printStackTrace();

			return "An exception of type " + this.exception.getClass() + " occoured in " + this.exception.getStackTrace()[0].getClassName() + ":" + this.exception.getStackTrace()[0].getLineNumber() + " when Yappy was performing a scan. This exception has been printed to the jenkins log.";
		}
	}

	public Vector<Issue> getIssues() {
		return this.issues;
	}  

	@Override
	public List<TopLevelItem> getItems() {
		ArrayList<TopLevelItem> items = new ArrayList<TopLevelItem>();
		items.addAll(Items.getAllItems(this.getOwnerItemGroup(), TopLevelItem.class));

		return items;
	}

	public Scanner getNewScanner() {
		pipelineRegistery.clear();
		System.out.println("getNewScanner - starting a new scan");

		this.scanner = new Scanner();

		try {
			this.issues = this.scanner.scan(this);
		} catch (Exception e) {
			this.exception = e;
		}

		this.issues.add(new Issue("quality-warning", "Yappy is alpha quality software at the moment.", Severity.INFO));

		return this.scanner;
	}

	public String getVersion() {
		String jarVersion = this.getClass().getPackage().getImplementationVersion();

		if ((jarVersion == null) || jarVersion.isEmpty()) {
			return "???";
		} else {
			return jarVersion;
		}
	}

	private void init() {
		System.out.println("Dashboard constructed");
	}
	 
	public String getPipelines() {
		return "Pipeline 1: " + Dashboard.pipelineRegistery.asString(); 
	}

	@Override
	protected void submit(StaplerRequest arg0) throws IOException, ServletException, FormException {
	}
}
