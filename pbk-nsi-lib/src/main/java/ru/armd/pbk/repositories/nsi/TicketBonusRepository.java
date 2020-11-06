package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.TicketBonus;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.TicketBonusMapper;

import java.util.List;

/**
 * Репозиторий для связки премирования-бонус.
 */
@Repository
public class TicketBonusRepository
	extends BaseDomainRepository<TicketBonus> {

   public static final Logger LOGGER = Logger.getLogger(TicketBonusRepository.class);

   private TicketBonusMapper ticketBonusMapper;

   @Autowired
   TicketBonusRepository(TicketBonusMapper mapper) {
	  super(NsiAuditType.NSI_TICKET_BONUS, mapper);
	  this.ticketBonusMapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получает id билетов по id премирования.
	*
	* @param bonusId id премирования
	* @return список id билетов
	*/
   public List<Long> getTicketIdsByBonusId(Long bonusId) {
	  List<Long> routeIds = null;
	  try {
		 routeIds = ticketBonusMapper.getTicketIdsByBonusId(bonusId);
	  } catch (Exception e) {
		 String message = "Не удалось получить список id билетов бонуса. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return routeIds;
   }

   /**
	* Отвязывает билеты от премирования.
	*
	* @param bonusId   id премирования
	* @param ticketIds id билетов
	* @return
	*/
   public int deleteTaskRoutes(Long bonusId, List<Long> ticketIds) {
	  int count = 0;
	  if (ticketIds == null) {
		 return count;
	  }
	  try {
		 count = ticketBonusMapper.deleteBonusTickets(bonusId, ticketIds);
		 logAudit(AuditLevel.TRACE, getDomainAuditType(), AuditObjOperation.SELECT, ticketIds,
			 "Успешно удалено " + count + " из " + ticketIds.size() + " записи/записей.", null);
	  } catch (Exception e) {
		 String message = "Не удалось удалить записи. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, ticketIds, message, e);
		 throw new PBKException(message, e);
	  }
	  return count;
   }
}
