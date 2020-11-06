package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.district.District;
import ru.armd.pbk.domain.nsi.district.DistrictRoute;
import ru.armd.pbk.dto.nsi.district.DistrictDTO;
import ru.armd.pbk.dto.nsi.district.DistrictRouteDTO;

/**
 * Мапер для сопостовления различных типов сущности "Округ".
 */
@Mapper
public interface IDistrictMatcher
	extends IDomainMatcher<District, DistrictDTO> {

   IDistrictMatcher INSTANCE = Mappers.getMapper(IDistrictMatcher.class);

   /**
	* Перевод ДТО маршрута округа в домен маршрута округа.
	*
	* @param dto - ДТО маршрут округа.
	* @return домен маршрута округа.
	*/
   DistrictRoute toDomain(DistrictRouteDTO dto);

   /**
	* Перевод домена маршрута округа в ДТО маршрута округа.
	*
	* @param domain - домен маршрута округа.
	* @return - ДТО маршрута округа.
	*/
   @Mappings( {@Mapping(target = "routeIds", ignore = true)})
   DistrictRouteDTO toDTO(DistrictRoute domain);
}
