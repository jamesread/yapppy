package com.example.yappy.checkers;

import com.example.yappy.Dashboard;
import com.example.yappy.Scanner;
import hudson.ExtensionPoint;
import hudson.model.Job;
import hudson.model.TopLevelItem;

public interface CheckerBase extends ExtensionPoint {
    public void check(Scanner scanner, TopLevelItem i);

    public void checkJob(Scanner scanner, Job job);

    public void checkRoot(Scanner scanner, Dashboard dashboard);
}
