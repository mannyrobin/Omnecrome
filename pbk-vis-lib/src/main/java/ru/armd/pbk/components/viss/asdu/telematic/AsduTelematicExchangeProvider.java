/*
 * Decompiled with CFR 0.143.
 * 
 * Could not load the following classes:
 *  armd.lightHttp.client.BaseHttpClientParameters
 *  armd.lightRest.client.BaseRestClient
 *  org.apache.log4j.Logger
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Component
 *  ru.armd.pbk.domain.nsi.Telematics
 *  ru.armd.pbk.enums.core.AuditType
 *  ru.armd.pbk.enums.core.VisAuditType
 */
package ru.armd.pbk.components.viss.asdu.telematic;

import armd.lightHttp.client.BaseHttpClientParameters;
import armd.lightRest.client.BaseRestClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.components.viss.core.clients.DefaultRestClient;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.Viss;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;
import java.text.SimpleDateFormat;

@Component
public class AsduTelematicExchangeProvider
extends BaseExchangeProvider {
    public static final Logger LOGGER = Logger.getLogger(AsduTelematicExchangeProvider.class);
    @Autowired
    private AsduTelematicLoader loader;

    public AsduTelematicExchangeProvider() {
        super(Viss.ASDU, VisAuditType.VIS_ASDU_TELEMATIC);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected ImportResult<?> importStream(VisExchange visExchange, InputStream is) {
        return this.loader.importFile(is, visExchange.getWorkDate());
    }

    @Override
    protected boolean processAsZip(VisExchange visExchange, BaseRestClient client) {
        return true;
    }

    @Override
    protected boolean checkFileName(VisExchange visExchange, String name) {
        return true;
    }

    @Override
    protected DefaultRestClient createRestClient(VisExchange visExchange, BaseHttpClientParameters parameters) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        parameters.setServiceAddress(parameters.getServiceAddress().replace("{date}", df.format(visExchange.getWorkDate())));
        return new DefaultRestClient(parameters);
    }
}

