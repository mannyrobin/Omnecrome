/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  armd.lightHttp.client.IHttpPacketProcessor
 *  armd.lightSoap.client.BaseSoapClient
 *  armd.lightSoap.client.SoapClientException
 *  com.sap.document.sap.rfc.functions.ZPBKDEP
 *  com.sap.document.sap.rfc.functions.ZPBKDEPResponse
 *  org.apache.log4j.Logger
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Component
 *  ru.armd.pbk.enums.core.AuditType
 *  ru.armd.pbk.enums.core.VisAuditType
 */
package ru.armd.pbk.components.viss.easu.department;

import armd.lightHttp.client.IHttpPacketProcessor;
import armd.lightSoap.client.BaseSoapClient;
import armd.lightSoap.client.SoapClientException;
import com.sap.document.sap.rfc.functions.ZPBKDEP;
import com.sap.document.sap.rfc.functions.ZPBKDEPResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.components.viss.easu.EasuFhdLoader;
import ru.armd.pbk.components.viss.easu.EasuFhdSoapPacketProcessor;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.Viss;

@Component
public class EasuFhdDepartmentProvider
extends BaseExchangeProvider {
    public static final Logger LOGGER = Logger.getLogger(EasuFhdDepartmentProvider.class);
    @Autowired
    private EasuFhdLoader loader;

    public EasuFhdDepartmentProvider() {
        super(Viss.EASU_FHD, VisAuditType.VIS_EASUFHD_DEPARTMENT);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected void importSoap(VisExchange visExchange, BaseSoapClient client) throws SoapClientException {
        client.setPacketProcessor((IHttpPacketProcessor)new EasuFhdSoapPacketProcessor("com.sap.document.sap.rfc.functions", "com.sap.document.sap.rfc.functions"));
        ZPBKDEP reqDep = new ZPBKDEP();
        this.loader.parseObject(client.callSoap((Object)reqDep, "getDepartments", ZPBKDEPResponse.class), visExchange.getWorkDate());
    }
}

