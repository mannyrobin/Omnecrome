package ru.armd.pbk.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.enums.nsi.Shift;
import ru.armd.pbk.repositories.So20Repository;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.report.So20CheckPlanView;
import ru.armd.pbk.views.report.So20CheckResultView;
import ru.armd.pbk.views.report.So20MediumResultView;
import ru.armd.pbk.views.report.So20MergedCheckPlanView;
import ru.armd.pbk.views.report.So20MergedCheckResultView;
import ru.armd.pbk.views.report.SoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by Yakov Volkov.
 */
@Service
public class So20Service
	extends BaseDomainService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(So20Service.class);

   @Autowired
   So20Service(So20Repository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public <Views extends BaseGridView, Params extends BaseGridViewParams> JsonGridData getGridViews(Params params) {

	  List<SoView> resultViews = new ArrayList<>();

	  processCheckResultViews(params, resultViews);
	  processCheckPlanViews(resultViews);

	  return createJsonGridData(resultViews, params.getPage(), params.getCount());
   }

   private <Params extends BaseGridViewParams> void processCheckResultViews(Params params, List<SoView> resultViews) {
	  int counter = 0;
	  List<So20CheckResultView> views = domainRepository.getGridViews(params);
	  So20MergedCheckResultView lastView = null;
	  for (So20CheckResultView view : views) {

		 if (lastView != null && !lastView.getRouteNumber().equals(view.getRouteNumber())) {
			addMediumResult(resultViews, counter, lastView);
			counter = 0;
		 }

		 if (lastView != null && lastView.getFio().equals(view.getFio()) &&
			 lastView.getRouteNumber().equals(view.getRouteNumber())) {

			lastView.getCheckTimes().add(view.getCheckTime());
			lastView.getMocs().add(view.getMoc());
			lastView.getShifts().add(view.getShift());
			lastView.getMoveCodes().add(view.getMoveCode());
			lastView.getNumbers().add(++counter);
		 } else {
			lastView = createMergedCheckResultView(++counter, view);
			resultViews.add(lastView);
		 }
	  }
	  if (!views.isEmpty()) {
		 addMediumResult(resultViews, counter, lastView);
	  }
   }

   private void processCheckPlanViews(List<SoView> resultViews) {
	  So20MergedCheckPlanView lastView = null;
	  for (So20CheckPlanView so20CheckPlanView : ((So20Repository) domainRepository).getCheckPlanView()) {
		 if (lastView != null && lastView.getRouteNumber().equals(so20CheckPlanView.getRouteNumber())) {

			lastView.getCheckDates().add(so20CheckPlanView.getCheckDate());
			lastView.getMocs().add(so20CheckPlanView.getMoc());
			lastView.getShifts().add(so20CheckPlanView.getShift());

		 } else {
			lastView = createMergedCheckPlanView(so20CheckPlanView);
			resultViews.add(lastView);
		 }
	  }
   }

   private So20MergedCheckPlanView createMergedCheckPlanView(So20CheckPlanView so20CheckPlanView) {
	  So20MergedCheckPlanView so20MergedCheckPlanView = new So20MergedCheckPlanView();
	  so20MergedCheckPlanView.setCnt(0L);
	  so20MergedCheckPlanView.setRouteNumber(so20CheckPlanView.getRouteNumber());
	  so20MergedCheckPlanView.getCheckDates().add(so20CheckPlanView.getCheckDate());
	  so20MergedCheckPlanView.getShifts().add(so20CheckPlanView.getShift());
	  so20MergedCheckPlanView.getMocs().add(so20CheckPlanView.getMoc());

	  return so20MergedCheckPlanView;
   }


   private So20MergedCheckResultView createMergedCheckResultView(int counter, So20CheckResultView view) {
	  So20MergedCheckResultView so20MergedCheckResultView = new So20MergedCheckResultView();

	  so20MergedCheckResultView.setCnt(view.getCnt());

	  so20MergedCheckResultView.setRouteNumber(view.getRouteNumber());
	  so20MergedCheckResultView.setFio(view.getFio());
	  so20MergedCheckResultView.setScm(view.getScm());
	  so20MergedCheckResultView.setScmo(view.getScmo());
	  so20MergedCheckResultView.setVesb(view.getVesb());
	  so20MergedCheckResultView.setOther(view.getOther());

	  so20MergedCheckResultView.getCheckTimes().add(view.getCheckTime());
	  so20MergedCheckResultView.getMocs().add(view.getMoc());
	  so20MergedCheckResultView.getShifts().add(view.getShift());
	  so20MergedCheckResultView.getMoveCodes().add(view.getMoveCode());
	  so20MergedCheckResultView.getNumbers().add(counter);
	  return so20MergedCheckResultView;
   }

   private void addMediumResult(List<SoView> resultViews, int counter, So20MergedCheckResultView lastView) {
	  So20MediumResultView mediumResult = new So20MediumResultView();
	  mediumResult.setCheckCount(counter);
	  mediumResult.setRouteNumber(lastView.getRouteNumber());

	  ListIterator li = resultViews.listIterator(resultViews.size());

	  int scmSum = 0;
	  int scmoSum = 0;
	  int vesbSum = 0;
	  int otherSum = 0;

	  Map<String, Integer> shiftMap = new HashMap<String, Integer>() {{
		 for (Shift shift : Shift.values()) {
			put(shift.getName(), 0);
		 }
	  }};

	  while (li.hasPrevious()) {
		 Object prev = li.previous();
		 if (prev instanceof So20MergedCheckResultView) {
			So20MergedCheckResultView o = (So20MergedCheckResultView) prev;
			scmSum += o.getScm();
			scmoSum += o.getScmo();
			vesbSum += o.getVesb();
			otherSum += o.getOther();

			for (String shift : o.getShifts()) {
			   shiftMap.put(shift, shiftMap.get(shift) + 1);
			}

		 } else {
			break;
		 }
	  }

	  mediumResult.setScmSum(scmSum);
	  mediumResult.setScmoSum(scmoSum);
	  mediumResult.setVesbSum(vesbSum);
	  mediumResult.setOtherSum(otherSum);
	  mediumResult.setShiftResult(createShiftResult(shiftMap));

	  resultViews.add(mediumResult);
   }

   private String createShiftResult(Map<String, Integer> shiftMap) {

	  StringBuilder sb = new StringBuilder();

	  boolean first = true;

	  Integer iCount = shiftMap.get(Shift.I.getName());
	  if (iCount > 0) {
		 sb.append(iCount).append("(I)");
		 first = false;
	  }

	  Integer iiCount = shiftMap.get(Shift.II.getName());
	  if (iiCount > 0) {
		 if (!first) {
			sb.append("/");
		 }
		 sb.append(iiCount).append("(II)");
		 first = false;
	  }

	   Integer iiiCount = shiftMap.get(Shift.III.getName());
	   if (iiiCount > 0) {
		   if (!first) {
			   sb.append("/");
		   }
		   sb.append(iiiCount).append("(III)");
		   first = false;
	   }

	  Integer dayCount = shiftMap.get(Shift.DAY.getName());
	  if (dayCount > 0) {
		 if (!first) {
			sb.append("/");
		 }
		 sb.append(dayCount).append("(д)");
		 first = false;
	  }

	  Integer nightCount = shiftMap.get(Shift.NIGHT.getName());
	  if (nightCount > 0) {
		 if (!first) {
			sb.append("/");
		 }
		 sb.append(nightCount).append("(н)");
		 first = false;
	  }

	  Integer otherCount = shiftMap.get(Shift.OTHER.getName());
	  if (otherCount > 0) {
		 if (!first) {
			sb.append("/");
		 }
		 sb.append(otherCount).append("(иное)");
	  }

	  return sb.toString();
   }
}