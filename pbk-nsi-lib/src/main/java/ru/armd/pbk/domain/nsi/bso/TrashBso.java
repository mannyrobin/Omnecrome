package ru.armd.pbk.domain.nsi.bso;

import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

public class TrashBso extends BaseDomain {

    private Long bsoId;
    private String issueDate;
    private String reason;
    private Long employeeOffId;
    private Date endDate;
    private String actNumber;

    public Long getBsoId() {
        return bsoId;
    }

    public void setBsoId(Long bsoId) {
        this.bsoId = bsoId;
    }

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

    public Long getEmployeeOffId() {
        return employeeOffId;
    }

    public void setEmployeeOffId(Long employeeOffId) {
        this.employeeOffId = employeeOffId;
    }

    public String getActNumber() {
        return actNumber;
    }

    public void setActNumber(String actNumber) {
        this.actNumber = actNumber;
    }
}
