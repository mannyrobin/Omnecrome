/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="ZPBK_S_BAL", propOrder={"pernr", "datum", "stdaz", "sobeg", "soend"})
public class ZPBKSBAL {
    @XmlElement(name="PERNR")
    protected String pernr;
    @XmlElement(name="DATUM")
    protected String datum;
    @XmlElement(name="STDAZ")
    protected BigDecimal stdaz;
    @XmlElement(name="SOBEG")
    protected String sobeg;
    @XmlElement(name="SOEND")
    protected String soend;

    public String getPERNR() {
        return this.pernr;
    }

    public void setPERNR(String value) {
        this.pernr = value;
    }

    public String getDATUM() {
        return this.datum;
    }

    public void setDATUM(String value) {
        this.datum = value;
    }

    public BigDecimal getSTDAZ() {
        return this.stdaz;
    }

    public void setSTDAZ(BigDecimal value) {
        this.stdaz = value;
    }

    public String getSOBEG() {
        return this.sobeg;
    }

    public void setSOBEG(String value) {
        this.sobeg = value;
    }

    public String getSOEND() {
        return this.soend;
    }

    public void setSOEND(String value) {
        this.soend = value;
    }
}

