/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import com.sap.document.sap.rfc.functions.DEPARTS;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="ZPBK_DEPS", propOrder={"departs"})
public class ZPBKDEPS {
    @XmlElement(name="DEPARTS", required=true)
    protected DEPARTS departs;

    public DEPARTS getDEPARTS() {
        return this.departs;
    }

    public void setDEPARTS(DEPARTS value) {
        this.departs = value;
    }
}

