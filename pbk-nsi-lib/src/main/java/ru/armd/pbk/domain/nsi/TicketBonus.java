package ru.armd.pbk.domain.nsi;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 * Домен для сущности связки "Премирование - Билет".
 */
public class TicketBonus
	extends BaseDomain {

   private Long ticketId;
   private Long bonusId;

   /**
	* Конструктор по умолчанию.
	*/
   public TicketBonus() {

   }

   /**
	* Конструктор с параметрами.
	*
	* @param bonusId  id премирования
	* @param ticketId id билета
	*/
   public TicketBonus(Long bonusId, Long ticketId) {
	  this.bonusId = bonusId;
	  this.ticketId = ticketId;
   }

   public Long getTicketId() {
	  return ticketId;
   }

   public void setTicketId(Long ticketId) {
	  this.ticketId = ticketId;
   }

   public Long getBonusId() {
	  return bonusId;
   }

   public void setBonusId(Long bonusId) {
	  this.bonusId = bonusId;
   }

}
