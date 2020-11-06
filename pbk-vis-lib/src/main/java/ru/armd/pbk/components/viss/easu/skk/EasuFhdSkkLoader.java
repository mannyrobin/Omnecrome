/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  com.aspose.cells.Cell
 *  com.aspose.cells.Row
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Component
 *  ru.armd.pbk.core.domain.BaseDomain
 *  ru.armd.pbk.core.repositories.IDomainRepository
 *  ru.armd.pbk.domain.nsi.OfficialCard
 *  ru.armd.pbk.domain.nsi.employee.Employee
 *  ru.armd.pbk.enums.core.AuditType
 *  ru.armd.pbk.enums.core.VisAuditType
 *  ru.armd.pbk.repositories.nsi.OfficialCardRepository
 *  ru.armd.pbk.repositories.nsi.employee.EmployeeRepository
 */
package ru.armd.pbk.components.viss.easu.skk;

import com.aspose.cells.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseXlsxLoader;
import ru.armd.pbk.domain.nsi.OfficialCard;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.nsi.OfficialCardRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class EasuFhdSkkLoader
extends BaseXlsxLoader<OfficialCard> {
    @Autowired
    private EmployeeRepository employeeRepository;
    private OfficialCardRepository repository;

    @Autowired
    public EasuFhdSkkLoader(OfficialCardRepository repository) {
        super(repository, VisAuditType.VIS_EASUFHD_SKK);
        this.repository = repository;
    }

    @Override
    protected OfficialCard doProcessElement(Row row) {
        Employee employee;
        OfficialCard domain = super.doProcessElement(row);
        if (domain != null && (employee = this.employeeRepository.getDomain(this.getEmployeeParams(row))) != null) {
            employee.setOffCardId(domain.getHeadId());
            this.employeeRepository.save(employee);
        }
        return domain;
    }

    @Override
    protected OfficialCard createDomain(Row row) {
        if (row.getCellByIndex(1).getStringValue().isEmpty()) {
            return null;
        }
        OfficialCard domain = new OfficialCard();
        domain.setCardNumber(row.getCellByIndex(1).getStringValue());
        return domain;
    }

    @Override
    protected OfficialCard getExistedDomain(OfficialCard newDomain) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("cardNumber", newDomain.getCardNumber());
        return this.repository.getDomain(params);
    }

    @Override
    protected void updateDomain(OfficialCard newDomain, OfficialCard existedDomain) {
        newDomain.setHeadId(existedDomain.getHeadId());
    }

    protected Map<String, Object> getEmployeeParams(Row row) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("personalNumber", row.getCellByIndex(0).getStringValue());
        return params;
    }
}

