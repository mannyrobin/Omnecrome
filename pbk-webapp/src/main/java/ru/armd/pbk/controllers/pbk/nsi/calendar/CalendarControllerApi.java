package ru.armd.pbk.controllers.pbk.nsi.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.core.controllers.BaseControllerApi;
import ru.armd.pbk.dto.nsi.CalendarDTO;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.services.nsi.calendar.CalendarService;
import ru.armd.pbk.utils.date.DateUtils;
import ru.armd.pbk.utils.json.JsonResult;

import javax.validation.Valid;
import java.util.Date;

/**
 * Рест контроллер календаря.
 */
@Controller
@RequestMapping(CalendarControllerApi.RM_CONTROLLER_PATH)
public class CalendarControllerApi
	extends BaseControllerApi {

   public static final String RM_CONTROLLER_PATH = "/pbk/nsi/calendar";

   static final String PERM_READ_CALENDAR = "hasAnyRole('DEBUG_TO_REPLACE','CALENDAR')";
   static final String PERM_EDIT_CALENDAR = "hasAnyRole('DEBUG_TO_REPLACE','CALENDAR_EDIT')";

   @Autowired
   private CalendarService calendarService;

   /**
	* Календарь на период.
	*
	* @param from с
	* @param to   по
	* @return
	*/
   @RequestMapping(method = RequestMethod.GET)
   @PreAuthorize(PERM_READ_CALENDAR)
   @ResponseBody
   public ResponseEntity<?> getCalendarForPeriod(
	   @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date from,
	   @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date to) {
	  JsonResult jsonResult = new JsonResult();
	  jsonResult.setResult(calendarService.getCalendarForPeriod(from, to));
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Заполнение календаря на день.
	*
	* @param day день
	* @return
	* @throws AsduException
	*/
   @RequestMapping(method = RequestMethod.POST)
   @PreAuthorize(PERM_EDIT_CALENDAR)
   @ResponseBody
   public ResponseEntity<?> saveCalendarDay(@RequestBody @Valid CalendarDTO day)
	   throws PBKException {
	  CalendarDTO oldCalendar = calendarService.getCalendarByDay(day.getWorkDate());
	  JsonResult jsonResult = new JsonResult();
	  if (oldCalendar == null) {
		 jsonResult.setResult(calendarService.createDay(day));
	  } else {
		 day.setId(oldCalendar.getId());
		 jsonResult.setResult(calendarService.updateDay(day));
	  }
	  return new ResponseEntity<>(jsonResult, HttpStatus.OK);
   }

   /**
	* Заполнение календаря на диапазон.
	*
	* @param from с
	* @param to   по
	* @return
	* @throws AsduException
	*/
   @RequestMapping(value = "/fill", method = RequestMethod.POST)
   @PreAuthorize(PERM_EDIT_CALENDAR)
   @ResponseBody
   public ResponseEntity<?> fillCalendar(
	   @RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy", iso = ISO.DATE) Date from,
	   @RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "dd.MM.yyyy") Date to)
	   throws PBKException {
	  calendarService.fillCalendar(DateUtils.shiftToDayStart(from), DateUtils.shiftToDayStart(to));
	  return new ResponseEntity<>(HttpStatus.OK);
   }

}
