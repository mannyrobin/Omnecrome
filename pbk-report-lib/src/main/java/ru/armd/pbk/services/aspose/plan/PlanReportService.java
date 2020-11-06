package ru.armd.pbk.services.aspose.plan;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportData;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.plan.BrigadeGraphReportBeanData;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.plans.brigades.Brigade;
import ru.armd.pbk.enums.nsi.Shift;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.exceptions.PBKReportException;
import ru.armd.pbk.mappers.plan.PlansReportMapper;
import ru.armd.pbk.repositories.plans.brigades.PlanBrigadeRepository;
import ru.armd.pbk.services.reports.AsposeReportsService;
import ru.armd.pbk.views.plan.BrigadeGraphPlanVenuesView;
import ru.armd.pbk.views.plan.BrigadeGraphVenuesView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Сервис генерации печатных форм планирования.
 */
@Service("planReportingService")
public class PlanReportService
	extends AsposeReportsService {

   private static final Logger LOGGER = Logger.getLogger(PlanReportService.class);

   private PlansReportMapper reportMapper;


   private PlanBrigadeRepository reportRepository;

   /**
	* Конструктор по мапперу планирования.
	*
	* @param repository репозиторий планирования
	*/
   @Autowired
   public PlanReportService(PlanBrigadeRepository repository, PlansReportMapper mapper) {
	  this.reportRepository = repository;
	  this.reportMapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Генерирует печатную форму задания.
	*
	* @param type     свойства выходной печатной формы
	* @param deptId   ИД подразделения.
	* @param fromDate дата начала
	* @param toDate   дата конца
	* @return результат
	* @throws PBKAsposeReportException
	*/
   public Map<ReportFormat, File> generate(IReportType type, Long deptId, Date fromDate, Date toDate)
	   throws PBKAsposeReportException {
	  return generateReport(type, new ReportData(new BrigadeGraphReportBeanData(fromDate, toDate,
		  getVenuesView(deptId, fromDate, toDate), getPlanVenuesView(deptId, fromDate, toDate))));
   }

   /**
	* Получение мест встреч.
	*
	* @param deptId   ИД подразделения
	* @param fromDate дата начала
	* @param toDate   дата конца
	* @return
	*/
   private List<BrigadeGraphVenuesView> getVenuesView(Long deptId, Date fromDate, Date toDate) {
	  List<BrigadeGraphVenuesView> venues = reportMapper.getBrigadeGraphVenues(deptId, fromDate, toDate);
	  if (venues == null) {
		 throw new PBKReportException("Места встреч не найдены в БД");
	  }
	  return divNightBrigadeGraphVenuesView(venues);
   }

   private List<BrigadeGraphVenuesView> divNightBrigadeGraphVenuesView(List<BrigadeGraphVenuesView> venues) {
	  List<BrigadeGraphVenuesView> result = new ArrayList<BrigadeGraphVenuesView>();
	  Map<String, BrigadeGraphVenuesView> night = new LinkedHashMap<String, BrigadeGraphVenuesView>();
	  for (BrigadeGraphVenuesView venue : venues) {
		 if (Shift.NIGHT.getId().equals(venue.getShiftId())) {
			if (!night.containsKey(venue.getVenueName())) {
			   venue.setDeptName("Ночные");
			   venue.setDeptId(-1l);
			   night.put(venue.getVenueName(), venue);
			}
		 } else {
			result.add(venue);
		 }
	  }
	  result.addAll(night.values());
	  return result;
   }

   /**
	* Получение бригад мест встреч.
	*
	* @param deptId   ИД подразделения
	* @param fromDate дата начала
	* @param toDate   дата конца
	* @return
	*/
   private List<BrigadeGraphPlanVenuesView> getPlanVenuesView(Long deptId, Date fromDate, Date toDate) {
	  List<BrigadeGraphPlanVenuesView> planVenues = new LinkedList<BrigadeGraphPlanVenuesView>();
	  List<Long> deptIds = deptId == null ? reportMapper.getPlanDeptId(fromDate, toDate) : Arrays.asList(deptId);
	  for (Long id : deptIds) {
		 BaseGridViewParams params = getReportParams(id, fromDate, toDate);
		 if (params == null) {
			continue;
		 }
		 for (Brigade brigade : reportRepository.getPlanBrigades(params).getBrigades()) {
			planVenues.add(createView(brigade));
		 }
	  }
	  return divBrigadeGraphPlanVenuesView(planVenues);
   }

   private BrigadeGraphPlanVenuesView createView(Brigade brigade) {
	  BrigadeGraphPlanVenuesView view = new BrigadeGraphPlanVenuesView();
	  view.setCityVenueId(brigade.getCityVenueId());
	  view.setDeptId(brigade.getDeptId());
	  view.setGkuopCount(brigade.getGkuopCount());
	  view.setId(brigade.getId());
	  view.setIsAgree(brigade.getIsAgree());
	  view.setIsHaveFreeControllers(brigade.isHaveFreeControlers());
	  view.setIsNotFull(brigade.isNotFull());
	  view.setMgtCount(brigade.getMgtCount());
	  view.setPlanDate(brigade.getPlanDate());
	  view.setShiftId(brigade.getShiftId());
	  return view;
   }

   private BaseGridViewParams getReportParams(Long deptId, Date fromDate, Date toDate) {
	  Map<String, Object> filter = new HashMap<String, Object>();
	  filter.put("deptId", deptId);
	  filter.put("dateFrom", fromDate);
	  filter.put("dateTo", toDate);
	  return new BaseGridViewParams(1L, Long.valueOf(Integer.MAX_VALUE), "venueName", "asc", filter);
   }

   private List<BrigadeGraphPlanVenuesView> divBrigadeGraphPlanVenuesView(List<BrigadeGraphPlanVenuesView> planVenues) {
	  List<BrigadeGraphPlanVenuesView> result = new ArrayList<BrigadeGraphPlanVenuesView>();
	  Map<Long, LinkedHashMap<Date, BrigadeGraphPlanVenuesView>> night = new LinkedHashMap<Long, LinkedHashMap<Date, BrigadeGraphPlanVenuesView>>();
	  for (BrigadeGraphPlanVenuesView brg : planVenues) {
		 if (Shift.NIGHT.getId().equals(brg.getShiftId())) {
			BrigadeGraphPlanVenuesView brigade = brg.cloneBrigade();
			brigade.setDeptId(-1L);
			if (!night.containsKey(brigade.getCityVenueId())) {
			   night.put(brigade.getCityVenueId(), new LinkedHashMap<Date, BrigadeGraphPlanVenuesView>());
			}
			if (!night.get(brigade.getCityVenueId()).containsKey(brigade.getPlanDate())) {
			   night.get(brigade.getCityVenueId()).put(brigade.getPlanDate(), brigade);
			} else {
			   BrigadeGraphPlanVenuesView view = night.get(brigade.getCityVenueId()).get(brigade.getPlanDate());
			   view.setMgtCount(view.getMgtCount() + brigade.getMgtCount());
			   view.setGkuopCount(view.getGkuopCount() + brigade.getGkuopCount());
			   view.setIsHaveFreeControllers(view.getIsHaveFreeControllers() || brigade.getIsHaveFreeControllers());
			   view.setIsNotFull(view.getIsNotFull() || brigade.getIsNotFull());
			   view.setIsAgree(view.getIsAgree() && brigade.getIsAgree());
			   night.get(brigade.getCityVenueId()).put(brigade.getPlanDate(), view);
			}
		 }
		 result.add(brg);
	  }
	  for (LinkedHashMap<Date, BrigadeGraphPlanVenuesView> brigades : night.values()) {
		 result.addAll(brigades.values());
	  }
	  return result;
   }
}