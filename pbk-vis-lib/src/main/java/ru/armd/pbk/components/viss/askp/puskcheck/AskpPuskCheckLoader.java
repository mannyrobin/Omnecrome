package ru.armd.pbk.components.viss.askp.puskcheck;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvDomainLoader;
import ru.armd.pbk.domain.nsi.Route;
import ru.armd.pbk.domain.nsi.Ticket;
import ru.armd.pbk.domain.nsi.askppuskcheck.AskpPuskCheck;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.domain.tasks.Task;
import ru.armd.pbk.mappers.nsi.employee.EmployeeMapper;
import ru.armd.pbk.mappers.tasks.TaskMapper;
import ru.armd.pbk.repositories.nsi.RouteRepository;
import ru.armd.pbk.repositories.nsi.TicketRepository;
import ru.armd.pbk.repositories.nsi.askppuskcheck.AskpPuskCheckRepository;
import ru.armd.pbk.services.tasks.TasksService;
import ru.armd.pbk.utils.date.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Component
public class AskpPuskCheckLoader
	extends BaseCsvDomainLoader<AskpPuskCheck> {

   public static final Logger LOGGER = Logger.getLogger(AskpPuskCheckLoader.class);

   private final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
   private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd");

   private Map<String, Ticket> ticketCache = new HashMap<String, Ticket>();
   private Map<String, Route> routeCache = new HashMap<String, Route>();
   private Map<String, Employee> employeeCache = new HashMap<String, Employee>();

   @Autowired
   private TicketRepository ticketRepository;

   @Autowired
   private RouteRepository routeRepository;

   @Autowired
   private EmployeeMapper employeeMapper;

   @Autowired
   private TaskMapper taskMapper;

   @Autowired
   private TasksService taskService;

   @Autowired
   AskpPuskCheckLoader(AskpPuskCheckRepository domainRepository) {
	  super(domainRepository, false);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected AskpPuskCheck createDomain(String[] fields) {
	  AskpPuskCheck domain = new AskpPuskCheck();
	  try {
		 Route route = getRoute(fields[1]);
		 domain.setWorkDate(DateUtils.shiftToDayStart(DATE_FORMAT.parse(fields[0])));
		 domain.setAskpRouteCode(fields[1]);
		 domain.setRouteId(route == null ? null : route.getHeadId());
		 domain.setMoveCode(fields[2]);
		 domain.setUid(getLongValue(fields[3]));
		 domain.setTicketId(getTicketId(fields[4]));
		 domain.setCheckStartTime(DATE_TIME_FORMAT.parse(fields[5]));
		 domain.setCheckEndTime(DATE_TIME_FORMAT.parse(fields[6]));
		 domain.setCheckResult1Count(getIntegerValue(fields[7]));
		 domain.setCheckResult2Count(getIntegerValue(fields[8]));
		 domain.setCheckResult3Count(getIntegerValue(fields[9]));
		 domain.setCardNumber(fields[10]);
		 Employee employee = getEmployee(fields[10]);
		 if (employee != null) {
			Task task = taskMapper.getEmployeeTask(employee.getHeadId(), domain.getWorkDate());
			if (task != null) {
			   domain.setTaskId(task.getId());
			   taskService.processASKP(task.getId());
			}
		 }
	  } catch (Exception e) {
		 getLogger().error(e);
	  }
	  return domain;
   }

   protected Long getTicketId(String key) {
	  if (!ticketCache.containsKey(key)) {
		 ticketCache.put(key, ticketRepository.getByCode(key));
	  }
	  return ticketCache.get(key).getHeadId();
   }

   protected Route getRoute(String key) {
	  if (!routeCache.containsKey(key)) {
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("askpCod", key);
		 routeCache.put(key, routeRepository.getDomain(params));
	  }
	  return routeCache.get(key);
   }

   protected Employee getEmployee(String key) {
	  if (!employeeCache.containsKey(key)) {
		 employeeCache.put(key, employeeMapper.getEmployeeBySKKCardNumber(key));
	  }
	  return employeeCache.get(key);
   }

   @Override
   protected AskpPuskCheck getExistedDomain(AskpPuskCheck newDomain) {
	  return null;
   }
}
