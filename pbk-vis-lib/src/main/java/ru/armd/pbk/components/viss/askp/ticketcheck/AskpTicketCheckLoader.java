package ru.armd.pbk.components.viss.askp.ticketcheck;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvMultipleDomainLoader;
import ru.armd.pbk.domain.nsi.Route;
import ru.armd.pbk.domain.nsi.Ticket;
import ru.armd.pbk.domain.nsi.askp.TicketCheck;
import ru.armd.pbk.mappers.nsi.askp.TicketCheckByHourMapper;
import ru.armd.pbk.mappers.nsi.askp.TicketCheckByTypeMapper;
import ru.armd.pbk.mappers.viss.intervals.PassengersMapper;
import ru.armd.pbk.repositories.nsi.RouteRepository;
import ru.armd.pbk.repositories.nsi.TicketRepository;
import ru.armd.pbk.repositories.nsi.askp.TicketCheckRepository;
import ru.armd.pbk.utils.ImportResult;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Загрузчик проверок билетов из АСКП.
 */
@Component
@Scope("prototype")
public class AskpTicketCheckLoader
	extends BaseCsvMultipleDomainLoader<TicketCheck> {

   private static final Logger LOGGER = Logger.getLogger(AskpTicketCheckLoader.class);
   private static final Integer COUNT_TIKCET_CACHE = 250;

   private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
   private final DateFormat DATE_FORMAT_DB = new SimpleDateFormat("yyyy-MM-dd");
   private final DateFormat DATE_TIME_FORMAT_DB = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

   private Map<String, Ticket> ticketCache = null;
   private Map<String, Route> routeCache = null;

   @Autowired
   private TicketRepository ticketRepository;

   @Autowired
   private RouteRepository routeRepository;

   @Autowired
   private AskpTicketCheckByTicketLoader loader;

   @Autowired
   private TicketCheckByHourMapper ticketCheckByHourMapper;

   @Autowired
   private TicketCheckByTypeMapper ticketCheckByTypeMapper;

   @Autowired
   private PassengersMapper passengersMapper;

   @Autowired
   AskpTicketCheckLoader(TicketCheckRepository domainRepository) {
	  super(domainRepository, COUNT_TIKCET_CACHE);
   }

   @Override
   protected TicketCheck createDomain(String[] fields) {
	  TicketCheck domain = new TicketCheck();
	  try {
		 Route route = getRoute(fields[0]);
		 domain.setRouteId(route == null ? null : route.getHeadId());
		 domain.setAskpRouteCode(fields[0]);
		 domain.setMoveCode(fields[1]);
		 domain.setTicketCode(fields[2]);
		 domain.setTicketId(getTicketId(fields[2]));
		 Date parse = DATE_FORMAT.parse(fields[3]);
		 domain.setWorkDate(getWorkDate());
		 domain.setCheckTime(parse);
		 domain.setAskpCheckId(fields[4]);
	  } catch (ParseException e) {
		 LOGGER.error(e.getMessage(), e);
	  }
	  return domain;
   }

   @Override
   protected String createSql(TicketCheck domain) {
	  String sql = "INSERT INTO [PBK_VIS].[ASKP_TICKET_CHECS]([WORK_DATE], [ROUTE_ID], [ASKP_ROUTE_CODE], [MOVE_CODE], [TICKET_ID], [CHECK_TIME], [ASKP_CHECK_ID]) \n"
		  + "VALUES('%s', %s, '%s', '%s', %s, '%s', '%s')";
	  return String.format(sql, DATE_FORMAT_DB.format(domain.getWorkDate()), domain.getRouteId(), domain.getAskpRouteCode(), domain.getMoveCode(), domain.getTicketId(), DATE_TIME_FORMAT_DB.format(domain.getCheckTime()), domain.getAskpCheckId());
   }

   protected Long getTicketId(String key) {
	  if (ticketCache == null) {
		 ticketCache = new HashMap<String, Ticket>();
		 Map<String, Object> params = new HashMap<String, Object>();
		 List<Ticket> tickets = ticketRepository.getDomains(params);
		 for (Ticket ticket : tickets) {
			ticketCache.put(ticket.getCode(), ticket);
		 }
	  }
	  return ticketCache.get(key).getHeadId();
   }

   protected Route getRoute(String key) {
	  if (routeCache == null) {
		 routeCache = new HashMap<String, Route>();
		 Map<String, Object> params = new HashMap<String, Object>();
		 List<Route> routes = routeRepository.getDomains(params);
		 for (Route route : routes) {
			routeCache.put(route.getAskpRouteCode(), route);
		 }
	  }
	  return routeCache.get(key);
   }

   @Override
   protected void importDomain(TicketCheck newDomain, ImportResult<TicketCheck> importResult) {
	  super.importDomain(newDomain, importResult);
	  loader.processTicketCheck(newDomain);
   }

   /**
	* Подготовить данные к обмену.
	*
	* @param workDate дата
	*/
   public void prepareDate(Date workDate) {
	  ticketCheckByHourMapper.deleteByWorkDate(workDate, workDate);
	  ticketCheckByTypeMapper.deleteByWorkDate(workDate);
	  passengersMapper.deleteByDate(workDate);
	  loader.prepareData(workDate);
   }

   /**
	* Завершить обмен.
	*
	* @param workDate дата
	*/
   public void flush(Date workDate) {
	  try {
		 ticketCheckByHourMapper.insertByWorkDate(workDate, workDate);
		 ticketCheckByHourMapper.updateRoutesId(workDate, workDate);
		 ticketCheckByTypeMapper.insertByWorkDate(workDate);
		 ticketCheckByTypeMapper.updateRoutesId(workDate);
		 passengersMapper.insertFromTicketChecks(workDate);
		 loader.flush(workDate);
	  } catch (Exception e) {
		 System.out.println(e);
	  }

   }
}
