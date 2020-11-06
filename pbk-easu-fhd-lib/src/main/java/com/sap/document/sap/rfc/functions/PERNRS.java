/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import com.sap.document.sap.rfc.functions.ZPBKSPERNR;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="PERNRS", propOrder={"item"})
public class PERNRS {
    @XmlElement(required=true)
    protected List<ZPBKSPERNR> item;

    public List<ZPBKSPERNR> getItem() {
        if (this.item == null) {
            this.item = new ArrayList<ZPBKSPERNR>();
        }
        return this.item;
    }
}

