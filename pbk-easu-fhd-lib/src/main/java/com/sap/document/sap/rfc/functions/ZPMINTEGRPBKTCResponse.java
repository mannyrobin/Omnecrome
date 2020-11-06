/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import com.sap.document.sap.rfc.functions.ZPMINTEGRPBKTCS;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="", propOrder={"_return"})
@XmlRootElement(name="ZPM_INTEGR_PBK_TCResponse")
public class ZPMINTEGRPBKTCResponse {
    @XmlElement(name="return", required=true)
    protected ZPMINTEGRPBKTCS _return;

    public ZPMINTEGRPBKTCS getReturn() {
        return this._return;
    }

    public void setReturn(ZPMINTEGRPBKTCS value) {
        this._return = value;
    }
}

