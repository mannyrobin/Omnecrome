package ru.armd.pbk.domain.nsi.bso;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BsoDto {

    String number;
    Integer status;

    @JsonProperty("task-number")
    String taskNumber;

    @JsonProperty("inspector_employee_number")
    String employeeNumber;

    @JsonProperty("task_id")
    String taskId;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
