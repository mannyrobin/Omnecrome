/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  ru.armd.pbk.core.components.BaseComponent
 *  ru.armd.pbk.core.domain.BaseDomain
 *  ru.armd.pbk.core.repositories.IDomainRepository
 *  ru.armd.pbk.domain.core.Audit
 *  ru.armd.pbk.enums.core.AuditLevel
 *  ru.armd.pbk.enums.core.AuditObjOperation
 *  ru.armd.pbk.enums.core.AuditType
 *  ru.armd.pbk.enums.core.VisAuditType
 */
package ru.armd.pbk.components.viss.easu.converters;

import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.VisAuditType;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

public abstract class AbstractEasuConverter<Easu, Domain extends BaseDomain>
extends BaseComponent {
    protected abstract Domain convert(Easu var1);

    protected abstract Boolean merge(Domain var1, Domain var2);

    protected abstract IDomainRepository<Domain> getRepository();

    protected abstract Map<String, Object> getParams(Easu var1);

    public Domain save(Easu easu) {
        BaseDomain oldDomain = null;
        Domain newDomain = null;
        try {
            oldDomain = this.getRepository().getDomain(this.getParams(easu));
            newDomain = this.convert(easu);
            if (oldDomain != null && !this.merge(newDomain, (Domain)oldDomain).booleanValue()) {
                return (Domain)oldDomain;
            }
            return (Domain)(oldDomain == null ? this.save(newDomain) : this.save((Domain)oldDomain));
        }
        catch (Exception e) {
            this.logAudit(AuditLevel.DEBUG, VisAuditType.EXCEPTION, AuditObjOperation.OTHER, newDomain, e.getMessage(), (Throwable)e);
            return null;
        }
    }

    protected Domain save(Domain domain) {
        return (Domain)this.getRepository().save(domain);
    }

    protected String getStringValue(JAXBElement<String> value) {
        return value == null ? null : value.getValue();
    }

    protected Date getDateValue(JAXBElement<XMLGregorianCalendar> value) {
        if (value != null) {
            return value.getValue().toGregorianCalendar().getTime();
        }
        return null;
    }

    protected Date getDateValue(XMLGregorianCalendar value) {
        if (value != null) {
            return value.toGregorianCalendar().getTime();
        }
        return null;
    }

    protected Integer getIntValue(JAXBElement<BigInteger> value) {
        if (value != null) {
            return value.getValue().intValue();
        }
        return null;
    }
}

