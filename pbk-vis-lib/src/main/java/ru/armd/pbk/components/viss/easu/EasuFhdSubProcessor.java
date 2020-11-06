/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  org.apache.log4j.Logger
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.scheduling.annotation.Async
 *  org.springframework.stereotype.Component
 *  ru.armd.pbk.domain.core.Audit
 *  ru.armd.pbk.enums.core.AuditLevel
 *  ru.armd.pbk.enums.core.AuditObjOperation
 *  ru.armd.pbk.enums.core.AuditType
 *  ru.armd.pbk.enums.core.VisAuditType
 */
package ru.armd.pbk.components.viss.easu;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.BaseExchangeProcessor;
import ru.armd.pbk.components.viss.easu.employee.EasuFhdEmployeeProvider;
import ru.armd.pbk.components.viss.easu.workmode.EasuFhdWorkModeProvider;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.enums.viss.VisExchangeOperations;

import java.util.Date;

@Component
public class EasuFhdSubProcessor
extends BaseExchangeProcessor {
    public static final Logger LOGGER = Logger.getLogger(EasuFhdProcessor.class);
    @Autowired
    private EasuFhdEmployeeProvider easuFhdEmployeeProvider;
    @Autowired
    private EasuFhdWorkModeProvider easuFhdWorkModeProvider;

    public EasuFhdSubProcessor() {
        super(VisAuditType.VIS_EASUFHD);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Async(value="easufhdExecutor")
    public void doImport(VisExchangeObjects visExchangeObject, String parameter, Date start, Date end, boolean force) {
        this.logAudit(AuditLevel.DEBUG, VisAuditType.VIS_EASUFHD, AuditObjOperation.IMPORT, null, Thread.currentThread().getName() + "doImport: visExchangeObject = " + visExchangeObject.getId(), null);
        this.doProcess(visExchangeObject, VisExchangeOperations.IMPORT, parameter, start, end, force);
    }

    @Override
    protected void doExchange(VisExchangeConfig visExchangeConfig, String parameter, Date workDate, boolean force) {
        if (VisExchangeObjects.EASU_FHD_EMPLOYEE.getId().equals(visExchangeConfig.getExchangeObjectId())) {
            this.easuFhdEmployeeProvider.doExchange(visExchangeConfig, parameter, workDate, force);
        } else if (VisExchangeObjects.EASU_FHD_WORK_MODE.getId().equals(visExchangeConfig.getExchangeObjectId())) {
            this.easuFhdWorkModeProvider.doExchange(visExchangeConfig, parameter, workDate, force);
        }
    }
}

