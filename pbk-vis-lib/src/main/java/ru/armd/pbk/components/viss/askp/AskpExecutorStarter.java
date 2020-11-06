package ru.armd.pbk.components.viss.askp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.IVisExecutor;
import ru.armd.pbk.enums.viss.VisExchangeObjects;

import java.util.Date;

@Component
public class AskpExecutorStarter {

   @Autowired
   protected ApplicationContext applicationContext;

   /**
	* Метод, запускающий асинхронный процесс информационного обмена с ВИС АСДУ.
	*
	* @param visExchangeObject - Тип обмена с ВИС.
	* @param parameter         - параметр.
	* @param start             - дата начала периуда.
	* @param end               - дата окончания периуда.
	* @param force             - флаг принудительного запуска.
	*/
   @Async(IVisExecutor.ASKP_EXECUTOR)
   public void doImport(VisExchangeObjects visExchangeObject, String parameter, Date start, Date end, boolean force) {
	  AskpExchangeProcessor askpExchangeProcessor = applicationContext.getBean(AskpExchangeProcessor.class);
	  askpExchangeProcessor.doImport(visExchangeObject, parameter, start, end, force);
   }

}
