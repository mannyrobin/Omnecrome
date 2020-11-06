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
 *  ru.armd.pbk.domain.nsi.ContactlessCard
 *  ru.armd.pbk.domain.nsi.employee.Employee
 *  ru.armd.pbk.enums.core.AuditType
 *  ru.armd.pbk.enums.core.VisAuditType
 *  ru.armd.pbk.repositories.nsi.ContactlessCardRepository
 *  ru.armd.pbk.repositories.nsi.employee.EmployeeRepository
 */
package ru.armd.pbk.components.viss.easu.bsk;

import com.aspose.cells.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseXlsxLoader;
import ru.armd.pbk.domain.nsi.ContactlessCard;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.nsi.ContactlessCardRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class EasuFhdBskLoader
extends BaseXlsxLoader<ContactlessCard> {
    @Autowired
    private EmployeeRepository employeeRepository;
    private ContactlessCardRepository repository;

    @Autowired
    public EasuFhdBskLoader(ContactlessCardRepository domainRepository) {
        super(domainRepository, VisAuditType.VIS_EASUFHD_BSK);
        this.repository = domainRepository;
    }

    @Override
    protected ContactlessCard doProcessElement(Row row) {
        Employee employee;
        ContactlessCard domain = super.doProcessElement(row);
        if (domain != null && (employee = this.employeeRepository.getDomain(this.getEmployeeParams(row))) != null) {
            employee.setContCardId(domain.getHeadId());
            employee.setLicenceDetails(domain.getCardNumber());
            this.employeeRepository.save(employee);
        }
        return domain;
    }

    @Override
    protected ContactlessCard createDomain(Row row) {
        if (row.getCellByIndex(3).getStringValue().isEmpty()) {
            return null;
        }
        ContactlessCard domain = new ContactlessCard();
        domain.setCardNumber(row.getCellByIndex(3).getStringValue());
        return domain;
    }

    @Override
    protected ContactlessCard getExistedDomain(ContactlessCard newDomain) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("cardNumber", newDomain.getCardNumber());
        return this.repository.getDomain(params);
    }

    @Override
    protected void updateDomain(ContactlessCard newDomain, ContactlessCard existedDomain) {
        newDomain.setHeadId(existedDomain.getHeadId());
    }

    protected Map<String, Object> getEmployeeParams(Row row) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("personalNumber", row.getCellByIndex(2).getStringValue());
        return params;
    }
}

