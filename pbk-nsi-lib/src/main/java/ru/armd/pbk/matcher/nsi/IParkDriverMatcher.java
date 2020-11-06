package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.ParkDriver;
import ru.armd.pbk.dto.nsi.ParkDriverDTO;

/**
 * Мапер для водителя парка.
 */
@Mapper
public interface IParkDriverMatcher
	extends IDomainMatcher<ParkDriver, ParkDriverDTO> {

   IParkDriverMatcher INSTANCE = Mappers.getMapper(IParkDriverMatcher.class);

   /**
	* Перевод ДТО ParkDriverDTO в ParkDriver.
	*
	* @param dto - ДТО.
	* @return домен.
	*/
   ParkDriver toDomain(ParkDriverDTO dto);

   /**
	* Перевод домена ParkDriver в ДТО ParkDriverDTO.
	*
	* @param domain - домен.
	* @return - ДТО.
	*/
   @Mappings( {@Mapping(target = "tsDriverIds", ignore = true)})
   ParkDriverDTO toDTO(ParkDriver domain);
}
