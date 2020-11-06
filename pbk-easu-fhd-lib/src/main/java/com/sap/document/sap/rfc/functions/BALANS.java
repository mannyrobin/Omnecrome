/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import com.sap.document.sap.rfc.functions.ZPBKSBAL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="BALANS", propOrder={"item"})
public class BALANS {
    @XmlElement(required=true)
    protected List<ZPBKSBAL> item;

    public List<ZPBKSBAL> getItem() {
        if (this.item == null) {
            this.item = new ArrayList<ZPBKSBAL>();
        }
        return this.item;
    }
}

