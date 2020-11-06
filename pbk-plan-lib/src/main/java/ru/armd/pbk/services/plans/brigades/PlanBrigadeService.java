package ru.armd.pbk.services.plans.brigades;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.armd.pbk.authtoken.AuthenticationManager;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.department.Department;
import ru.armd.pbk.domain.plans.brigades.Brigade;
import ru.armd.pbk.dto.plans.brigades.BrigadeApproveDTO;
import ru.armd.pbk.dto.plans.brigades.BrigadeDTO;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.mappers.nsi.department.DepartmentMapper;
import ru.armd.pbk.mappers.plans.brigades.PlanBrigadeMapper;
import ru.armd.pbk.mappers.tasks.TaskReportMapper;
import ru.armd.pbk.matcher.plans.PlanBrigadeMatcher;
import ru.armd.pbk.repositories.plans.brigades.PlanBrigadeRepository;
import ru.armd.pbk.services.plans.PlansService;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.plans.brigades.BrigadeVenueView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Сервисов бригад планов отдела.
 */
@Service
public class PlanBrigadeService
	extends BaseDomainService<Brigade, BrigadeDTO> {

   private static final Logger LOGGER = Logger.getLogger(PlanBrigadeService.class);

   private PlanBrigadeRepository repository;

   @Autowired
   private PlansService planService;

   @Autowired
   private PlanBrigadeMapper planBrigadeMapper;

   @Autowired
   private DepartmentMapper departmentMapper;

   @Autowired
   private TaskReportMapper taskReportMapper;

   @Autowired
   PlanBrigadeService(PlanBrigadeRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Brigade toDomain(BrigadeDTO dto) {
	  return PlanBrigadeMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public BrigadeDTO toDTO(Brigade domain) {
	  return PlanBrigadeMatcher.INSTANCE.toDTO(domain);
   }

   @Override
   public JsonGridData getGridViews(BaseGridViewParams params) {
	  List<BrigadeVenueView> planBrigadeView = PlanBrigadeMatcher.INSTANCE.getBrigadeVenueView(repository.getPlanBrigades(params));
	  return createJsonGridData(planBrigadeView, params.getPage(), params.getCount());
   }

   /**
	* Одобрить бригаду.
	*
	* @param approveDTO дто.
	* @param bApprove   подтверждение.
	*/
   public void approveBrigade(BrigadeApproveDTO approveDTO, boolean bApprove)
	   throws PBKValidationException {
	  if (bApprove) {
		 List<Department> departs = new ArrayList<Department>();
		 Set<String> incorDepts = new HashSet<String>();
		 if (approveDTO.getDeptId() == null) {
			Map<String, Object> params = new HashMap<>();
			params.put("forPlanUse", true);
			List<SelectItem> list = departmentMapper.getSelectItems(params);
			for (SelectItem si : list) {
			   departs.add(departmentMapper.getActual(si.getId()));
			}
		 } else {
			departs.add(departmentMapper.getActual(approveDTO.getDeptId()));
		 }
		 for (Department dept : departs) {
			Calendar c = Calendar.getInstance();
			c.setTime(approveDTO.getStartDate());
			while (!c.getTime().after(approveDTO.getEndDate()) && !incorDepts.contains(dept.getName())) {
			   Map<String, Object> filter = new HashMap<String, Object>();
			   filter.put("dateFrom", c.getTime());
			   filter.put("dateTo", c.getTime());
			   filter.put("deptId", dept.getHeadId());
			   BaseGridViewParams params = new BaseGridViewParams(1L, (long) Integer.MAX_VALUE, (String) null, "asc", filter);
			   List<BrigadeVenueView> brigadeVenues = planBrigadeMapper.getBrigadeVenueViews(params);
			   if (brigadeVenues != null && !brigadeVenues.isEmpty()) {
				  List<Brigade> brigades = planBrigadeMapper.getBrigadeShiftsForVenues(params, brigadeVenues);
				  if (brigades != null && !brigades.isEmpty()) {
					 List<Brigade> brgs = planService.prepareBrigades(dept.getHeadId(), c.getTime(), brigades);
					 for (Brigade brigade : brigades) {
						if (brgs != null) {
						   for (Brigade br : brgs) {
							  if (br.getCityVenueId().equals(brigade.getCityVenueId())
								  && br.getShiftId().equals(brigade.getShiftId())) {
								 if (br.getMgtCount() - brigade.getMgtCount() < 0) {
									incorDepts.add(dept.getName());
								 }
								 break;
							  }
						   }
						   if (incorDepts.contains(dept.getName())) {
							  break;
						   }
						}
					 }
				  }
			   }
			   c.add(Calendar.DAY_OF_YEAR, 1);
			}
		 }
		 if (incorDepts.size() > 0) {
			String s = "";
			if (approveDTO.getDeptId() == null) {
			   s = " (" + StringUtils.collectionToDelimitedString(incorDepts, ", ") + ")";
			}
			throw new PBKValidationException("error", "Согласование невозможно, т.к. имеются неполные бригады" + s + ".");
		 }
			/* List<Brigade> incorrectBrigades = planBrigadeMapper.checkBrigadeShiftsForVenues(
					approveDTO.getDeptId(), null, approveDTO.getStartDate(), approveDTO.getEndDate());
			if (incorrectBrigades.size() > 0) {
				String s = "";
				if (approveDTO.getDeptId() == null) {
					for (Brigade br: incorrectBrigades) {
						depts.add(br.getDeptName());
					}
					s = " (" + StringUtils.collectionToDelimitedString(depts, ", ") + ")";
				}
				throw new PBKValidationException("error", "Согласование невозможно, т.к. имеются неполные бригады" + s + ".");
			} */
	  } else {
		 List<Brigade> incorrectBrigades = planBrigadeMapper.checkBrigadeForTask(approveDTO.getDeptId(),
			 approveDTO.getStartDate(), approveDTO.getEndDate());
		 if (incorrectBrigades.size() > 0) {
			String s = "";
			if (approveDTO.getDeptId() == null) {
			   Set<String> depts = new HashSet<String>();
			   for (Brigade br : incorrectBrigades) {
				  depts.add(br.getDeptName());
			   }
			   s = " (" + StringUtils.collectionToDelimitedString(depts, ", ") + ")";
			}
			throw new PBKValidationException("error", "Рассогласование невозможно, т.к. есть задания со статусом 'В работе', 'Выполнено' или 'Закрыто'" + s + ".");
		 }
	  }
	  long l = System.currentTimeMillis();
	  List<Long> depts = approveDTO.getDeptId() != null ? Arrays.asList(new Long[] {approveDTO.getDeptId()})
		  : departmentMapper.getIds(new HashMap<String, Object>());
	  for (Long deptId : depts) {
		 if (!bApprove) {
			repository.approveBrigade(new BrigadeApproveDTO(deptId, approveDTO.getStartDate(), approveDTO.getEndDate()),
				bApprove);
		 }
		 System.out.println("1---> " + (System.currentTimeMillis() - l) / 1000.0 + " " + new Date());
		 l = System.currentTimeMillis();
		 Calendar c = Calendar.getInstance();
		 c.setTime(approveDTO.getStartDate());
		 do {
			if (bApprove) {
			   List<Brigade> brigades = planBrigadeMapper.getBrigades(c.getTime(), deptId, null);
			   List<Long> approvedBrigades = new ArrayList<Long>();
			   for (Brigade br : brigades) {
				  if (br.getIsAgree()) {
					 approvedBrigades.add(br.getId());
				  }
			   }
			   System.out.println("2---> " + (System.currentTimeMillis() - l) / 1000.0 + " " + new Date());
			   l = System.currentTimeMillis();
			   repository.approveBrigade(new BrigadeApproveDTO(deptId, c.getTime(), c.getTime()),
				   bApprove);
			   System.out.println("3---> " + (System.currentTimeMillis() - l) / 1000.0 + " " + new Date());
			   l = System.currentTimeMillis();
			   planService.distributedControllersToBrigades(deptId, c.getTime(), approvedBrigades);
			   System.out.println("4---> " + (System.currentTimeMillis() - l) / 1000.0 + " " + new Date());
			   l = System.currentTimeMillis();
			   Map<Long, Date> changes = new HashMap<Long, Date>();
			   changes.put(deptId, c.getTime());
			   planService.distributedRoutesToTasks(null, changes);
			   System.out.println("5---> " + (System.currentTimeMillis() - l) / 1000.0 + " " + new Date());
			   l = System.currentTimeMillis();
			} else {
			   planService.clearBrigadeTasks(deptId, c.getTime());
			}
			c.add(Calendar.DAY_OF_YEAR, 1);
		 } while (!c.getTime().after(approveDTO.getEndDate()));
		 if (bApprove) {
			taskReportMapper.insertReports(AuthenticationManager.getUserInfo().getUserId());
		 }
		 System.out.println("6---> " + (System.currentTimeMillis() - l) / 1000.0 + " " + new Date());
		 l = System.currentTimeMillis();
	  }
   }

   @Override
   public BrigadeDTO saveDTO(BrigadeDTO dto) {
	  if (dto.getDateFrom() == null && dto.getDateTo() == null) {
		 dto = super.saveDTO(dto);
	  } else {
		 if (dto.getDateFrom() == null) {
			dto.setDateFrom(dto.getPlanDate());
		 }
		 if (dto.getDateTo() == null) {
			dto.setDateTo(dto.getPlanDate());
		 }
		 long endDateSec = dto.getDateTo().getTime();
		 long delta = 24 * 3600000;
		 for (long date = dto.getDateFrom().getTime(); date <= endDateSec; date += delta) {
			Brigade currentBrigade = repository.getBrigadeByParams(dto.getDeptId(), dto.getCityVenueId(), dto.getShiftId(), new Date(date));
			if (currentBrigade == null || !currentBrigade.getIsAgree()) {
			   dto.setId(currentBrigade != null ? currentBrigade.getId() : null);
			   dto.setPlanDate(new Date(date));
			   dto = super.saveDTO(dto);
			}
		 }
	  }
	  return dto;
   }
}