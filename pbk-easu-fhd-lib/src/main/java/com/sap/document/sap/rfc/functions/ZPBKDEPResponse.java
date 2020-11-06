/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import com.sap.document.sap.rfc.functions.ZPBKDEPS;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="", propOrder={"_return"})
@XmlRootElement(name="Z_PBK_DEPResponse")
public class ZPBKDEPResponse {
    @XmlElement(name="return", required=true)
    protected ZPBKDEPS _return;

    public ZPBKDEPS getReturn() {
        return this._return;
    }

    public void setReturn(ZPBKDEPS value) {
        this._return = value;
    }
}

