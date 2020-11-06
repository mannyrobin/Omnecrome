package ru.armd.pbk.matchers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.So2DomainView;
import ru.armd.pbk.domain.So2ShiftHours;
import ru.armd.pbk.domain.plans.routes.RouteRatio;
import ru.armd.pbk.dto.plans.routes.RouteRatioDTO;
import ru.armd.pbk.views.report.So2EmployeeView;
import ru.armd.pbk.views.report.So2ShiftHoursView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Преобразует входные данные для получения view стандартного отчета №2.
 */
@Mapper
public abstract class So2Matcher
	implements IDomainMatcher<RouteRatio, RouteRatioDTO> {

   public static final So2Matcher INSTANCE = Mappers.getMapper(So2Matcher.class);

   /**
	* Получает из домена смены стандартного отчета №2 view.
	*
	* @param shift домен смены стандартного отчета №2
	* @return view view
	*/
   abstract So2ShiftHoursView getShiftView(So2ShiftHours shift);

   /**
	* Получает из домена стандартного отчета №2 view.
	*
	* @param so2DomainView домен стандартного отчета №2
	* @return view view
	*/
   public List<So2EmployeeView> getSo2Views(So2DomainView so2DomainView) {
	  if (so2DomainView != null) {
		 List<So2EmployeeView> so2DomainViews = initPlanRouteViewList(so2DomainView.getEmployees());
		 if (so2DomainView.getShifts() != null && so2DomainView.getShifts().size() > 0) {
			SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
			for (So2ShiftHours shift : so2DomainView.getShifts()) {
			   for (So2EmployeeView view : so2DomainViews) {
				  if (view.getId().equals(shift.getEmployeeId())) {
					 view.getShifts().put(df.format(shift.getDate()), getShiftView(shift));
					 continue;
				  }
			   }
			}
		 }
		 if (so2DomainViews.isEmpty()) {
			so2DomainViews.add(new So2EmployeeView());
			so2DomainViews.get(0).setCnt(0L);
		 }
		 so2DomainViews.get(0).setStartDate(so2DomainView.getStartDate());
		 so2DomainViews.get(0).setEndDate(so2DomainView.getEndDate());
		 return so2DomainViews;
	  }
	  return null;
   }

   private List<So2EmployeeView> initPlanRouteViewList(List<So2EmployeeView> employeeViews) {
	  if (employeeViews == null) {
		 return null;
	  }
	  for (So2EmployeeView employeeView : employeeViews) {
		 employeeView.setShifts(new HashMap<String, So2ShiftHoursView>());
	  }
	  return employeeViews;
   }
}
