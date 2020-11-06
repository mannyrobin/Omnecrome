/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  armd.lightHttp.client.IHttpPacketProcessor
 *  armd.lightSoap.client.BaseSoapClient
 *  armd.lightSoap.client.SoapClientException
 *  com.sap.document.sap.rfc.functions.ZPBKPNR
 *  com.sap.document.sap.rfc.functions.ZPBKPNRResponse
 *  org.apache.log4j.Logger
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Component
 *  ru.armd.pbk.enums.core.AuditType
 *  ru.armd.pbk.enums.core.VisAuditType
 *  ru.armd.pbk.repositories.nsi.department.DepartmentRepository
 */
package ru.armd.pbk.components.viss.easu.employee;

import armd.lightHttp.client.IHttpPacketProcessor;
import armd.lightSoap.client.BaseSoapClient;
import armd.lightSoap.client.SoapClientException;
import com.sap.document.sap.rfc.functions.ZPBKPNR;
import com.sap.document.sap.rfc.functions.ZPBKPNRResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.components.viss.easu.EasuFhdLoader;
import ru.armd.pbk.components.viss.easu.EasuFhdSoapPacketProcessor;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.Viss;
import ru.armd.pbk.repositories.nsi.department.DepartmentRepository;

import java.text.SimpleDateFormat;

@Component
public class EasuFhdEmployeeProvider
extends BaseExchangeProvider {
    public static final Logger LOGGER = Logger.getLogger(EasuFhdEmployeeProvider.class);
    @Autowired
    private EasuFhdLoader loader;
    @Autowired
    private DepartmentRepository departmentRepository;

    public EasuFhdEmployeeProvider() {
        super(Viss.EASU_FHD, VisAuditType.VIS_EASUFHD_EMPLOYEE);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected void importSoap(VisExchange visExchange, BaseSoapClient client) throws SoapClientException {
        client.setPacketProcessor((IHttpPacketProcessor)new EasuFhdSoapPacketProcessor("com.sap.document.sap.rfc.functions", "com.sap.document.sap.rfc.functions"));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ZPBKPNR reqEmp = new ZPBKPNR();
        reqEmp.setORGEH(visExchange.getParameter());
        reqEmp.setBEGDA(df.format(visExchange.getWorkDate()));
        reqEmp.setENDDA(df.format(visExchange.getWorkDate()));
        this.loader.parseObject(client.callSoap((Object)reqEmp, "getEmployees", ZPBKPNRResponse.class), visExchange.getWorkDate());
    }
}

