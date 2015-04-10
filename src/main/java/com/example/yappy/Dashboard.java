package com.example.yappy;

import com.example.yappy.checkers.CheckerBase;
import hudson.Extension;
import hudson.model.ListView;
import hudson.model.ViewDescriptor;

import java.util.Vector;

import jenkins.model.ModelObjectWithContextMenu.ContextMenu;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import com.example.yappy.Issue.Severity;
import sun.tools.jar.resources.jar;

@Extension
public class Dashboard extends ListView {
    private Exception exception;
    private Vector<Issue> issues = new Vector<Issue>();
    private Scanner scanner = new Scanner();

	@Extension
	public static final class DescriptorImpl extends ViewDescriptor {
		@Override
		public String getDisplayName() {
			return "Yappy";
		}
	}

	public Dashboard() {
		super("Yappy");

        init();
	}

	@DataBoundConstructor
	public Dashboard(String name) {
		super(name);

        init();
	}

    private void init() {
        System.out.println("Dashboard constructed");
    }

	@Override
	public ContextMenu doChildrenContextMenu(StaplerRequest arg0, StaplerResponse arg1) throws Exception {
		ContextMenu menu = super.doChildrenContextMenu(arg0, arg1);

		menu.add(new Button());

		return menu;
	}

    public String getException() {
        if (this.exception == null) {
            return "";
        } else {
            exception.printStackTrace();

            return "An exception of type " + this.exception.getClass() + " occoured in " + exception.getStackTrace()[0].getClassName() + ":" + exception.getStackTrace()[0].getLineNumber() + " when Yappy was performing a scan. This exception has been printed to the jenkins log.";
        }
    }

    public Scanner getNewScanner() {
        System.out.println("getNewScanner - starting a new scan");

        this.scanner = new Scanner();

        try {
            this.issues = scanner.scan(this);
        } catch (Exception e) {
            this.exception = e;
        }

        issues.add(new Issue("quality-warning", "Yappy is alpha quality software at the moment.", Severity.INFO));

        return scanner;
    }

    public Vector<CheckerBase> getCheckers() {
        return this.scanner.getCheckers();
    }

    public String getCheckerList() {
        StringBuilder sb = new StringBuilder();

        for (CheckerBase checker : this.getCheckers()) {
            sb.append(Util.checkerName(checker));
            sb.append("\n");
        }

        return sb.toString();
    }

	public Vector<Issue> getIssues() {
        return this.issues;
	}

	public String getVersion() {
		String jarVersion = this.getClass().getPackage().getImplementationVersion();

        if (jarVersion == null || jarVersion.isEmpty()) {
            return "???";
        } else {
            return jarVersion;
        }
	}
}
