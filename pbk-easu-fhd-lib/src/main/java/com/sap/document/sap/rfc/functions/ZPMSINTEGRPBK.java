/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="ZPMS_INTEGR_PBK", propOrder={"eqfnr", "vidtc", "vmest", "maxoccup", "parentclass", "_class", "databegin", "dataend"})
public class ZPMSINTEGRPBK {
    @XmlElement(name="EQFNR")
    protected String eqfnr;
    @XmlElement(name="VIDTC")
    protected String vidtc;
    @XmlElement(name="VMEST")
    protected String vmest;
    @XmlElement(name="MAXOCCUP")
    protected String maxoccup;
    @XmlElement(name="PARENT_CLASS")
    protected String parentclass;
    @XmlElement(name="CLASS")
    protected String _class;
    @XmlElement(name="DATABEGIN")
    protected String databegin;
    @XmlElement(name="DATAEND")
    protected String dataend;

    public String getEQFNR() {
        return this.eqfnr;
    }

    public void setEQFNR(String value) {
        this.eqfnr = value;
    }

    public String getVIDTC() {
        return this.vidtc;
    }

    public void setVIDTC(String value) {
        this.vidtc = value;
    }

    public String getVMEST() {
        return this.vmest;
    }

    public void setVMEST(String value) {
        this.vmest = value;
    }

    public String getMAXOCCUP() {
        return this.maxoccup;
    }

    public void setMAXOCCUP(String value) {
        this.maxoccup = value;
    }

    public String getPARENTCLASS() {
        return this.parentclass;
    }

    public void setPARENTCLASS(String value) {
        this.parentclass = value;
    }

    public String getCLASS() {
        return this._class;
    }

    public void setCLASS(String value) {
        this._class = value;
    }

    public String getDATABEGIN() {
        return this.databegin;
    }

    public void setDATABEGIN(String value) {
        this.databegin = value;
    }

    public String getDATAEND() {
        return this.dataend;
    }

    public void setDATAEND(String value) {
        this.dataend = value;
    }
}

