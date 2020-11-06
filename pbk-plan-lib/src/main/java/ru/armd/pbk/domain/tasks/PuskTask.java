package ru.armd.pbk.domain.tasks;

import java.util.Date;

public class PuskTask {

    //TODO _ _ _ _  @JsonProperties
    private Long taskId;
    private Date workDate;
    private Long taskType;
    private Long taskState;
    private String personId;
    private String taskNumber;
    private Long shiftId;
    private String workTimeFrom;
    private String workTimeTo;
    private String lunchTimeFrom;
    private String lunchTimeTo;
    private String startPlace;
    private String specialRouteNumbers;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Long getTaskType() {
        return taskType;
    }

    public void setTaskType(Long taskType) {
        this.taskType = taskType;
    }

    public Long getTaskState() {
        return taskState;
    }

    public void setTaskState(Long taskState) {
        this.taskState = taskState;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public String getWorkTimeFrom() {
        return workTimeFrom;
    }

    public void setWorkTimeFrom(String workTimeFrom) {
        this.workTimeFrom = workTimeFrom;
    }

    public String getWorkTimeTo() {
        return workTimeTo;
    }

    public void setWorkTimeTo(String workTimeTo) {
        this.workTimeTo = workTimeTo;
    }

    public String getLunchTimeFrom() {
        return lunchTimeFrom;
    }

    public void setLunchTimeFrom(String lunchTimeFrom) {
        this.lunchTimeFrom = lunchTimeFrom;
    }

    public String getLunchTimeTo() {
        return lunchTimeTo;
    }

    public void setLunchTimeTo(String lunchTimeTo) {
        this.lunchTimeTo = lunchTimeTo;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getSpecialRouteNumbers() {
        return specialRouteNumbers;
    }

    public void setSpecialRouteNumbers(String specialRouteNumbers) {
        this.specialRouteNumbers = specialRouteNumbers;
    }
}
