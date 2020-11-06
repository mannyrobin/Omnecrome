package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Calendar;
import ru.armd.pbk.dto.nsi.CalendarDTO;

import java.util.List;

/**
 * Мапер для сопостовления различных типов сущности "календарь".
 */
@Mapper
public interface ICalendarMatcher
	extends IDomainMatcher<Calendar, CalendarDTO> {

   ICalendarMatcher INSTANCE = Mappers.getMapper(ICalendarMatcher.class);

   /**
	* Преобразовать список доменов в список DTO.
	*
	* @param domainList список доменов
	* @return список DTO
	*/
   List<CalendarDTO> domainListToDtoList(List<Calendar> domainList);

   /**
	* Преобразовать список DTO в список доменов.
	*
	* @param dtoList список DTO
	* @return список доменов
	*/
   List<Calendar> dtoListToDomainList(List<CalendarDTO> dtoList);
}
