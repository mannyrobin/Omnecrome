package ru.armd.pbk.components.viss.askp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.askp.contactlesscard.AskpContactlessCardProvider;
import ru.armd.pbk.components.viss.askp.puskcheck.AskpPuskCheckProvider;
import ru.armd.pbk.components.viss.askp.ticket.AskpTicketProvider;
import ru.armd.pbk.components.viss.askp.ticketcheck.AskpTicketCheckProvider;
import ru.armd.pbk.components.viss.core.BaseExchangeProcessor;
import ru.armd.pbk.domain.viss.VisExchangeConfig;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeObjects;
import ru.armd.pbk.enums.viss.VisExchangeOperations;

import java.util.Date;

/**
 * Класс с методами взаимодействия с ВИС АСКП высокого уровня. Каждая
 * задача запускается в отдельном процессе.
 */
@Component
@Scope("prototype")
public class AskpExchangeProcessor
	extends BaseExchangeProcessor {

   @Autowired
   private AskpTicketProvider askpTicketProvider;

   @Autowired
   private AskpTicketCheckProvider askpTicketCheckProvider;

   @Autowired
   private AskpPuskCheckProvider askpPuskCheckProvider;

   @Autowired
   private AskpContactlessCardProvider askpContactlessCardProvider;

   AskpExchangeProcessor() {
	  super(VisAuditType.VIS_ASKP);
   }

   /**
	* Метод, запускающий процесс информационного обмена с ВИС АСКП.
	*
	* @param visExchangeObject - Тип обмена с ВИС.
	* @param parameter         - параметр.
	* @param start             - дата начала периуда.
	* @param end               - дата окончания периуда.
	* @param force             - флаг принудительного запуска.
	*/
   public void doImport(VisExchangeObjects visExchangeObject, String parameter, Date start, Date end, boolean force) {
	  doProcess(visExchangeObject, VisExchangeOperations.IMPORT, parameter, start, end, force);
   }

   @Override
   protected void doExchange(VisExchangeConfig visExchangeConfig, String parameter, Date workDate, boolean force) {
	  if (VisExchangeObjects.ASKP_TICKET.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 askpTicketProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.ASKP_TICKET_CHECK.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 askpTicketCheckProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.ASKP_PUSK_CHECK.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 askpPuskCheckProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  } else if (VisExchangeObjects.ASKP_CONTACTLESS_CARD.getId().equals(visExchangeConfig.getExchangeObjectId())) {
		 askpContactlessCardProvider.doExchange(visExchangeConfig, parameter, workDate, force);
	  }
   }

}
