package ru.armd.pbk.mappers.nsi;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.Telematics;

import java.util.List;

/**
 * Маппер телематики.
 */
@IsMapper
public interface TelematicsMapper
	extends IDomainMapper<Telematics> {

   /**
	* Множественная вставка данных
	* из списка {@code list}.
	*
	* @param list список.
	*/
   void insertBulk(List<Telematics> list);
}
