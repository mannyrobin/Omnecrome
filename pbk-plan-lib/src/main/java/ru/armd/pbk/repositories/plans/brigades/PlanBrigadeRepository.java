package ru.armd.pbk.repositories.plans.brigades;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.authtoken.AuthenticationManager;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.plans.brigades.Brigade;
import ru.armd.pbk.domain.plans.brigades.PlanBrigade;
import ru.armd.pbk.dto.plans.brigades.BrigadeApproveDTO;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.PlanAuditType;
import ru.armd.pbk.enums.nsi.Shift;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.plans.brigades.PlanBrigadeMapper;
import ru.armd.pbk.services.plans.PlansService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий бригад планов отдела.
 */
@Repository
public class PlanBrigadeRepository
	extends BaseDomainRepository<Brigade> {

   public static final Logger LOGGER = Logger.getLogger(PlanBrigadeRepository.class);

   @Autowired
   private PlanBrigadeMapper planBrigadeMapper;

   @Autowired
   private PlansService plansService;

   @Autowired
   PlanBrigadeRepository(PlanBrigadeMapper mapper) {
	  super(PlanAuditType.PLAN_BRIGADE, mapper);
   }

   /**
	* Получает домен бригад плана отдела по параметрам.
	*
	* @param params параметры
	* @return домен бригад плана отдела
	*/
   public PlanBrigade getPlanBrigades(BaseGridViewParams params) {
	  PlanBrigade planBrigade = new PlanBrigade();
	  try {
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 planBrigade.setBrigadeVenues(planBrigadeMapper.getBrigadeVenueViews(params));
		 planBrigade.setReserveCount(planBrigadeMapper.getBrigadeReserveCounts(params));
		 if (planBrigade.getBrigadeVenues() != null && !planBrigade.getBrigadeVenues().isEmpty()) {
			planBrigade.setBrigades(
				planBrigadeMapper.getBrigadeShiftsForVenues(params, planBrigade.getBrigadeVenues()));
			if (planBrigade.getBrigades() != null && !planBrigade.getBrigades().isEmpty()) {
			   Map<Date, List<Brigade>> checkBrgs = new HashMap<>();
			   for (Brigade brigade : planBrigade.getBrigades()) {
				  if (!brigade.getIsAgree()) {
					 if (!checkBrgs.containsKey(brigade.getPlanDate()) && brigade.getDeptId() != null) {
						checkBrgs.put(brigade.getPlanDate(), plansService.prepareBrigades(brigade.getDeptId(), brigade.getPlanDate(), planBrigade.getBrigades()));
					 }
				  } else if (brigade.getId() != null) {
					 int reserveCount = getBrigadeReserveCount(planBrigade.getReserveCounts().get(df.format(brigade.getPlanDate())), brigade.getShiftId());
					 brigade.setHaveFreeControlers(brigade.getMgtCount().intValue() < brigade.getTaskCount() || reserveCount > brigade.getShiftReserveCount());
					 brigade.setNotFull(brigade.getMgtCount().intValue() > brigade.getTaskCount());
				  }
			   }
			   for (Brigade brigade : planBrigade.getBrigades()) {
				  List<Brigade> brgs = checkBrgs.get(brigade.getPlanDate());
				  if (brgs != null) {
					 for (Brigade br : brgs) {
						if (br.getCityVenueId().equals(brigade.getCityVenueId())
							&& br.getShiftId().equals(brigade.getShiftId())) {
						   if (br.getMgtCount() - brigade.getMgtCount() > 0) {
							  brigade.setHaveFreeControlers(true);
						   } else if (br.getMgtCount() - brigade.getMgtCount() < 0) {
							  brigade.setNotFull(true);
						   }
						   break;
						}
					 }
				  }
			   }
			}
		 }
	  } catch (Exception e) {
		 String message = "Не удалось получить список для отображения бригад. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, PlanAuditType.PLAN_BRIGADE, AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return planBrigade;
   }

   /**
	* Получение из строки кол-ва резервов нужное значение, в соответствии со
	* сменой.
	*
	* @param reserve строка резервов
	* @param shiftId id смены
	* @return кол-во человек в резерве
	*/
   private int getBrigadeReserveCount(String reserve, Long shiftId) {
	  if (reserve == null || shiftId == null) {
		 return 0;
	  }
	  if (shiftId.equals(Shift.I.getId())) {
		 return Integer.parseInt(reserve.split(" ")[0].split("/")[1]);
	  }
	  if (shiftId.equals(Shift.II.getId())) {
		 return Integer.parseInt(reserve.split(" ")[1].split("/")[1]);
	  }
	  if (shiftId.equals(Shift.DAY.getId())) {
		 return Integer.parseInt(reserve.split(" ")[2].split("/")[1]);
	  }
	  if (shiftId.equals(Shift.NIGHT.getId())) {
		 return Integer.parseInt(reserve.split(" ")[3].split("/")[1]);
	  }
	  return 0;
   }

   /**
	* Одобрение бригады.
	*
	* @param dto      дто.
	* @param bApprove одобрение.
	*/
   @Transactional(propagation = Propagation.REQUIRES_NEW)
   public void approveBrigade(BrigadeApproveDTO dto, boolean bApprove) {
	  planBrigadeMapper.approveBrigade(dto.getDeptId(), dto.getStartDate(), dto.getEndDate(), bApprove,
		  AuthenticationManager.getUserInfo().getUserId());
   }

   /**
	* Получить бригаду по параметрам.
	*
	* @param deptId      id подразделения
	* @param cityVenueId id места встречи
	* @param shiftId     id смены
	* @param workDate    дата
	* @return бригаду
	*/
   public Brigade getBrigadeByParams(Long deptId, Long cityVenueId, Long shiftId, Date workDate) {
	  Brigade result = null;
	  try {
		 result = planBrigadeMapper.getBrigadeByParams(deptId, cityVenueId, shiftId, workDate);
	  } catch (Exception e) {
		 String message = "Не удалось получить бригаду. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return result;
   }
}