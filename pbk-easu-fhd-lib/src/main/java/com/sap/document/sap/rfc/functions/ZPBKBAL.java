/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="", propOrder={})
@XmlRootElement(name="Z_PBK_BAL")
public class ZPBKBAL {
    @XmlElement(name="FEATD", required=true)
    protected String featd;
    @XmlElement(name="PERNR", required=true)
    protected String pernr;

    public String getFEATD() {
        return this.featd;
    }

    public void setFEATD(String value) {
        this.featd = value;
    }

    public String getPERNR() {
        return this.pernr;
    }

    public void setPERNR(String value) {
        this.pernr = value;
    }
}

