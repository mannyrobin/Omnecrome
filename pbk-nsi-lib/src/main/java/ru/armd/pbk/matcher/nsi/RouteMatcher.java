package ru.armd.pbk.matcher.nsi;

import org.apache.commons.lang.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Route;
import ru.armd.pbk.dto.nsi.RouteDTO;
import ru.armd.pbk.views.nsi.route.RouteSelectItem;

import java.util.List;

/**
 * Мапер для сопостовления различных типов сущности "Маршрут".
 */
@Mapper
public abstract class RouteMatcher
	implements IDomainMatcher<Route, RouteDTO> {

   public static final RouteMatcher INSTANCE = Mappers.getMapper(RouteMatcher.class);

   /**
	* Получение названия маршрута, для отображения в выпадающем списке.
	*
	* @param item маршрут
	* @return маршрут с именем для отображения
	*/
   public RouteSelectItem getRouteSelectView(RouteSelectItem item) {
	  item.setName(item.getName() + " (" + item.getEntranceCount() + " вых. " + StringUtils.trimToEmpty(item.getCountyName()) + ")");
	  return item;
   }

   /**
	* Преобреобразование названий списка маршрутов, для отображения в
	* выпадающем списке.
	*
	* @param list список маршрутов
	* @return список маршрутов с именами для отображения
	*/
   public abstract List<RouteSelectItem> getRouteSelectListView(List<RouteSelectItem> list);
}
