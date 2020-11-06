package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.domain.nsi.district.District;
import ru.armd.pbk.domain.nsi.venue.Venue;
import ru.armd.pbk.domain.nsi.venue.VenueRoute;
import ru.armd.pbk.domain.nsi.venue.VenueRouteType;
import ru.armd.pbk.dto.nsi.venue.VenueDTO;
import ru.armd.pbk.dto.nsi.venue.VenueRouteDTO;
import ru.armd.pbk.dto.nsi.venue.VenueRouteTypeDTO;
import ru.armd.pbk.utils.WktGeomPoint;
import ru.armd.pbk.utils.WktGeomUtils;

/**
 * Мапер для сопостовления различных типов сущностей мест встреч.
 */
@Mapper
public abstract class IVenueMatcher {

   public static final IVenueMatcher INSTANCE = Mappers.getMapper(IVenueMatcher.class);

   /**
	* Базовый перевод ДТО места встречи в домен места встречи.
	*
	* @param dto - ДТО места встречи.
	* @return - домен места встречи.
	*/
   @Mappings( {@Mapping(target = "wktGeom", ignore = true),
	   @Mapping(target = "shiftI", ignore = true),
	   @Mapping(target = "shiftII", ignore = true),
	   @Mapping(target = "shiftIII", ignore = true),
	   @Mapping(target = "shiftDay", ignore = true),
	   @Mapping(target = "tpu", ignore = true),
	   @Mapping(target = "createUserId", ignore = true),
	   @Mapping(target = "createDate", ignore = true),
	   @Mapping(target = "updateUserId", ignore = true),
	   @Mapping(target = "updateDate", ignore = true),
	   @Mapping(target = "shiftNight", ignore = true)})
   public abstract Venue baseToDomain(VenueDTO dto);

   /**
	* Базовый перевод домена места встречи и домена округа в ДТО места встречи.
	*
	* @param domain   - домен места встречи.
	* @param district - домен округа.
	* @return - ДТО места встречи.
	*/
   @Mappings( {@Mapping(target = "id", source = "domain.id"),
	   @Mapping(target = "isDelete", source = "domain.isDelete"),
	   @Mapping(target = "longitude", ignore = true),
	   @Mapping(target = "latitude", ignore = true),
	   @Mapping(target = "name", source = "domain.name"),
	   @Mapping(target = "cod", source = "domain.cod"),
	   @Mapping(target = "description", source = "domain.description"),
	   @Mapping(target = "countyId", source = "district.countyId"),
	   @Mapping(target = "districtId", source = "domain.districtId"),
	   @Mapping(target = "headId", source = "domain.headId"),
	   @Mapping(target = "versionStartDate", source = "domain.versionStartDate"),
	   @Mapping(target = "versionEndDate", source = "domain.versionEndDate"),
	   @Mapping(target = "tpu", ignore = true),
	   @Mapping(target = "shiftI", ignore = true),
	   @Mapping(target = "shiftII", ignore = true),
	   @Mapping(target = "shiftIII", ignore = true),
	   @Mapping(target = "shiftDay", ignore = true),
	   @Mapping(target = "shiftNight", ignore = true)})
   public abstract VenueDTO baseToDTO(Venue domain, District district);

   /**
	* Перевод домена места встречи и домена округа в ДТО места встречи.
	*
	* @param domain   - домен места встречи.
	* @param district - домен округа.
	* @return - ДТО места встречи.
	*/
   public VenueDTO toDTO(Venue domain, District district) {
	  VenueDTO dto = baseToDTO(domain, district);
	  WktGeomPoint point = WktGeomUtils.stringToWktGeomPoint(domain.getWktGeom());
	  dto.setLatitude(point.getLatitude());
	  dto.setLongitude(point.getLongitude());
	  if (domain.isShiftI() == 1) {
		 dto.setShiftI(true);
	  } else {
		 dto.setShiftI(false);
	  }
	  if (domain.isShiftII() == 1) {
		 dto.setShiftII(true);
	  } else {
		 dto.setShiftII(false);
	  }
	   if (domain.isShiftIII() == 1) {
		   dto.setShiftIII(true);
	   } else {
		   dto.setShiftIII(false);
	   }
	  if (domain.isShiftDay() == 1) {
		 dto.setShiftDay(true);
	  } else {
		 dto.setShiftDay(false);
	  }
	  dto.setShiftNight(domain.isShiftNight() == 1);
	  dto.setTpu(domain.getTpu() == 1);
	  return dto;
   }

   /**
	* Перевод ДТО места встречи в домен места встречи.
	*
	* @param dto - ДТО места встречи.
	* @return - домен места встречи.
	*/
   public Venue toDomain(VenueDTO dto) {
	  Venue domain = baseToDomain(dto);
	  domain.setWktGeom(WktGeomUtils.wktGeomPointToString(new WktGeomPoint(dto.getLatitude(), dto.getLongitude())));
	  if (dto.getShiftI()) {
		 domain.setShiftI(1);
	  } else {
		 domain.setShiftI(0);
	  }
	  if (dto.getShiftII()) {
		 domain.setShiftII(1);
	  } else {
		 domain.setShiftII(0);
	  }
	   if (dto.getShiftIII()) {
		   domain.setShiftIII(1);
	   } else {
		   domain.setShiftIII(0);
	   }
	  if (dto.getShiftDay()) {
		 domain.setShiftDay(1);
	  } else {
		 domain.setShiftDay(0);
	  }
	  if (dto.getTpu()) {
		 domain.setTpu(1);
	  } else {
		 domain.setTpu(0);
	  }
	  domain.setShiftNight(dto.getShiftNight() ? 1 : 0);
	  return domain;
   }

   /**
	* Перевод ДТО маршрута места встречи в домен маршрута места встречи.
	*
	* @param dto - ДТО маршрута места встречи.
	* @return - домен маршрута места встречи.
	*/
   public abstract VenueRoute toDomain(VenueRouteDTO dto);

   /**
	* Перевод домена маршрута места встречи в ДТО маршрута места встречи.
	*
	* @param domain - домен маршрута места встречи.
	* @return - ДТО маршрута места встречи.
	*/
   @Mappings( {@Mapping(target = "routeIds", ignore = true)})
   public abstract VenueRouteDTO toDTO(VenueRoute domain);

   /**
	* Перевод ДТО типа маршрута места встречи в домен типа маршрута места встречи.
	*
	* @param dto - ДТО типа маршрута места встречи.
	* @return - домен типа маршрута места встречи.
	*/
   public abstract VenueRouteType toDomain(VenueRouteTypeDTO dto);

   /**
	* Перевод домена типа маршрута места встречи в ДТО типа маршрута места встречи.
	*
	* @param domain - домен типа маршрута места встречи.
	* @return - ДТО типа маршрута места встречи.
	*/
   public abstract VenueRouteTypeDTO toDTO(VenueRouteType domain);

}
