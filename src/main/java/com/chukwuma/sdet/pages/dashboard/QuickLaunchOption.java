package com.chukwuma.sdet.pages.dashboard;

public enum QuickLaunchOption {

    ASSIGN_LEAVE("Assign Leave", "/leave/assignLeave"),
    LEAVE_LIST("Leave List", "/leave/viewLeaveList"),
    TIMESHEETS("Timesheets", "/time/viewEmployeeTimesheet"),
    APPLY_LEAVE("Apply Leave", "/leave/applyLeave"),
    MY_LEAVE("My Leave", "/leave/viewMyLeaveList"),
    MY_TIMESHEET("My Timesheet", "/time/viewMyTimesheet");

    private final String label;
    private final String urlFragment;

    QuickLaunchOption(String label, String urlFragment) {
        this.label = label;
        this.urlFragment = urlFragment;
    }

    public String getLabel() {
        return label;
    }

    public String getUrlFragment() {
        return urlFragment;
    }
}