package ru.armd.pbk.mappers.nsi.stop;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.domain.nsi.stop.Stop;

/**
 * Мапер для операций с остановками.
 */
@IsMapper
public interface StopMapper
	extends IVersionDomainMapper<Stop> {

   /**
	* Обновить ИД АСДУ.
	*
	* @param domain домен.
	* @return
	*/
   int updateAsduId(Stop domain);

}
