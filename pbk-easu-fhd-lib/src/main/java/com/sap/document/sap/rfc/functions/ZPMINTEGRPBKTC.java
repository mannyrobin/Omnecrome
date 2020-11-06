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
@XmlRootElement(name="ZPM_INTEGR_PBK_TC")
public class ZPMINTEGRPBKTC {
    @XmlElement(name="IV_DATE_H", required=true)
    protected String ivdateh;
    @XmlElement(name="IV_DATE_L", required=true)
    protected String ivdatel;

    public String getIVDATEH() {
        return this.ivdateh;
    }

    public void setIVDATEH(String value) {
        this.ivdateh = value;
    }

    public String getIVDATEL() {
        return this.ivdatel;
    }

    public void setIVDATEL(String value) {
        this.ivdatel = value;
    }
}

