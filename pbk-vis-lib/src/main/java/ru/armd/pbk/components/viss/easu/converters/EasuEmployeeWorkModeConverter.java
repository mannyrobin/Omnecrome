/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  com.sap.document.sap.rfc.functions.ZPBKSBAL
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Component
 *  ru.armd.pbk.core.domain.BaseDomain
 *  ru.armd.pbk.core.repositories.IDomainRepository
 *  ru.armd.pbk.domain.nsi.employee.Employee
 *  ru.armd.pbk.domain.nsi.employee.EmployeeWorkMode
 *  ru.armd.pbk.enums.nsi.WorkModeType
 *  ru.armd.pbk.mappers.nsi.employee.EmployeeAbsenceMapper
 *  ru.armd.pbk.repositories.nsi.employee.EmployeeRepository
 *  ru.armd.pbk.repositories.nsi.employee.EmployeeWorkModeRepository
 *  ru.armd.pbk.services.plans.PlansService
 */
package ru.armd.pbk.components.viss.easu.converters;

import com.sap.document.sap.rfc.functions.ZPBKSBAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.domain.nsi.employee.EmployeeWorkMode;
import ru.armd.pbk.enums.nsi.WorkModeType;
import ru.armd.pbk.mappers.nsi.employee.EmployeeAbsenceMapper;
import ru.armd.pbk.repositories.nsi.employee.EmployeeRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeWorkModeRepository;
import ru.armd.pbk.services.plans.PlansService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class EasuEmployeeWorkModeConverter
extends AbstractEasuConverter<ZPBKSBAL, EmployeeWorkMode> {
    private static final String START_HOURS = "00:00";
    private static final String END_HOURS = "24:00";
    @Autowired
    private EmployeeWorkModeRepository repository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PlansService plansService;
    @Autowired
    private EmployeeAbsenceMapper employeeAbsenceMapper;

    @Override
    protected EmployeeWorkMode convert(ZPBKSBAL easu) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            EmployeeWorkMode domain = new EmployeeWorkMode();
            domain.setEmployeeId((this.employeeRepository.getDomain(this.getEmployeeParams(easu))).getHeadId());
            domain.setWorkDate(dateFormat.parse(easu.getDATUM()));
            domain.setWorkPlanHours(easu.getSTDAZ());
            domain.setWorkModeId(this.getWorkModeByTime(easu));
            domain.setIsChange(Boolean.valueOf(false));
            return domain;
        }
        catch (ParseException e) {
            return null;
        }
    }

    protected EmployeeWorkMode convert(Long employeeId, ZPBKSBAL easu) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            EmployeeWorkMode domain = new EmployeeWorkMode();
            domain.setEmployeeId(employeeId);
            domain.setWorkDate(dateFormat.parse(easu.getDATUM()));
            domain.setWorkPlanHours(easu.getSTDAZ());
            domain.setWorkModeId(this.getWorkModeByTime(easu));
            domain.setIsChange(Boolean.valueOf(false));
            return domain;
        }
        catch (ParseException e) {
            return null;
        }
    }

    public void save(List<ZPBKSBAL> easu, Date workDate) {
        if (easu.isEmpty()) {
            return;
        }
        List<EmployeeWorkMode> oldWorkModes;
        List<EmployeeWorkMode> newWorkModes = new ArrayList<EmployeeWorkMode>();
        Long employeeId = employeeRepository.getDomain(getEmployeeParams(easu.get(0))).getHeadId();
        EmployeeWorkMode current = null;
        EmployeeWorkMode last = null;
        ZPBKSBAL lastEasu = null;
        for (ZPBKSBAL e : easu) {
            current = convert(employeeId, e);
            if (last != null && current.getWorkModeId().equals(WorkModeType.HOLIDAY.getId()) && isNight(lastEasu)) {
                last.setWorkPlanHours(last.getWorkPlanHours().add(current.getWorkPlanHours()));
            }
            if (current.getWorkModeId().equals(WorkModeType.HOLIDAY.getId())) {
                current.setWorkPlanHours(BigDecimal.ZERO);
            }
            newWorkModes.add(current);
            last = current;
            lastEasu = e;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("employeeId", employeeId);
        params.put("dateFrom", easu.get(0).getDATUM());
        params.put("dateTo", easu.get(easu.size() - 1).getDATUM());
        oldWorkModes = repository.getDomains(params);
        Boolean isNewEmployee = false;
        for (EmployeeWorkMode nwm : newWorkModes) {
            Boolean find = false;
            for (EmployeeWorkMode owm : oldWorkModes) {
                if (nwm.getWorkDate().equals(owm.getWorkDate())) {
                    find = true;
                    if (merge(nwm, owm)) {
                        save(owm, workDate);
                    }
                    break;
                }
            }
            if (!find) {
                save(nwm, workDate);
                isNewEmployee = true;
            }
        }
        if (isNewEmployee) {
            plansService.processShiftModes(easu.get(0).getPERNR());
        }
    }

    @Override
    protected Boolean merge(EmployeeWorkMode from, EmployeeWorkMode to) {
        Boolean result = false;
        if (!from.getEmployeeId().equals(to.getEmployeeId())) {
            result = true;
            to.setEmployeeId(from.getEmployeeId());
        }
        if (!from.getWorkDate().equals(to.getWorkDate())) {
            result = true;
            to.setWorkDate(from.getWorkDate());
        }
        if (from.getWorkPlanHours().compareTo(to.getWorkPlanHours()) != 0) {
            result = true;
            to.setWorkPlanHours(from.getWorkPlanHours());
        }
        if (!from.getWorkModeId().equals(to.getWorkModeId())) {
            result = true;
            to.setWorkModeId(from.getWorkModeId());
        }
        if (!from.getIsChange().equals(to.getIsChange())) {
            result = true;
            to.setIsChange(from.getIsChange());
        }
        return result;
    }

    protected EmployeeWorkMode save(EmployeeWorkMode newDomain, Date workDate) {
        checkControllerGraph(newDomain, workDate);
        return save(newDomain);
    }

    private void checkControllerGraph(EmployeeWorkMode ewm, Date workDate) {
        plansService.checkControllerGraph(ewm.getEmployeeId(), ewm.getWorkDate(),
                ewm.getWorkModeId(), ewm.getWorkPlanHours(), workDate, false);
    }

    public void postProcess() {
        plansService.processShiftModes();
    }

    @Override
    protected IDomainRepository<EmployeeWorkMode> getRepository() {
        return this.repository;
    }

    @Override
    protected Map<String, Object> getParams(ZPBKSBAL easu) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("employeeId", ((Employee)this.employeeRepository.getDomain(this.getEmployeeParams(easu))).getHeadId());
            params.put("workDate", dateFormat.parse(easu.getDATUM()));
            return params;
        }
        catch (ParseException e) {
            return null;
        }
    }

    protected Long getWorkModeByCod(String cod) {
        if (cod.equals("\u0445")) {
            return WorkModeType.NOT_WORK.getId();
        }
        if (cod.equals("\u0432")) {
            return WorkModeType.HOLIDAY.getId();
        }
        if (cod.equals("\u0434")) {
            return WorkModeType.DAY.getId();
        }
        if (cod.equals("\u043d")) {
            return WorkModeType.NIGHT.getId();
        }
        return null;
    }

    protected Long getWorkModeByTime(ZPBKSBAL easu) {
        if (this.employeeAbsenceMapper.getDomains(this.getParams(easu)).size() > 0) {
            return WorkModeType.NOT_WORK.getId();
        }
        if ("".equals(easu.getSOBEG()) && "".equals(easu.getSOEND())) {
            return WorkModeType.HOLIDAY.getId();
        }
        if (END_HOURS.equals(easu.getSOEND())) {
            return WorkModeType.NIGHT.getId();
        }
        if (START_HOURS.equals(easu.getSOBEG())) {
            return WorkModeType.HOLIDAY.getId();
        }
        return WorkModeType.DAY.getId();
    }

    protected Boolean isNight(ZPBKSBAL easu) {
        if ("".equals(easu.getSOBEG()) && "".equals(easu.getSOEND())) {
            return false;
        }
        if (END_HOURS.equals(easu.getSOEND())) {
            return true;
        }
        return false;
    }

    protected Map<String, Object> getEmployeeParams(ZPBKSBAL easu) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("personalNumber", easu.getPERNR());
        return params;
    }
}

