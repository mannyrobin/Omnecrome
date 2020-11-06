package ru.armd.pbk.core.matcher;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;

/**
 * Интерфейс конвертатора Доменов в ДТО и наоборот.
 *
 * @param <Domain> Домен.
 * @param <DTO>    ДТО.
 */
public interface IDomainMatcher<Domain extends BaseDomain, DTO extends BaseDTO> {

   /**
	* Метод создания и инициализации домена по данным ДТО.
	*
	* @param dto ДТО.
	* @return Домен.
	*/
   abstract Domain toDomain(DTO dto);

   /**
	* Метод создания и инициализации ДТО по данным домена.
	*
	* @param domain Домен.
	* @return ДТО.
	*/
   abstract DTO toDTO(Domain domain);

}
