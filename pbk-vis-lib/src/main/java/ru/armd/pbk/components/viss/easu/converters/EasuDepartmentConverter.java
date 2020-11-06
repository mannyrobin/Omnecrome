/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  com.sap.document.sap.rfc.functions.ZPBKSDEPART
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Component
 *  ru.armd.pbk.core.domain.BaseDomain
 *  ru.armd.pbk.core.repositories.IDomainRepository
 *  ru.armd.pbk.domain.nsi.department.Department
 *  ru.armd.pbk.repositories.nsi.department.DepartmentRepository
 */
package ru.armd.pbk.components.viss.easu.converters;

import com.sap.document.sap.rfc.functions.ZPBKSDEPART;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.nsi.department.Department;
import ru.armd.pbk.repositories.nsi.department.DepartmentRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class EasuDepartmentConverter
extends AbstractEasuConverter<ZPBKSDEPART, Department> {
    @Autowired
    private DepartmentRepository repository;

    @Override
    protected Department convert(ZPBKSDEPART easu) {
        Department domain = new Department();
        domain.setEasuFhdId(easu.getOBJID());
        domain.setName(easu.getSHORT().trim());
        domain.setFullName(easu.getSHORT().trim());
        domain.setDescription(easu.getSTEXT());
        Department parent = (Department)this.repository.getDomain(this.getParentParams(easu));
        if (parent != null) {
            domain.setParentDeptId(parent.getHeadId());
        }
        return domain;
    }

    @Override
    protected Boolean merge(Department from, Department to) {
        Boolean result = false;
        if (!from.getName().equals(to.getName())) {
            result = true;
            to.setName(from.getName());
        }
        if (!from.getFullName().equals(to.getFullName())) {
            result = true;
            to.setFullName(from.getFullName());
        }
        if (!from.getEasuFhdId().equals(to.getEasuFhdId())) {
            result = true;
            to.setEasuFhdId(from.getEasuFhdId());
        }
        if (from.getParentDeptId() != null && !from.getParentDeptId().equals(to.getParentDeptId())) {
            result = true;
            to.setParentDeptId(from.getParentDeptId());
        }
        return result;
    }

    @Override
    protected IDomainRepository<Department> getRepository() {
        return this.repository;
    }

    @Override
    protected Map<String, Object> getParams(ZPBKSDEPART easu) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("easuFhdId", easu.getOBJID());
        return params;
    }

    protected Map<String, Object> getParentParams(ZPBKSDEPART easu) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("easuFhdId", easu.getOHEAD());
        return params;
    }
}

