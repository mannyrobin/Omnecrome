package ru.armd.pbk.controllers.pbk.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.armd.pbk.components.viss.jobs.PassengersJob;
import ru.armd.pbk.components.viss.jobs.StopIntervalsJob;
import ru.armd.pbk.core.controllers.BaseControllerApi;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.utils.date.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * rest контроллер запуска джобов.
 */
@Controller
@RequestMapping(JobControllerApi.RM_CONTROLLER_PATH)
public class JobControllerApi
	extends BaseControllerApi {

   public static final String RM_PATH = "/job";
   public static final String RM_CONTROLLER_PATH = RM_API_PATH + RM_PATH;


   @Autowired
   PassengersJob passengersJob;

   @Autowired
   StopIntervalsJob stopIntervalsJob;

   /**
	* Запустить джоб.
	*
	* @param name  имя джоба.
	* @param start начало.
	* @param end   конец.
	* @return
	* @throws PBKException
	*/
   @RequestMapping(value = "", method = RequestMethod.GET)
   @ResponseBody
   public ResponseEntity<?> startJob(
	   @RequestParam(value = "name", required = true) String name,
	   @RequestParam(value = "start", required = true) @DateTimeFormat(pattern = "yyyyMMdd") Date start,
	   @RequestParam(value = "end", required = false) @DateTimeFormat(pattern = "yyyyMMdd") Date end)
	   throws PBKException {
	  if ("passengers".equals(name)) {
		 passengersJob.calculate(start, end);
	  } else if ("intervals".equals(name)) {
		 stopIntervalsJob.calculate(start, end);
	  } else {
		 throw new PBKException("Incorrect job name");
	  }
	  if (end == null) {
		 return new ResponseEntity<>("job '" + name + "' started for date '" + (new SimpleDateFormat(DateUtils.DATE_FORMAT)).format(start) + "'", HttpStatus.OK);
	  } else {
		 return new ResponseEntity<>("job '" + name + "' started for dates between '" + (new SimpleDateFormat(DateUtils.DATE_FORMAT)).format(start)
			 + "' and '" + (new SimpleDateFormat(DateUtils.DATE_FORMAT)).format(end) + "'", HttpStatus.OK);
	  }
   }
}
