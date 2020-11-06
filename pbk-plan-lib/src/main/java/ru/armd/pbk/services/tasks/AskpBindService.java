package ru.armd.pbk.services.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseService;
import ru.armd.pbk.mappers.nsi.askppuskcheck.AskpPuskCheckMapper;
import ru.armd.pbk.mappers.tasks.AskpContactlessCardMapper;
import ru.armd.pbk.repositories.plans.schedules.PlanScheduleRepository;

import java.util.Date;

/**
 * Сервис привязки данных из АСКП.
 */
@Service
public class AskpBindService
	extends BaseService {

   private static final Logger LOGGER = Logger.getLogger(AskpBindService.class);

   @Autowired
   private AskpPuskCheckMapper puskMapper;

   @Autowired
   private AskpContactlessCardMapper contactlessCardMapper;

   @Autowired
   private PlanScheduleRepository planScheduleRepository;

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Осуществлить привязку АСКП на заданую дату.
	*
	* @param workDate дата
	*/
   public void bindAskpByWorkDate(Date workDate) {
	  try {
		 puskMapper.bindByWorkDate(workDate);
		 contactlessCardMapper.updateTasks(workDate);
	  } catch (Exception e) {
		 LOGGER.error(String.format("Не удалось привязать АСКП на дату %s", workDate), e);
	  }
   }

   /**
	* Осуществлить привязку АСКП индефикатору расписания.
	*
	* @param psId индефикатор расписания
	*/
   public void bindAskpByPlanSchedulerId(Long psId) {
	  try {
		 bindAskpByWorkDate(planScheduleRepository.getById(psId).getPlanDate());
	  } catch (Exception e) {
		 LOGGER.error(String.format("Не удалось привязать АСКП по индефикатору %s", psId), e);
	  }
   }
}
