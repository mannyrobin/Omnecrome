package ru.armd.pbk.mappers.nsi;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.domain.nsi.Driver;

/**
 * Мапер для операций с водителями.
 */
@IsMapper
public interface DriverMapper
	extends IVersionDomainMapper<Driver> {

   /**
	* Возвращает домен водителя по id из системы асду.
	*
	* @param asduDriverId id водителя из системы асду.
	* @return Домен водителя.
	*/
   Driver getByAsduDriverId(String asduDriverId);
}
