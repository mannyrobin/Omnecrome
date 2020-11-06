package ru.armd.pbk.mappers.bsos;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.aspose.nsi.bsos.BsoReportBeanData;
import ru.armd.pbk.core.mappers.IReportMapper;

import java.util.List;

/**
 * Маппер для работы с печатной формой акта об изъятии.
 */
@IsMapper
public interface BsoReportMapper
	extends IReportMapper {

   /**
	* Получить данные актов об изъятии.
	*
	* @param ids список id
	* @return список актов об изъятии
	*/
   List<BsoReportBeanData> getByIds(@Param("ids") List<Long> ids);

}
