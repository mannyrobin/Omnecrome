/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Component
 *  ru.armd.pbk.enums.core.AuditType
 *  ru.armd.pbk.enums.core.VisAuditType
 */
package ru.armd.pbk.components.viss.easu.bsk;

import java.io.InputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.BaseExchangeProvider;
import ru.armd.pbk.components.viss.easu.bsk.EasuFhdBskLoader;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.Viss;
import ru.armd.pbk.utils.ImportResult;

@Component
public class EasuFhdBskProvider
extends BaseExchangeProvider {
    @Autowired
    private EasuFhdBskLoader loader;

    public EasuFhdBskProvider() {
        super(Viss.EASU_FHD, VisAuditType.VIS_EASUFHD_BSK);
    }

    @Override
    protected ImportResult<?> importStream(InputStream is) {
        return this.loader.importFile(is);
    }

    @Override
    protected boolean checkFileName(VisExchange visExchange, String name) {
        return true;
    }
}

