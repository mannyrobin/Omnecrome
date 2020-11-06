/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import com.sap.document.sap.rfc.functions.ZPBKSDEPART;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value=XmlAccessType.FIELD)
@XmlType(name="DEPARTS", propOrder={"item"})
public class DEPARTS {
    @XmlElement(required=true)
    protected List<ZPBKSDEPART> item;

    public List<ZPBKSDEPART> getItem() {
        if (this.item == null) {
            this.item = new ArrayList<ZPBKSDEPART>();
        }
        return this.item;
    }
}

