/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="ZPBK_S_DEPART", propOrder={"objid", "_short", "stext", "ohead"})
public class ZPBKSDEPART {
    @XmlElement(name="OBJID", required=true)
    protected String objid;
    @XmlElement(name="SHORT", required=true)
    protected String _short;
    @XmlElement(name="STEXT", required=true)
    protected String stext;
    @XmlElement(name="OHEAD", required=true)
    protected String ohead;

    public String getOBJID() {
        return this.objid;
    }

    public void setOBJID(String value) {
        this.objid = value;
    }

    public String getSHORT() {
        return this._short;
    }

    public void setSHORT(String value) {
        this._short = value;
    }

    public String getSTEXT() {
        return this.stext;
    }

    public void setSTEXT(String value) {
        this.stext = value;
    }

    public String getOHEAD() {
        return this.ohead;
    }

    public void setOHEAD(String value) {
        this.ohead = value;
    }
}

