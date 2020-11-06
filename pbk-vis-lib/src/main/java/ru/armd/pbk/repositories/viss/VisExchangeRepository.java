package ru.armd.pbk.repositories.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.VisExchange;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.enums.viss.VisExchangeStates;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.viss.VisExchangeMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий журнальной записи обмена.
 */
@Repository
public class VisExchangeRepository
	extends BaseDomainRepository<VisExchange> {

   public static final Logger LOGGER = Logger.getLogger(VisExchangeRepository.class);


   private VisExchangeMapper visExchangeMapper;

   /**
	* Конструктор.
	*
	* @param visExchangeMapper Маппер репозитория.
	*/
   @Autowired
   public VisExchangeRepository(VisExchangeMapper visExchangeMapper) {
	  super(VisAuditType.VIS_EXCHANGE, visExchangeMapper);
	  this.visExchangeMapper = visExchangeMapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получение домена обмена по дате.
	*
	* @param configId  id конфигурации.
	* @param workDate  Дата.
	* @param parameter - параметр.
	* @return Данные по обмену.
	*/
   public VisExchange getVisExchange(Long configId, Date workDate, String parameter) {
	  Map<String, Object> params = new HashMap<>();
	  params.put("configId", configId);
	  params.put("workDate", workDate);
	  params.put("parameter", parameter);
	  List<VisExchange> domains = getDomains(params);
	  if (domains.size() == 1) {
		 return domains.get(0);
	  } else if (domains.size() <= 1) {
		 return null;
	  }
	  throw new PBKException("getVisExchange return more 1 domain");
   }

   /**
	* Метод возвращает список дат из периода startDate - endDate по которым не производилась отправка для конфигурации configId.
	* Так же в результат входит список дат, у которых обмен не состоялся и его нужно повторить
	*
	* @param configId  id конфигурации.
	* @param startDate Дата начала периода выборки.
	* @param endDate   Дата окончания периода выборки.
	* @param force     Флаг принудительного запуска.
	* @return
	*/
   public List<Date> getWorkDatesToSend(Long configId, Date startDate, Date endDate, boolean force) {
	  return visExchangeMapper.getWorkDatesToSend(configId, VisExchangeStates.SUCCESS.getId(), startDate, endDate, force);
   }

   public List<Integer> isValidDay(Date workDate, List<Integer> configIds){
   	  return visExchangeMapper.isValidDay(workDate, configIds);
   }
}
