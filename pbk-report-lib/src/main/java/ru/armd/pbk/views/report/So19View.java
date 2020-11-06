package ru.armd.pbk.views.report;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.armd.pbk.utils.json.DotSepratedDateSerializer;

import java.util.Date;

import static ru.armd.pbk.utils.StringUtils.binaryToCheckMark;
import static ru.armd.pbk.utils.StringUtils.binaryToMarkSymbol;

/**
 * Данные для заполнения полей печатной формы стандартного отчёта
 * "Количество совместных бригад с ГКУ ОП".
 */
public class So19View extends SoView {
    @JsonSerialize(using = DotSepratedDateSerializer.class)
    private Date workDate;
    private String gkuPodrName;
    private String mgtEmployees;
    private String gkuEmployees;
    private String venue;
    private String county;
    private String routes;
    private String shiftName;
    private String brKind0;
    private String brKind11;
    private String brKind12;
    private String brKind13;
    private String brKind21;
    private String brKind22;
    private String brKind23;
    private int dopMGT;
    private int dopGKU;
    private int dopMGTnoGKU;
    private int mgtCountInDay;
    private int brCommon;
    private int mgtCountInCommonBr;
    private int gkuCountInCommonBr;

    public So19View() {
    }

    public So19View(String brKind0, String brKind11, String brKind12, String brKind13, String brKind21, String brKind22, String brKind23, int dopMGT, int dopGKU, int dopMGTnoGKU, int mgtCountInDay, int brCommon, int mgtCountInCommonBr, int gkuCountInCommonBr) {
        this.brKind0 = brKind0;
        this.brKind11 = brKind11;
        this.brKind12 = brKind12;
        this.brKind13 = brKind13;
        this.brKind21 = brKind21;
        this.brKind22 = brKind22;
        this.brKind23 = brKind23;
        this.dopMGT = dopMGT;
        this.dopGKU = dopGKU;
        this.dopMGTnoGKU = dopMGTnoGKU;
        this.mgtCountInDay = mgtCountInDay;
        this.brCommon = brCommon;
        this.mgtCountInCommonBr = mgtCountInCommonBr;
        this.gkuCountInCommonBr = gkuCountInCommonBr;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getGkuPodrName() {
        return gkuPodrName;
    }

    public void setGkuPodrName(String gkuPodrName) {
        this.gkuPodrName = gkuPodrName;
    }

    public String getMgtEmployees() {
        return mgtEmployees;
    }

    public void setMgtEmployees(String mgtEmployees) {
        this.mgtEmployees = mgtEmployees;
    }

    public String getGkuEmployees() {
        return gkuEmployees;
    }

    public void setGkuEmployees(String gkuEmployees) {
        this.gkuEmployees = gkuEmployees;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getRoutes() {
        return routes;
    }

    public void setRoutes(String routes) {
        this.routes = routes;
    }

    public String getBrKind0() {
        return brKind0;
    }

    public void setBrKind0(String brKind0, boolean isBinary) {
        this.brKind0 = isBinary ? binaryToCheckMark(brKind0) : brKind0;
    }

    public String getBrKind11() {
        return brKind11;
    }

    public void setBrKind11(String brKind11, boolean isBinary) {
        this.brKind11 = isBinary ? binaryToCheckMark(brKind11) : brKind11;
    }

    public String getBrKind12() {
        return brKind12;
    }

    public void setBrKind12(String brKind12, boolean isBinary) {
        this.brKind12 = isBinary ? binaryToCheckMark(brKind12) : brKind12;
    }

    public String getBrKind13() {
        return brKind13;
    }

    public void setBrKind13(String brKind13, boolean isBinary) {
        this.brKind13 = isBinary ? binaryToCheckMark(brKind13) : brKind13;
    }

    public String getBrKind21() {
        return brKind21;
    }

    public void setBrKind21(String brKind21, boolean isBinary) {
        this.brKind21 = isBinary ? binaryToCheckMark(brKind21) : brKind21;
    }

    public String getBrKind22() {
        return brKind22;
    }

    public void setBrKind22(String brKind22, boolean isBinary) {
        this.brKind22 = isBinary ? binaryToCheckMark(brKind22) : brKind22;
    }

    public String getBrKind23() {
        return brKind23;
    }

    public void setBrKind23(String brKind23, boolean isBinary) {
        this.brKind23 = isBinary ? binaryToCheckMark(brKind23) : brKind23;
    }

    public int getDopMGT() {
        return dopMGT;
    }

    public void setDopMGT(int dopMGT) {
        this.dopMGT = dopMGT;
    }

    public int getDopMGTnoGKU() {
        return dopMGTnoGKU;
    }

    public void setDopMGTnoGKU(int dopMGTnoGKU) {
        this.dopMGTnoGKU = dopMGTnoGKU;
    }

    public int getMgtCountInDay() {
        return mgtCountInDay;
    }

    public void setMgtCountInDay(int mgtCountInDay) {
        this.mgtCountInDay = mgtCountInDay;
    }

    public int getBrCommon() {
        return brCommon;
    }

    public void setBrCommon(int brCommon) {
        this.brCommon = brCommon;
    }

    public int getMgtCountInCommonBr() {
        return mgtCountInCommonBr;
    }

    public void setMgtCountInCommonBr(int mgtCountInCommonBr) {
        this.mgtCountInCommonBr = mgtCountInCommonBr;
    }

    public int getDopGKU() {
        return dopGKU;
    }

    public void setDopGKU(int dopGKU) {
        this.dopGKU = dopGKU;
    }

    public int getGkuCountInCommonBr() {
        return gkuCountInCommonBr;
    }

    public void setGkuCountInCommonBr(int gkuCountInCommonBr) {
        this.gkuCountInCommonBr = gkuCountInCommonBr;
    }
}