package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.domain.nsi.stop.Stop;
import ru.armd.pbk.domain.nsi.stop.StopPlace;
import ru.armd.pbk.dto.nsi.StopDTO;
import ru.armd.pbk.utils.WktGeomPoint;
import ru.armd.pbk.utils.WktGeomUtils;

/**
 * Мапер для сопостовления различных типов сущности "Остановочный пункт".
 */
@Mapper
public abstract class IStopMatcher {

   public static final IStopMatcher INSTANCE = Mappers.getMapper(IStopMatcher.class);

   /**
	* Базовый перевод Домена "Остановочный пункт" в ДТО "Остановочный пункт".
	*
	* @param domain - Домен "Остановочный пункт"
	* @return ДТО "Остановочный пункт"
	*/
   @Mappings( {
	   @Mapping(target = "longitude", ignore = true),
	   @Mapping(target = "latitude", ignore = true)})
   public abstract StopDTO baseToDTO(Stop domain);

   /**
	* Перевод ДТО "Остановочный пункт" в Домен "Остановочный пункт".
	*
	* @param dto - ДТО "Остановочный пункт"
	* @return Домен "Остановочный пункт"
	*/
   public abstract Stop toDomain(StopDTO dto);

   /**
	* Перевод ДТО "Остановочный пункт" в Домен "Остановочный пункт".
	*
	* @param domain    - Домен "Остановочный пункт"
	* @param stopPlace Домен "Остановочное место"
	* @return ДТО "Остановочный пункт"
	*/
   public StopDTO toDTO(Stop domain, StopPlace stopPlace) {
	  StopDTO dto = baseToDTO(domain);
	  WktGeomPoint point = WktGeomUtils.stringToWktGeomPoint(stopPlace.getWktGeom());
	  dto.setLatitude(point.getLatitude());
	  dto.setLongitude(point.getLongitude());
	  dto.setRouteNames(domain.getRouteNames());
	  dto.setNameWithoutDistrict(domain.getNameWithoutDistrict());
	  return dto;
   }
}
