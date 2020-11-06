package ru.armd.pbk.mappers.nsi;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.TicketBonus;

import java.util.List;

/**
 * Маппер для сущности связки "Премирование - Билет".
 */
@IsMapper
public interface TicketBonusMapper
	extends IDomainMapper<TicketBonus> {

   /**
	* Получает id билетов по id премирования.
	*
	* @param bonusId id премирования
	* @return список id билетов
	*/
   List<Long> getTicketIdsByBonusId(@Param("bonusId") Long bonusId);

   /**
	* Отвязывает билеты от премирования.
	*
	* @param bonusId   id премирования
	* @param ticketIds id билетов
	* @return
	*/
   int deleteBonusTickets(@Param("bonusId") Long bonusId, @Param("ticketIds") List<Long> ticketIds);
}
