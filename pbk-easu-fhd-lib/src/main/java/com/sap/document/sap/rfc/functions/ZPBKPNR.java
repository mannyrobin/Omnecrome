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
@XmlRootElement(name="Z_PBK_PNR")
public class ZPBKPNR {
    @XmlElement(name="BEGDA", required=true)
    protected String begda;
    @XmlElement(name="ENDDA", required=true)
    protected String endda;
    @XmlElement(name="ORGEH", required=true)
    protected String orgeh;

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

    public String getORGEH() {
        return this.orgeh;
    }

    public void setORGEH(String value) {
        this.orgeh = value;
    }
}

