package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.Shift;
import ru.armd.pbk.dto.nsi.ShiftDTO;
import ru.armd.pbk.matcher.nsi.IShiftMatcher;
import ru.armd.pbk.repositories.nsi.ShiftRepository;

import java.util.List;

/**
 * Сервис смен.
 */
@Service
public class ShiftService
	extends BaseDomainService<Shift, ShiftDTO> {

   private static final Logger LOGGER = Logger.getLogger(ShiftService.class);

   private ShiftRepository repository;

   @Autowired
   ShiftService(ShiftRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public ShiftDTO toDTO(Shift domain) {
	  return IShiftMatcher.INSTANCE.toDTO(domain);
   }

   @Override
   public Shift toDomain(ShiftDTO dto) {
	  return IShiftMatcher.INSTANCE.toDomain(dto);
   }

   /**
	* Получение списка смен для отображения в плановых комбобоксах.
	*
	* @param params параметры фильтрации.
	* @return список смен объектов.
	*/
   public List<ISelectItem> getSelectItemsForPlan(BaseSelectListParams params) {
	  List<ISelectItem> selectItemList = repository.getSelectItemsForPlan(params);
	  return selectItemList;
   }

   /**
	* Выбрать смену по id расписания.
	*
	* @param scheduleId id расписания.
	* @return смену.
	*/
   @Transactional
   public ShiftDTO getDTOByScheduleId(Long scheduleId) {
	  Shift domain = repository.getByScheduleId(scheduleId);
	  ShiftDTO dto = toDTO(domain);
	  return dto;
   }

   /**
	* Получить смену задания. Время окончания рабочего дня смены
	* зависит от планового времени работы сотрудника. Например,
	* если смена начинается в 11:00, а заканчивается в 20:00
	* и плановое время сотрудника 7 часов, то время окончания
	* рабочего дня будет 19:00.
	*
	* @param taskId ИД задания
	* @return смена
	*/
   @Transactional
   public ShiftDTO getDTOByTaskId(Long taskId) {
	  Shift domain = repository.getByTaskId(taskId);
	  ShiftDTO dto = toDTO(domain);
	  return dto;
   }
}
