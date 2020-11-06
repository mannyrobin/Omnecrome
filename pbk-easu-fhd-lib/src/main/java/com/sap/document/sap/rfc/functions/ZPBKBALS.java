/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import com.sap.document.sap.rfc.functions.BALANS;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="ZPBK_BALS", propOrder={"balans"})
public class ZPBKBALS {
    @XmlElement(name="BALANS", required=true)
    protected BALANS balans;

    public BALANS getBALANS() {
        return this.balans;
    }

    public void setBALANS(BALANS value) {
        this.balans = value;
    }
}

