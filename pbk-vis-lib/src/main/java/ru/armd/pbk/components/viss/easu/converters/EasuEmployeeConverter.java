package ru.armd.pbk.components.viss.easu.converters;

import com.sap.document.sap.rfc.functions.ZPBKSPERNR;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.core.Setting;
import ru.armd.pbk.domain.nsi.County;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.domain.nsi.employee.EmployeeAbsence;
import ru.armd.pbk.domain.nsi.employee.EmployeeAbsenceType;
import ru.armd.pbk.domain.nsi.employee.EmployeeDepartmentMove;
import ru.armd.pbk.domain.nsi.employee.EmployeeLastUpdate;
import ru.armd.pbk.repositories.core.SettingsRepository;
import ru.armd.pbk.repositories.nsi.CountyRepository;
import ru.armd.pbk.repositories.nsi.SexRepository;
import ru.armd.pbk.repositories.nsi.department.DepartmentRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeAbsenceRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeAbsenceTypeRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeDepartmentMoveRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeLastUpdateRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeRepository;
import ru.armd.pbk.services.plans.PlansService;
import ru.armd.pbk.services.plans.employees.PlanEmployeeService;
import ru.armd.pbk.utils.date.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Конвертер для сотрудников.
 */
@Component
public class EasuEmployeeConverter
        extends AbstractEasuConverter<ZPBKSPERNR, Employee> {

    public static final Logger logger = Logger.getLogger(EasuEmployeeConverter.class);

    private static final String SEX_UNKNOWN = "UNKNOWN";
    private static final String ABSENCE_VACATION_TYPE = "VACATION";
    private static final String EMP_POS_NAME_FOR_PLAN_USE = "EMPLOYEE_POSITION_NAME_FOR_PLAN_USE";
    private static final String NULL_DATE = "0001-01-01";
    private static final String FULL_DATE = "9999-12-31";

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PlansService plansService;

    @Autowired
    private EmployeeAbsenceRepository employeeAbsenceRepository;

    @Autowired
    private EmployeeAbsenceTypeRepository employeeAbsenceTypeRepository;

    @Autowired
    private EmployeeDepartmentMoveRepository employeeDepartmentMoveRepository;

    @Autowired
    private SexRepository sexRepository;

    @Autowired
    private SettingsRepository settingsRepository;

    @Autowired
    private CountyRepository countyRepository;

    @Autowired
    private EmployeeLastUpdateRepository employeeLastUpdateRepository;

    @Autowired
    private PlanEmployeeService planEmployeeService;

    @Override
    public Employee save(ZPBKSPERNR easu) {
        Employee domain = super.save(easu);
        if (domain == null) {
            return null;
        }

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date periodStartDeptDate = dateFormat.parse(easu.getBEGDA());

            Date periodEndDeptDate = dateFormat.parse(easu.getENDDA());

            if (periodEndDeptDate != null && NULL_DATE.equals(easu.getENDDA())) {
                periodEndDeptDate = dateFormat.parse(FULL_DATE);}
            //&& !NULL_DATE.equals(easu.getENDDA())
            if (periodStartDeptDate != null && !NULL_DATE.equals(easu.getBEGDA()) ) {
                EmployeeDepartmentMove last = employeeDepartmentMoveRepository.getLastDepartmentMove(domain.getHeadId());
                if (last == null || !last.getDepartmentId().equals(domain.getDepartmentId())) {
                    if (last != null) {
                        last.setPeriodEndDate(DateUtils.addDays(periodStartDeptDate, -1));
                        employeeDepartmentMoveRepository.save(last);
                    }
                    EmployeeDepartmentMove employeeDepartmentMove = new EmployeeDepartmentMove();
                    employeeDepartmentMove.setPeriodStartDate(periodStartDeptDate);

                    employeeDepartmentMove.setPeriodEndDate(periodEndDeptDate);

                    employeeDepartmentMove.setDepartmentId(domain.getDepartmentId());
                    employeeDepartmentMove.setEmployeeId(domain.getHeadId());
                    employeeDepartmentMoveRepository.save(employeeDepartmentMove);
                }

                /**
                 if(!END_DATE.equals(easu.getENDDA())){
                 planEmployeeService.fireEmployee(domain.getHeadId(), domain.getDepartmentId(), periodEndDeptDate);
                 } else if (domain.getFireDate() != null) {
                 planEmployeeService.unfireEmployee(domain.getHeadId(), domain.getDepartmentId());
                 }*/
            }
            Date periodStartDate = dateFormat.parse(easu.getOBEGD());
            Date periodEndDate = dateFormat.parse(easu.getOENDD());
            if (periodStartDate != null && !NULL_DATE.equals(easu.getOBEGD()) && !NULL_DATE.equals(easu.getOENDD())) {
                EmployeeAbsenceType vacationType = employeeAbsenceTypeRepository.getByCode(ABSENCE_VACATION_TYPE);
                if (employeeAbsenceRepository.getDomain(getAbsenceVacationParams(domain, vacationType, periodStartDate, periodEndDate)) == null) {
                    EmployeeAbsence employeeAbsence = new EmployeeAbsence();
                    employeeAbsence.setPeriodStartDate(periodStartDate);
                    employeeAbsence.setPeriodEndDate(periodEndDate);
                    employeeAbsence.setAbsenceTypeId(vacationType.getId());
                    employeeAbsence.setEmployeeId(domain.getHeadId());
                    employeeAbsence.setDescription(easu.getATEXT());
                    employeeAbsenceRepository.save(employeeAbsence);
                    plansService.checkControllerGraph(employeeAbsence.getEmployeeId(),
                            employeeAbsence.getPeriodStartDate(), employeeAbsence.getPeriodEndDate(),
                            employeeAbsence.getAbsenceTypeId());
                }
            }
            if (domain.getCountyId() == null) {
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("deptId", domain.getDepartmentId());
                List<County> counties = countyRepository.getDomains(params);
                if (counties.size() == 1) {
                    domain.setCountyId(counties.get(0).getHeadId());
                    repository.save(domain);
                }
            }
            setUpdateDate(domain, periodEndDeptDate);
        } catch (ParseException e) {

        }
        return domain;
    }

    protected void setUpdateDate(Employee employee, Date fireDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("employeeId", employee.getHeadId());
        EmployeeLastUpdate employeeLastUpdate = employeeLastUpdateRepository.getDomain(params);
        if (employeeLastUpdate == null) {
            employeeLastUpdate = new EmployeeLastUpdate();
            employeeLastUpdate.setEmployeeId(employee.getHeadId());
        }
        employeeLastUpdate.setFireDate(fireDate);
        employeeLastUpdate.setIsFire(employee.getFireDate() != null);
        if (employeeLastUpdate.getLastDepartmentId() != null && !employeeLastUpdate.getLastDepartmentId().equals(employeeLastUpdate.getCurrDepartmentId())) {
            if (employeeLastUpdate.getIsScheduled()) {
                employeeLastUpdate.setLastDepartmentId(employeeLastUpdate.getCurrDepartmentId());
                employeeLastUpdate.setCurrDepartmentId(employee.getDepartmentId());
                employeeLastUpdate.setIsScheduled(false);
            } else {
                logger.error("EmployeeUpdateScheduler doesn't set isSheduled in case of moving department for employeeFhdId: " + employee.getEasuFhdId());
            }
        } else {
            employeeLastUpdate.setLastDepartmentId(employeeLastUpdate.getCurrDepartmentId());
            employeeLastUpdate.setCurrDepartmentId(employee.getDepartmentId());
            employeeLastUpdate.setIsScheduled(false);
        }
        employeeLastUpdate.setLastUpdate(new Date());
        employeeLastUpdateRepository.save(employeeLastUpdate);
    }

    @Override
    protected Employee convert(ZPBKSPERNR easu) {
        Long deptId = departmentRepository.getDomain(getDepartmentParams(easu)).getHeadId();
        Employee domain = new Employee();
        domain.setEasuFhdId(easu.getPERNR());
        domain.setPatronumic(easu.getMIDNM());
        domain.setName(easu.getVORNA());
        domain.setSurname(easu.getNACHN());
        domain.setPersonalNumber(easu.getPERNR());
        domain.setDepartmentId(deptId);
        domain.setSexId(sexRepository.getByCode(SEX_UNKNOWN).getId().intValue());
        domain.setPositionName(easu.getPLTXT());
        Setting setting = settingsRepository.getByCode(EMP_POS_NAME_FOR_PLAN_USE);
        domain.setForPlanUse(setting.getValue().equals(easu.getPLTXT()));
        return domain;
    }

    @Override
    protected Boolean merge(Employee from, Employee to) {
        Boolean result = false;
        if (!from.getName().equals(to.getName())) {
            result = true;
            to.setName(from.getName());
        }
        if (!from.getPatronumic().equals(to.getPatronumic())) {
            result = true;
            to.setPatronumic(from.getPatronumic());
        }
        if (!from.getEasuFhdId().equals(to.getEasuFhdId())) {
            result = true;
            to.setEasuFhdId(from.getEasuFhdId());
        }
        if (!from.getSurname().equals(to.getSurname())) {
            result = true;
            to.setSurname(from.getSurname());
        }
        if (!from.getPersonalNumber().equals(to.getPersonalNumber())) {
            result = true;
            to.setPersonalNumber(from.getPersonalNumber());
        }
        if (!from.getDepartmentId().equals(to.getDepartmentId())) {
            result = true;
            to.setDepartmentId(from.getDepartmentId());
        }
        if (!from.getPositionName().equals(to.getPositionName())) {
            result = true;
            to.setPositionName(from.getPositionName());
        }
		/*
		 * Метка участвует в планировании и пол сотрудника остается старыми.
		if(!from.getSexId().equals(to.getSexId())) {
			result = true;
			to.setSexId(from.getSexId());
		}
		if(!from.getForPlanUse().equals(to.getForPlanUse())) {
			result = true;
			to.setForPlanUse(from.getForPlanUse());
		}*/
        return result;
    }

    @Override
    protected Employee save(Employee domain) {
        if (domain.getId() != null) {
            domain.setVersionStartDate(new Date());
            return repository.saveVersion(domain);
        }
        return repository.save(domain);
    }

    @Override
    protected IDomainRepository<Employee> getRepository() {
        return repository;
    }

    @Override
    protected Map<String, Object> getParams(ZPBKSPERNR easu) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("personalNumber", easu.getPERNR());
        return params;
    }

    protected Map<String, Object> getDepartmentParams(ZPBKSPERNR easu) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("easuFhdId", easu.getORGEH());
        return params;
    }

    protected Map<String, Object> getDepartmentMoveParams(Employee domain, Date startDate, Date endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("employeeId", domain.getHeadId());
        params.put("periodStartDate", startDate);
        params.put("periodEndDate", endDate);
        params.put("departmentId", domain.getDepartmentId());
        return params;
    }

    protected Map<String, Object> getAbsenceVacationParams(Employee domain, EmployeeAbsenceType type, Date startDate, Date endDate) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("employeeId", domain.getHeadId());
        params.put("periodStartDate", startDate);
        params.put("periodEndDate", endDate);
        params.put("absenceTypeId", type.getId());
        return params;
    }

}
