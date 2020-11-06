package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.report.standard.So21Mapper;
import ru.armd.pbk.views.report.So21View;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий стандартного отчёта "Проходы по БСК контролера".
 */
@Repository
public class So21Repository
	extends SoRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(So21Repository.class);

    @Autowired
    private So21Mapper so21Mapper;

   @Autowired
   So21Repository(So21Mapper mapper) {
	  super(ReportAuditType.REPORT_STANDARD_SO21, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

    @Override
    @DepartmentAuthorization
    public List<So21View> getGridViews(BaseGridViewParams params) {
        String timeFrom = (String) params.getFilter().get("timeFrom");
        String timeTo = (String) params.getFilter().get("timeTo");
        Date dateFrom = (Date) params.getFilter().get("dateFrom");
        Date dateTo = (Date) params.getFilter().get("dateTo");
        if (!timeFrom.equals("00:00") || !timeTo.equals("00:00")) {

            Calendar dateFromCnd = Calendar.getInstance();
            dateFromCnd.setTime(dateFrom);
            dateFromCnd.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeFrom.split(":")[0]));
            dateFromCnd.set(Calendar.MINUTE, Integer.valueOf(timeFrom.split(":")[1]));
            Calendar dateToCnd = Calendar.getInstance();
            dateToCnd.setTime(dateTo);
            dateToCnd.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeTo.split(":")[0]));
            dateToCnd.set(Calendar.MINUTE, Integer.valueOf(timeTo.split(":")[1]));
            params.getFilter().put("dateTimeFrom", dateFromCnd.getTime());
            params.getFilter().put("dateTimeTo", dateToCnd.getTime());
        }
        List<So21View> so21Views = null;
        try {
            so21Views = so21Mapper.getGridViews(params);
        } catch (Exception e) {
            String message = "Не удалось получить список для отображения расписания. Ошибка: " + e.getMessage();
            logAudit(AuditLevel.ERROR, ReportAuditType.REPORT_STANDARD_SO6, AuditObjOperation.SELECT, params, message, e);
            throw new PBKException(message, e);
        }
        return so21Views;
    }

    @Override
    @DepartmentAuthorization
    public <View extends BaseGridView> View getGridViewTotal(Map<String, Object> params) {
        String timeFrom = (String) params.get("timeFrom");
        String timeTo = (String) params.get("timeTo");
        Date dateFrom = (Date) params.get("dateFrom");
        Date dateTo = (Date) params.get("dateTo");
        if (!timeFrom.equals("00:00") || !timeTo.equals("00:00")) {

            Calendar dateFromCnd = Calendar.getInstance();
            dateFromCnd.setTime(dateFrom);
            dateFromCnd.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeFrom.split(":")[0]));
            dateFromCnd.set(Calendar.MINUTE, Integer.valueOf(timeFrom.split(":")[1]));
            Calendar dateToCnd = Calendar.getInstance();
            dateToCnd.setTime(dateTo);
            dateToCnd.set(Calendar.HOUR_OF_DAY, Integer.valueOf(timeTo.split(":")[0]));
            dateToCnd.set(Calendar.MINUTE, Integer.valueOf(timeTo.split(":")[1]));
            params.put("dateTimeFrom", dateFromCnd.getTime());
            params.put("dateTimeTo", dateToCnd.getTime());
        }
        So21View view = null;
        try {
            view = so21Mapper.getGridViewTotal(params);
        } catch (Exception e) {
            String message = "Не удалось получить значение Итого. Ошибка: " + e.getMessage();
            logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
            throw new PBKException(message, e);
        }
        return (View) view;
    }
}
