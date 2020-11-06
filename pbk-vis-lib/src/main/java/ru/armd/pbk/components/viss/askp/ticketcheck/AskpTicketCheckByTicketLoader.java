package ru.armd.pbk.components.viss.askp.ticketcheck;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.domain.nsi.Ticket;
import ru.armd.pbk.domain.nsi.askp.TicketCheck;
import ru.armd.pbk.domain.nsi.askp.TicketCheckByTicket;
import ru.armd.pbk.mappers.nsi.askp.TicketCheckByTicketMapper;
import ru.armd.pbk.repositories.nsi.TicketRepository;
import ru.armd.pbk.utils.date.DateUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

@Component
@Scope("prototype")
public class AskpTicketCheckByTicketLoader
	extends BaseComponent {

   public static final String FIELD_SEPARATOR = ";";

   private static final int FLUSH_COUNT = 299;
   private final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd");
   private final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

   private Map<String, Map<Integer, Integer>> cache = new HashMap<String, Map<Integer, Integer>>();
   private Map<String, Ticket> ticketCache = null;

   @Autowired
   private TicketRepository ticketRepository;

   @Autowired
   private TicketCheckByTicketMapper mapper;

   /**
	* Обработка проверки билета.
	*
	* @param domain проверка билета.
	*/
   public void processTicketCheck(TicketCheck domain) {
	  String key = getKey(domain);
	  if (!cache.containsKey(key)) {
		 cache.put(key, new TreeMap<Integer, Integer>());
	  }
	  putCache(cache.get(key), domain);
   }

   /**
	* Сохранить данные из кэша в БД.
	*
	* @param workDate дата сохранения.
	*/
   public void flush(Date workDate) {
	  List<TicketCheckByTicket> cacheQueue = new ArrayList<TicketCheckByTicket>();
	  for (Entry<String, Map<Integer, Integer>> entry : cache.entrySet()) {
		 cacheQueue.add(create(entry));
		 if (cacheQueue.size() >= FLUSH_COUNT) {
			mapper.insertChunck(cacheQueue);
			cacheQueue.clear();
		 }
	  }
	  if (cacheQueue.size() > 0) {
		 mapper.insertChunck(cacheQueue);
		 cacheQueue.clear();
	  }
	  cache.clear();
	  mapper.updateRoutesId(workDate);
   }

   /**
	* Подготовка данных на дату.
	* Данный метод вызывается перед началом обработки.
	*
	* @param workDate дата выполнения.
	*/
   public void prepareData(Date workDate) {
	  for (TicketCheckByTicket item : mapper.getByWorkDate(workDate)) {
		 cache.put(String.format("%s:%s:%s:%s", DATE_FORMAT.format(item.getWorkDate()), item.getTicketCode(),
			 item.getTicketCode(), item.getAskpRouteCode(), item.getMoveCode()), toHours(item.getAskpChecksCounties()));
	  }
	  mapper.deleteByWorkDate(workDate);
   }

   private Map<Integer, Integer> toHours(String counts) {
	  Map<Integer, Integer> result = new TreeMap<Integer, Integer>();
	  int hour = 0;
	  for (String count : counts.split(",")) {
		 result.put(hour, Integer.parseInt(count));
		 hour++;
	  }
	  return result;
   }

   private String getCountByHourString(Map<Integer, Integer> countByHour) {
	  List<Integer> countes = new LinkedList<Integer>();
	  Integer last = 0;
	  for (Entry<Integer, Integer> entry : countByHour.entrySet()) {
		 for (; last.compareTo(entry.getKey()) < 0; last++) {
			countes.add(0);
		 }
		 countes.add(entry.getValue());
		 last++;
	  }
	  for (int i = last; i < 24; i++) {
		 countes.add(0);
	  }
	  return StringUtils.join(countes, ",");
   }

   private String getKey(TicketCheck domain) {
	  return String.format("%s:%s:%s:%s", DATE_FORMAT.format(domain.getWorkDate()), domain.getTicketCode(), domain.getAskpRouteCode(), domain.getMoveCode());
   }

   private void putCache(Map<Integer, Integer> cache, TicketCheck domain) {
	  Calendar checkTime = Calendar.getInstance();
	  checkTime.setTime(domain.getCheckTime());
	  int key = checkTime.get(Calendar.HOUR_OF_DAY);
	  int count = cache.containsKey(key) ? cache.get(key) : 0;
	  cache.put(key, count + 1);
   }

   private TicketCheckByTicket create(Entry<String, Map<Integer, Integer>> entry) {
	  try {
		 TicketCheckByTicket domain = new TicketCheckByTicket();
		 String[] fields = entry.getKey().split(":");
		 domain.setWorkDate(DATE_FORMAT.parse(fields[0]));
		 Ticket ticket = getTicket(fields[1]);
		 if (ticket != null) {
			domain.setTicketCode(ticket.getCode());
			domain.setTicketName(ticket.getName());
		 }
		 domain.setAskpRouteCode(fields[2]);
		 domain.setMoveCode(fields[3]);
		 domain.setAskpChecksCounties(getCountByHourString(entry.getValue()));
		 return domain;
	  } catch (ParseException e) {
		 return null;
	  }
   }

   /**
	* Загрузка данных из файла на дату.
	*
	* @param is       входной поток файла.
	* @param workDate дата.
	* @throws IOException
	*/
   public void importFile(InputStream is, Date workDate)
	   throws IOException {
	  prepareData(workDate);
	  BufferedReader brFile = new BufferedReader(new InputStreamReader(is));
	  String line = null;
	  while ((line = brFile.readLine()) != null) {
		 doProcessLine(line);
	  }
	  flush(workDate);
   }

   protected Ticket getTicket(String key) {
	  if (ticketCache == null) {
		 ticketCache = new HashMap<String, Ticket>();
		 Map<String, Object> params = new HashMap<String, Object>();
		 List<Ticket> tickets = ticketRepository.getDomains(params);
		 for (Ticket ticket : tickets) {
			ticketCache.put(ticket.getCode(), ticket);
		 }
	  }
	  return ticketCache.get(key);
   }

   protected void doProcessLine(String line) {
	  TicketCheck domain = new TicketCheck();
	  String[] fields = line.split(FIELD_SEPARATOR);
	  try {
		 domain.setAskpRouteCode(fields[0]);
		 domain.setMoveCode(fields[1]);
		 domain.setTicketCode(fields[2]);
		 Date parse = DATE_TIME_FORMAT.parse(fields[3]);
		 domain.setWorkDate(DateUtils.shiftToDayStart(parse));
		 domain.setCheckTime(parse);
		 domain.setAskpCheckId(fields[4]);
		 processTicketCheck(domain);
	  } catch (ParseException e) {
		 LOGGER.error(e.getMessage(), e);
	  }
   }
}
