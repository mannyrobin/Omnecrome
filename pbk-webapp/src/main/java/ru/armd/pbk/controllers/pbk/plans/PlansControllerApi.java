package ru.armd.pbk.controllers.pbk.plans;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.domain.nsi.employee.EmployeeAbsence;
import ru.armd.pbk.domain.nsi.employee.EmployeeWorkMode;
import ru.armd.pbk.dto.plans.brigades.BrigadeApproveDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.employee.EmployeeAbsenceMapper;
import ru.armd.pbk.mappers.nsi.employee.EmployeeMapper;
import ru.armd.pbk.mappers.nsi.employee.EmployeeWorkModeMapper;
import ru.armd.pbk.services.plans.PlansService;
import ru.armd.pbk.utils.json.JsonResult;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * rest контроллер планов отдела.
 */
@Controller
@RequestMapping(PlansControllerApi.RM_CONTROLLER_PATH)
public class PlansControllerApi
	extends BaseDomainControllerApi<BaseDomain, BaseDTO> {

   public static final String RM_PATH = "/pbk/plans";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final Logger LOGGER = Logger.getLogger(PlansControllerApi.class);

   static final String PERM_READ_PLANS = "hasAnyRole('DEBUG_TO_REPLACE','PBK_PLANS')";
   static final String PERM_EDIT_PLANS = "hasAnyRole('DEBUG_TO_REPLACE','PBK_PLANS_EDIT')";

   @Autowired
   EmployeeWorkModeMapper ewmMapper;
   @Autowired
   EmployeeMapper eMapper;
   @Autowired
   EmployeeAbsenceMapper eaMapper;

   /**
	* Контроллер.
	*
	* @param service сервис.
	*/
   @Autowired
   PlansControllerApi(PlansService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.ADD_DTO, PERM_EDIT_PLANS);
   }

   /**
	* Перераспределение маршрутов по заданиям.
	*
	* @param dto дто
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/distribute-routes", method = RequestMethod.POST)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).ADD_DTO)")
   public ResponseEntity<?> distributeRoutes(@RequestBody @Valid BrigadeApproveDTO dto)
	   throws PBKException {
	  ((PlansService) service).distributedRoutesToTasks(dto.getDeptId(), dto.getStartDate(), dto.getEndDate());
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Пересоздание бригад.
	*
	* @param dto дто
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/recreate", method = RequestMethod.POST)
   @ResponseBody
   @PreAuthorize("#root.this.customPreAuthorize(#root, T(ru.armd.pbk.core.utils.ControllerMethods).ADD_DTO)")
   public ResponseEntity<?> recreateBrigades(@RequestBody @Valid BrigadeApproveDTO dto)
	   throws PBKException {
	  ((PlansService) service).formBrigades(dto.getDeptId(), dto.getStartDate(), dto.getEndDate(), true, dto.isResetManualData());
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Пересоздание бригад.
	*
	* @param dto дто
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/ratio-recalc", method = RequestMethod.POST)
   @ResponseBody
   public ResponseEntity<?> recalcRatio(@RequestBody @Valid BrigadeApproveDTO dto)
	   throws PBKException {
	  ((PlansService) service).makeRouteRating(dto.getStartDate(), dto.getEndDate(), null);
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Тест 2.
	*
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/test2", method = RequestMethod.POST)
   @ResponseBody
   public ResponseEntity<?> processShiftModes()
	   throws PBKException {
	  ((PlansService) service).processShiftModes();
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Тест 3.
	*
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/test3", method = RequestMethod.POST)
   @ResponseBody
   public ResponseEntity<?> checkControllerGraph()
	   throws PBKException {
	  List<Employee> emps = eMapper.getDomains(new HashMap<String, Object>());
	  for (Employee emp : emps) {
		 if (!emp.getForPlanUse()) {
			continue;
		 }
		 HashMap<String, Object> params = new HashMap<String, Object>();
		 params.put("employeeId", emp.getHeadId());
		 params.put("dateFrom", new Date(116, 0, 1));
		 params.put("dateTo", new Date(116, 9, 1));
		 List<EmployeeWorkMode> ewms = ewmMapper.getDomains(params);
		 for (EmployeeWorkMode ewm : ewms) {
			try {
			   ((PlansService) service).checkControllerGraph(emp.getHeadId(), ewm.getWorkDate(), ewm.getWorkModeId(), ewm.getWorkPlanHours());
			} catch (Exception e) {
			   LOGGER.error(e);
			}
		 }
	  }
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Тест 1.
	*
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/test1", method = RequestMethod.POST)
   @ResponseBody
   public ResponseEntity<?> checkControllerGraph1()
	   throws PBKException {
	  ((PlansService) service).checkControllerGraph(595L, new Date(116, 5, 2), 2L, new BigDecimal(8));
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Тест 5.
	*
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "/test5", method = RequestMethod.POST)
   @ResponseBody
   public ResponseEntity<?> checkControllerGraphAbsence()
	   throws PBKException {
	  List<EmployeeAbsence> emps = eaMapper.getDomains(new HashMap<String, Object>());
	  for (EmployeeAbsence emp : emps) {
		 ((PlansService) service).checkControllerGraph(emp.getEmployeeId(),
			 emp.getPeriodStartDate(), emp.getPeriodEndDate(), emp.getAbsenceTypeId());
	  }
	  JsonResult jsonResult = new JsonResult();
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }
}