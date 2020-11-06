/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import com.sap.document.sap.rfc.functions.PERNRS;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="ZPBK_PNRS", propOrder={"pernrs"})
public class ZPBKPNRS {
    @XmlElement(name="PERNRS", required=true)
    protected PERNRS pernrs;

    public PERNRS getPERNRS() {
        return this.pernrs;
    }

    public void setPERNRS(PERNRS value) {
        this.pernrs = value;
    }
}

