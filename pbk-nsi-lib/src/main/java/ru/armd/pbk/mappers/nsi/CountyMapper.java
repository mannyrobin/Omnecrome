package ru.armd.pbk.mappers.nsi;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.domain.nsi.County;

import java.util.List;

/**
 * Маппер для работы с округами.
 */
@IsMapper
public interface CountyMapper
	extends IVersionDomainMapper<County> {

   /**
	* Получить территорию округа.
	*
	* @param id - ИД округа.
	* @return
	*/
   List<String> getEgko(@Param("id") Long id);

}
