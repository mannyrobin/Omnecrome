package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.views.tasks.TaskDateSerializer;

import java.util.Date;

import static ru.armd.pbk.utils.StringUtils.binaryToMarkSymbol;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта "Сверка с ГКУ ОП".
 */
public class So24View
	extends SoView {

    @JsonSerialize(using = TaskDateSerializer.class)
    private Date taskDate;
    private String employeeName;
    private String personnelNumber;
    private String shiftName;
    private String departmentName;
    private String bsoNumber;
    private String taskState;
    private String isBsk;
    private String isVideo;
    private String isClosed;

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPersonnelNumber() {
        return personnelNumber;
    }

    public void setPersonnelNumber(String personnelNumber) {
        this.personnelNumber = personnelNumber;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBsoNumber() {
        return bsoNumber;
    }

    public void setBsoNumber(String bsoNumber) {
        this.bsoNumber = bsoNumber;
    }

    public String getTaskState() {
        return taskState;
    }

    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }

    public String getIsBsk() {
        return isBsk;
    }

    public void setIsBsk(String isBsk) {
        this.isBsk =  binaryToMarkSymbol(isBsk);
    }

    public String getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(String isVideo) {
        this.isVideo =  binaryToMarkSymbol(isVideo);
    }

    public String getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(String isClosed) {
        this.isClosed =  binaryToMarkSymbol(isClosed);
    }
}
