package ru.armd.pbk.matcher.plans;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.plans.brigades.Brigade;
import ru.armd.pbk.domain.plans.brigades.PlanBrigade;
import ru.armd.pbk.dto.plans.brigades.BrigadeDTO;
import ru.armd.pbk.enums.nsi.Shift;
import ru.armd.pbk.views.plans.brigades.BrigadeVenueView;
import ru.armd.pbk.views.plans.brigades.BrigadeView;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Преобразует входные данные для получения view бригад плана отдела.
 */
@Mapper
public abstract class PlanBrigadeMatcher
	implements IDomainMatcher<Brigade, BrigadeDTO> {

   public static final PlanBrigadeMatcher INSTANCE = Mappers.getMapper(PlanBrigadeMatcher.class);

   /**
	* Получает из домена смены бригады плана отдела view.
	*
	* @param brigade домен смены бригады плана отдела
	* @return view смены бригады плана отдела
	*/
   abstract BrigadeView getBrigadeView(Brigade brigade);

   /**
	* Получает из домена бригад плана отдела view.
	*
	* @param planBrigade домен бригад плана отдела
	* @return view бригад плана отдела
	*/
   public List<BrigadeVenueView> getBrigadeVenueView(PlanBrigade planBrigade) {
	  if (planBrigade != null) {
		 List<BrigadeVenueView> brigadeVenueViews = initBrigadeVenueViewList(planBrigade.getBrigadeVenues());
		 if (planBrigade.getBrigades() != null && planBrigade.getBrigades().size() > 0) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			for (Brigade brigade : planBrigade.getBrigades()) {
			   for (BrigadeVenueView view : brigadeVenueViews) {
				  if (view.getId().equals(brigade.getCityVenueId()) && ((brigade.getShiftId() == null && !brigade.getIsAgree()) || view.getShiftId().equals(brigade.getShiftId()))) {
					 view.getBrigades().put(df.format(brigade.getPlanDate()), getBrigadeView(brigade));
					 continue;
				  }
			   }
			}
		 }
		 if (brigadeVenueViews.isEmpty()) {
			brigadeVenueViews.add(new BrigadeVenueView());
			brigadeVenueViews.get(0).setCnt(0L);
		 }
		 brigadeVenueViews.get(0).setReserveCounts(planBrigade.getReserveCounts());
		 return brigadeVenueViews;
	  }
	  return null;
   }

   /**
	* Начальная инициализация view бригад плана отдела.
	*
	* @param brigadeVenues список views
	* @return список проинициализированных views
	*/
   private List<BrigadeVenueView> initBrigadeVenueViewList(List<BrigadeVenueView> brigadeVenues) {
	  if (brigadeVenues == null) {
		 return null;
	  }
	  List<BrigadeVenueView> nights = new LinkedList<BrigadeVenueView>();
	  List<BrigadeVenueView> result = new LinkedList<BrigadeVenueView>();
	  for (BrigadeVenueView brigadeVenue : brigadeVenues) {
		 brigadeVenue.setBrigades(new HashMap<String, BrigadeView>());
		 if (Shift.NIGHT.getId().equals(brigadeVenue.getShiftId())) {
			nights.add(brigadeVenue);
		 } else {
			result.add(brigadeVenue);
		 }
	  }
	  result.addAll(nights);
	  return result;
   }

}
