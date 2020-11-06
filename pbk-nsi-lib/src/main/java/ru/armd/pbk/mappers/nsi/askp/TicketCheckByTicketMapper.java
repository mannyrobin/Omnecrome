package ru.armd.pbk.mappers.nsi.askp;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.domain.nsi.askp.TicketCheckByTicket;

import java.util.Date;
import java.util.List;

/**
 * Маппер для агригации по билетам.
 */
@IsMapper
public interface TicketCheckByTicketMapper
	extends ITicketCheckMapper<TicketCheckByTicket> {

   /**
	* Получить проверку по билетам за заданный день.
	*
	* @param workDate день
	* @return
	*/
   List<TicketCheckByTicket> getByWorkDate(Date workDate);

} 
