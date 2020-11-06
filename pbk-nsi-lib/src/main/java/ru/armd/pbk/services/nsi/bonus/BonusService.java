package ru.armd.pbk.services.nsi.bonus;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.TicketBonus;
import ru.armd.pbk.domain.nsi.bonus.Bonus;
import ru.armd.pbk.dto.nsi.bonus.BonusDTO;
import ru.armd.pbk.matcher.nsi.bonus.IBonusMatcher;
import ru.armd.pbk.repositories.nsi.TicketBonusRepository;
import ru.armd.pbk.repositories.nsi.bonus.BonusRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис премирования.
 */
@Service
public class BonusService
	extends BaseDomainService<Bonus, BonusDTO> {

   private static final Logger LOGGER = Logger.getLogger(BonusService.class);

   private TicketBonusRepository ticketBonusRepository;

   @Autowired
   BonusService(BonusRepository repository, TicketBonusRepository ticketBonusRepository) {
	  super(repository);
	  this.ticketBonusRepository = ticketBonusRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Bonus toDomain(BonusDTO dto) {
	  return IBonusMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public BonusDTO toDTO(Bonus domain) {
	  BonusDTO dto = IBonusMatcher.INSTANCE.toDTO(domain);
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("bonusId", domain.getId());
	  for (TicketBonus ticketBonus : ticketBonusRepository.getDomains(params)) {
		 dto.getTickets().add(ticketBonus.getTicketId());
	  }
	  return dto;
   }

   @Transactional
   @Override
   public BonusDTO saveDTO(BonusDTO dto) {
	  Bonus domain = toDomain(dto);
	  Bonus resultDomain = domainRepository.save(domain);
	  List<Long> newTicketIds = dto.getTickets() == null ? new ArrayList<Long>() : new ArrayList<Long>(dto.getTickets());
	  if (dto.getId() != null) {
		 List<Long> oldTicketIds = ticketBonusRepository.getTicketIdsByBonusId(dto.getId());
		 if (oldTicketIds != null && !oldTicketIds.isEmpty() && dto.getTickets() != null) {
			for (Long routeId : dto.getTickets()) {
			   if (oldTicketIds.contains(routeId)) {
				  oldTicketIds.remove(routeId);
				  newTicketIds.remove(routeId);
			   }
			}
			if (!oldTicketIds.isEmpty()) {
			   ticketBonusRepository.deleteTaskRoutes(dto.getId(), oldTicketIds);
			}
		 }
	  }
	  if (newTicketIds != null) {
		 for (Long ticketId : newTicketIds) {
			if (ticketId != -1) {
			   ticketBonusRepository.save(new TicketBonus(resultDomain.getId(), ticketId));
			}
		 }
	  }
	  return dto;
   }

   @Override
   public int delete(List<Long> ids) {

	  return super.delete(ids);
   }
}
