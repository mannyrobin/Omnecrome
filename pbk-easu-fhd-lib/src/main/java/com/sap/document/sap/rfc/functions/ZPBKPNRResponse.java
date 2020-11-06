/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import com.sap.document.sap.rfc.functions.ZPBKPNRS;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="", propOrder={"_return"})
@XmlRootElement(name="Z_PBK_PNRResponse")
public class ZPBKPNRResponse {
    @XmlElement(name="return", required=true)
    protected ZPBKPNRS _return;

    public ZPBKPNRS getReturn() {
        return this._return;
    }

    public void setReturn(ZPBKPNRS value) {
        this._return = value;
    }
}

