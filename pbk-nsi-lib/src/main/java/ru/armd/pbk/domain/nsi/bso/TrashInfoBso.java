package ru.armd.pbk.domain.nsi.bso;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

public class TrashInfoBso extends BaseDomain {

    private String issueDate;
    private Date endDate;
    private String reason;
    private String employeeOffName;
    private String createUserLogin;
    private String actNumber;

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEmployeeOffName() {
        return employeeOffName;
    }

    public void setEmployeeOffName(String employeeOffName) {
        this.employeeOffName = employeeOffName;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public String getActNumber() {
        return actNumber;
    }

    public void setActNumber(String actNumber) {
        this.actNumber = actNumber;
    }
}