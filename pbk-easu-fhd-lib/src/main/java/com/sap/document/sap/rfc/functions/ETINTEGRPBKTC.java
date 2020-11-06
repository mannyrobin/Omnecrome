/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import com.sap.document.sap.rfc.functions.ZPMSINTEGRPBK;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="ET_INTEGR_PBK_TC", propOrder={"item"})
public class ETINTEGRPBKTC {
    @XmlElement(required=true)
    protected List<ZPMSINTEGRPBK> item;

    public List<ZPMSINTEGRPBK> getItem() {
        if (this.item == null) {
            this.item = new ArrayList<ZPMSINTEGRPBK>();
        }
        return this.item;
    }
}

