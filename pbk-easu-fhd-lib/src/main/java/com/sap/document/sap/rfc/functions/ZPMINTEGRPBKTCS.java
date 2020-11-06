/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import com.sap.document.sap.rfc.functions.ETINTEGRPBKTC;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="ZPMINTEGR_PBKTCS", propOrder={"etintegrpbktc"})
public class ZPMINTEGRPBKTCS {
    @XmlElement(name="ET_INTEGR_PBK_TC", required=true)
    protected ETINTEGRPBKTC etintegrpbktc;

    public ETINTEGRPBKTC getETINTEGRPBKTC() {
        return this.etintegrpbktc;
    }

    public void setETINTEGRPBKTC(ETINTEGRPBKTC value) {
        this.etintegrpbktc = value;
    }
}

