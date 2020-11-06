package ru.armd.pbk.controllers.pbk.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseDomainControllerApi;
import ru.armd.pbk.core.utils.ControllerMethods;
import ru.armd.pbk.domain.core.Audit;
import ru.armd.pbk.dto.core.AuditDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.core.AuditService;
import ru.armd.pbk.utils.json.JsonResult;

/**
 * Контроллер аудита.
 */
@Controller
@RequestMapping(AuditsControllerApi.RM_CONTROLLER_PATH)
public class AuditsControllerApi
	extends BaseDomainControllerApi<Audit, AuditDTO> {

   public static final String RM_PATH = "/admin/audit";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;
   public static final String RM_LEVELS = "/levels";
   public static final String RM_TYPES = "/types";
   public static final String RM_OPERATIONS = "/operations";
   static final String PERM_READ_AUDIT = "hasAnyRole('DEBUG_TO_REPLACE','ADMIN_AUDIT')";


   AuditService auditService;

   @Autowired
   AuditsControllerApi(AuditService service) {
	  this.service = service;
	  this.auth.put(ControllerMethods.GET_VIEWS, PERM_READ_AUDIT);
	  this.auth.put(ControllerMethods.GET_DTO, PERM_READ_AUDIT);
	  this.auditService = service;
   }

   /**
	* Возвращает перечень допустимых уровней сообщений.
	*
	* @return список допустимых уровней id и name
	*/
   @RequestMapping(value = RM_LEVELS, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize(PERM_ALLOW_ALL)
   public ResponseEntity<?> getAuditLevels()
	   throws PBKException {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(auditService.getAuditLevelsSelectItems());
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Возвращает перечень допустимых типов сообщений.
	*
	* @return список допустимых уровней id и name
	*/
   @RequestMapping(value = RM_TYPES, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize(PERM_ALLOW_ALL)
   public ResponseEntity<?> getAuditTypes()
	   throws PBKException {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(auditService.getAuditTypesSelectItems());
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Возвращает перечень допустимых типов операций.
	*
	* @return список допустимых операций id и name
	*/
   @RequestMapping(value = RM_OPERATIONS, method = RequestMethod.GET)
   @ResponseBody
   @PreAuthorize(PERM_ALLOW_ALL)
   public ResponseEntity<?> getOperationTypes()
	   throws PBKException {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(auditService.getAuditOperationsSelectItems());
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

}
