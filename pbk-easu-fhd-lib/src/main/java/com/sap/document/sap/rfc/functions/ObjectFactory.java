/*
 * Decompiled with CFR 0.143.
 */
package com.sap.document.sap.rfc.functions;

import com.sap.document.sap.rfc.functions.BALANS;
import com.sap.document.sap.rfc.functions.DEPARTS;
import com.sap.document.sap.rfc.functions.ETINTEGRPBKTC;
import com.sap.document.sap.rfc.functions.PERNRS;
import com.sap.document.sap.rfc.functions.ZPBKBAL;
import com.sap.document.sap.rfc.functions.ZPBKBALResponse;
import com.sap.document.sap.rfc.functions.ZPBKBALS;
import com.sap.document.sap.rfc.functions.ZPBKDEP;
import com.sap.document.sap.rfc.functions.ZPBKDEPResponse;
import com.sap.document.sap.rfc.functions.ZPBKDEPS;
import com.sap.document.sap.rfc.functions.ZPBKPNR;
import com.sap.document.sap.rfc.functions.ZPBKPNRResponse;
import com.sap.document.sap.rfc.functions.ZPBKPNRS;
import com.sap.document.sap.rfc.functions.ZPBKSBAL;
import com.sap.document.sap.rfc.functions.ZPBKSDEPART;
import com.sap.document.sap.rfc.functions.ZPBKSPERNR;
import com.sap.document.sap.rfc.functions.ZPMINTEGRPBKTC;
import com.sap.document.sap.rfc.functions.ZPMINTEGRPBKTCResponse;
import com.sap.document.sap.rfc.functions.ZPMINTEGRPBKTCS;
import com.sap.document.sap.rfc.functions.ZPMSINTEGRPBK;
import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    public ZPBKDEPResponse createZPBKDEPResponse() {
        return new ZPBKDEPResponse();
    }

    public ZPBKDEPS createZPBKDEPS() {
        return new ZPBKDEPS();
    }

    public ZPBKDEP createZPBKDEP() {
        return new ZPBKDEP();
    }

    public ZPBKSDEPART createZPBKSDEPART() {
        return new ZPBKSDEPART();
    }

    public DEPARTS createDEPARTS() {
        return new DEPARTS();
    }

    public ZPBKPNR createZPBKPNR() {
        return new ZPBKPNR();
    }

    public ZPBKPNRResponse createZPBKPNRResponse() {
        return new ZPBKPNRResponse();
    }

    public ZPBKPNRS createZPBKPNRS() {
        return new ZPBKPNRS();
    }

    public PERNRS createPERNRS() {
        return new PERNRS();
    }

    public ZPBKSPERNR createZPBKSPERNR() {
        return new ZPBKSPERNR();
    }

    public ZPBKBALResponse createZPBKBALResponse() {
        return new ZPBKBALResponse();
    }

    public ZPBKBALS createZPBKBALS() {
        return new ZPBKBALS();
    }

    public ZPBKBAL createZPBKBAL() {
        return new ZPBKBAL();
    }

    public BALANS createBALANS() {
        return new BALANS();
    }

    public ZPBKSBAL createZPBKSBAL() {
        return new ZPBKSBAL();
    }

    public ZPMINTEGRPBKTC createZPMINTEGRPBKTC() {
        return new ZPMINTEGRPBKTC();
    }

    public ZPMINTEGRPBKTCResponse createZPMINTEGRPBKTCResponse() {
        return new ZPMINTEGRPBKTCResponse();
    }

    public ZPMINTEGRPBKTCS createZPMINTEGRPBKTCS() {
        return new ZPMINTEGRPBKTCS();
    }

    public ETINTEGRPBKTC createETINTEGRPBKTC() {
        return new ETINTEGRPBKTC();
    }

    public ZPMSINTEGRPBK createZPMSINTEGRPBK() {
        return new ZPMSINTEGRPBK();
    }
}

