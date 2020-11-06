package ru.armd.pbk.components.viss.askp.contactlesscard;

import com.aspose.cells.Row;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseXlsxLoader;
import ru.armd.pbk.domain.nsi.Route;
import ru.armd.pbk.domain.nsi.RouteTsKind;
import ru.armd.pbk.domain.tasks.AskpContactlessCard;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.nsi.RouteRepository;
import ru.armd.pbk.repositories.nsi.RouteTsKindRepository;
import ru.armd.pbk.repositories.tasks.AskpContactlessCardRepository;
import ru.armd.pbk.services.tasks.TasksService;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Лоадер для БСК.
 */
@Component
public class AskpContactlessCardLoader
	extends BaseXlsxLoader<AskpContactlessCard> {

   private static final Logger LOGGER = Logger.getLogger(AskpContactlessCardLoader.class);
   private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yy HH:mm:ss");

   private Date date;
   private List<AskpContactlessCard> cache = new LinkedList<AskpContactlessCard>();
   private AskpContactlessCardRepository repository;
   private List<AskpContactlessCard> olds = null;

   @Autowired
   private RouteTsKindRepository tsRepository;

   @Autowired
   private RouteRepository routeRepository;

   @Autowired
   private TasksService tasksService;

   private Map<String, Long> typeCache = new HashMap<String, Long>();
   private Map<Long, Map<String, Route>> routeCache = new HashMap<Long, Map<String, Route>>();
   private Map<String, String> askpToPbkType = new HashMap<String, String>();

   /**
	* Конструктор.
	*
	* @param domainRepository - репозиторий.
	*/
   @Autowired
   public AskpContactlessCardLoader(AskpContactlessCardRepository domainRepository) {
	  super(domainRepository, VisAuditType.VIS_ASKP_CONTACTLESS_CARDS);
	  repository = domainRepository;
   }

   /**
	* Загрузить файл на дату.
	*
	* @param is   файл.
	* @param date дата.
	* @return
	*/
   public ImportResult<AskpContactlessCard> importFile(InputStream is, Date date) {
	  this.date = date;
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("workDate", date);
	  olds = repository.getDomains(params);

	  askpToPbkType.put("А", "1");
	  askpToPbkType.put("Тб", "2");
	  askpToPbkType.put("Тр", "3");
	  askpToPbkType.put("СТр", "4");

	  for (RouteTsKind ts : tsRepository.getDomains(null)) {
		 typeCache.put(ts.getCod(), ts.getId());
		 routeCache.put(ts.getId(), new HashMap<String, Route>());
	  }
	  for (Route route : routeRepository.getDomains(null)) {
		 routeCache.get(route.getRouteTsKindId()).put(route.getRouteNumber().toLowerCase(), route);
	  }
	  return super.importFile(is);
   }

   @Override
   protected AskpContactlessCard createDomain(Row row) {
	  if (row.getCellByIndex(0).getStringValue().isEmpty()
		  || row.getCellByIndex(1).getStringValue().isEmpty()
		  || row.getCellByIndex(2).getStringValue().isEmpty()
		  || row.getCellByIndex(3).getStringValue().isEmpty()
		  || row.getCellByIndex(4).getStringValue().isEmpty()) {
		 return null;
	  }
	  AskpContactlessCard domain = new AskpContactlessCard();
	  domain.setWorkDate(date);
	  domain.setCardNumber(row.getCellByIndex(0).getStringValue());
	  try {
		 domain.setCheckTime(DATE_TIME_FORMAT.parse(row.getCellByIndex(1).getStringValue()));
	  } catch (Throwable t) {
		 LOGGER.error(t);
	  }
	  domain.setParkName(row.getCellByIndex(2).getStringValue());
	  String[] r = row.getCellByIndex(3).getStringValue().split("_");
	  if (r.length == 2) {
		 domain.setRouteNumber(r[1]);
		 Route route = getRoute(r[0], r[1]);
		 domain.setRouteId(route == null ? null : route.getHeadId());
	  } else {
		 domain.setRouteNumber(row.getCellByIndex(3).getStringValue());
	  }
	  domain.setMoveCode(row.getCellByIndex(4).getStringValue());
	  return domain;
   }

   @Override
   protected AskpContactlessCard doProcessElement(Row row) {
	  AskpContactlessCard domain = createDomain(row);
	  if (domain != null) {
		 cache.add(domain);
		 if (cache.size() > 100) {
			saveCache();
		 }
	  }
	  return domain;
   }

   @Override
   protected void afterLastRow() {
	  if (cache.size() > 0) {
		 saveCache();
	  }

	  repository.updateTasks(date);
	  tasksService.processASKP(date);
   }

   protected void saveCache() {
	  if (olds != null && olds.size() > 0) {
		 List<AskpContactlessCard> removeOld = new LinkedList<AskpContactlessCard>();
		 List<AskpContactlessCard> removeCache = new LinkedList<AskpContactlessCard>();
		 for (int i = 0; i < cache.size(); i++) {
			String cardNumber = cache.get(i).getCardNumber();
			int j = 0;
			Boolean found = false;
			for (; j < olds.size() && !found; j++) {
			   found = olds.get(j).getCardNumber().equals(cardNumber);
			}
			if (found) {
			   for (; i < cache.size(); i++) {
				  AskpContactlessCard cacheItem = cache.get(i);
				  if (!cacheItem.getCardNumber().equals(cardNumber)) {
					 --i;
					 break;
				  }
				  for (int k = j - 1; k < olds.size(); k++) {
					 AskpContactlessCard oldsItem = olds.get(k);
					 if (!oldsItem.getCardNumber().equals(cardNumber)) {
						break;
					 }
					 if (cacheItem.getCheckTime().equals(oldsItem.getCheckTime())
						 && cacheItem.getParkName().equals(oldsItem.getParkName())
						 && cacheItem.getRouteNumber().equals(oldsItem.getRouteNumber())
						 && cacheItem.getMoveCode().equals(oldsItem.getMoveCode())) {
						removeOld.add(oldsItem);
						removeCache.add(cacheItem);
						break;
					 }
				  }
			   }
			}
		 }
		 cache.removeAll(removeCache);
		 olds.removeAll(removeOld);
	  }
	  if (cache.size() > 0) {
		 try {
			repository.save(cache, null);
		 } finally {
			cache.clear();
		 }
	  }
   }

   @Override
   protected AskpContactlessCard getExistedDomain(AskpContactlessCard newDomain) {
	  return null;
   }

   @Override
   protected int getFirstSkipRows() {
	  return 6;
   }

   @Override
   protected int getPage() {
	  return 1;
   }

   @Override
   protected Boolean checkPageName(String name) {
	  DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	  return df.format(date).equals(name);
   }

   protected Route getRoute(String type, String route) {
	  if (askpToPbkType.containsKey(type)) {
		 return routeCache.get(typeCache.get(askpToPbkType.get(type))).get(route.toLowerCase());
	  }
	  return null;
   }
}
