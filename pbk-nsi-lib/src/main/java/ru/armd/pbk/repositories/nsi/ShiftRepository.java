package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.Shift;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.ShiftMapper;

import java.util.List;

/**
 * Репозиторий смен.
 */
@Repository
public class ShiftRepository
	extends BaseDomainRepository<Shift> {

   public static final Logger LOGGER = Logger.getLogger(ShiftRepository.class);

   private ShiftMapper mapper;

   @Autowired
   ShiftRepository(ShiftMapper mapper) {
	  super(NsiAuditType.NSI_SHIFT, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получение списка смен для отображения в плановых комбобоксах.
	*
	* @param params параметры фильтрации.
	* @return список смен объектов.
	*/
   public List<ISelectItem> getSelectItemsForPlan(BaseSelectListParams params) {
	  List<ISelectItem> sList = null;
	  try {
		 sList = mapper.getSelectItemsForPlan(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Выбрать смену по id расписания.
	*
	* @param scheduleId id расписания.
	* @return смену.
	*/
   public Shift getByScheduleId(Long scheduleId) {
	  Shift domain = null;
	  try {
		 domain = mapper.getByScheduleId(scheduleId);
	  } catch (Exception e) {
		 String message = "Не удалось получить запись по id. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, scheduleId, message, e);
		 throw new PBKException(message, e);
	  }
	  return domain;
   }

   /**
	* Получить смену задания. Время окончания рабочего дня смены
	* зависит от планового времени работы сотрудника.
	* Например, если смена начинается в 11:00, а заканчивается в 20:00 и
	* плановое время сотрудника 7 часов, то время окончания рабочего дня будет 19:00.
	*
	* @param taskId ИД задания
	* @return смена
	*/
   public Shift getByTaskId(Long taskId) {
	  Shift domain = null;
	  try {
		 domain = mapper.getByTaskId(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось получить запись по id. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, taskId, message, e);
		 throw new PBKException(message, e);
	  }
	  return domain;
   }
}