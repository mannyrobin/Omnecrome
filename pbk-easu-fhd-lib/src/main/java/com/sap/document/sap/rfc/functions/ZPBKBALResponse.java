/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import javax.xml.bind.annotation.*;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="", propOrder={"_return"})
@XmlRootElement(name="Z_PBK_BALResponse")
public class ZPBKBALResponse {
    @XmlElement(name="return", required=true)
    protected ZPBKBALS _return;

    public ZPBKBALS getReturn() {
        return this._return;
    }

    public void setReturn(ZPBKBALS value) {
        this._return = value;
    }
}

