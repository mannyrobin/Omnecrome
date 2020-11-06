/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="ZPBK_S_PERNR", propOrder={"pnmgt", "pernr", "nachn", "vorna", "midnm", "orgeh", "pltxt", "begda", "endda", "obegd", "oendd", "awart", "atext"})
public class ZPBKSPERNR {
    @XmlElement(name="PNMGT")
    protected String pnmgt;
    @XmlElement(name="PERNR")
    protected String pernr;
    @XmlElement(name="NACHN")
    protected String nachn;
    @XmlElement(name="VORNA")
    protected String vorna;
    @XmlElement(name="MIDNM")
    protected String midnm;
    @XmlElement(name="ORGEH")
    protected String orgeh;
    @XmlElement(name="PLTXT")
    protected String pltxt;
    @XmlElement(name="BEGDA")
    protected String begda;
    @XmlElement(name="ENDDA")
    protected String endda;
    @XmlElement(name="OBEGD")
    protected String obegd;
    @XmlElement(name="OENDD")
    protected String oendd;
    @XmlElement(name="AWART")
    protected String awart;
    @XmlElement(name="ATEXT")
    protected String atext;

    public String getPNMGT() {
        return this.pnmgt;
    }

    public void setPNMGT(String value) {
        this.pnmgt = value;
    }

    public String getPERNR() {
        return this.pernr;
    }

    public void setPERNR(String value) {
        this.pernr = value;
    }

    public String getNACHN() {
        return this.nachn;
    }

    public void setNACHN(String value) {
        this.nachn = value;
    }

    public String getVORNA() {
        return this.vorna;
    }

    public void setVORNA(String value) {
        this.vorna = value;
    }

    public String getMIDNM() {
        return this.midnm;
    }

    public void setMIDNM(String value) {
        this.midnm = value;
    }

    public String getORGEH() {
        return this.orgeh;
    }

    public void setORGEH(String value) {
        this.orgeh = value;
    }

    public String getPLTXT() {
        return this.pltxt;
    }

    public void setPLTXT(String value) {
        this.pltxt = value;
    }

    public String getBEGDA() {
        return this.begda;
    }

    public void setBEGDA(String value) {
        this.begda = value;
    }

    public String getENDDA() {
        return this.endda;
    }

    public void setENDDA(String value) {
        this.endda = value;
    }

    public String getOBEGD() {
        return this.obegd;
    }

    public void setOBEGD(String value) {
        this.obegd = value;
    }

    public String getOENDD() {
        return this.oendd;
    }

    public void setOENDD(String value) {
        this.oendd = value;
    }

    public String getAWART() {
        return this.awart;
    }

    public void setAWART(String value) {
        this.awart = value;
    }

    public String getATEXT() {
        return this.atext;
    }

    public void setATEXT(String value) {
        this.atext = value;
    }
}

