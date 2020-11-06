package ru.armd.pbk.mappers.viss;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.viss.VisExchange;

import java.util.Date;
import java.util.List;

/**
 * МАппер журнальной записи обмена.
 */
@IsMapper
public interface VisExchangeMapper
	extends IDomainMapper<VisExchange> {
   List<Date> getWorkDatesToSend(
	   @Param("exchangeConfigId") Long exchangeConfigId,
	   @Param("exchangeStateId") Long exchangeStateId,
	   @Param("dateFrom") Date dateFrom,
	   @Param("dateTo") Date dateTo,
	   @Param("force") boolean force);

   List<Integer> isValidDay(@Param("workDate") Date workDate, @Param("list") List<Integer> configIds);
}
