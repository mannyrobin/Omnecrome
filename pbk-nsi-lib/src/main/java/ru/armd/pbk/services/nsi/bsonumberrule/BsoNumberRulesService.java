package ru.armd.pbk.services.nsi.bsonumberrule;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.bsonumberrule.BsoNumberRule;
import ru.armd.pbk.dto.nsi.bsonumberrule.BsoNumberRuleDTO;
import ru.armd.pbk.matcher.nsi.IBsoNumberRuleMatcher;
import ru.armd.pbk.repositories.nsi.bsonumberrule.BsoNumberRuleRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервисы правил формирования номеров БСО.
 */
@Service
public class BsoNumberRulesService
	extends BaseDomainService<BsoNumberRule, BsoNumberRuleDTO> {

   private static final Logger LOGGER = Logger.getLogger(BsoNumberRulesService.class);

   private BsoNumberRuleRepository repository;

   @Autowired
   BsoNumberRulesService(BsoNumberRuleRepository repository) {
	  super(repository);
	  this.repository = repository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получение следующего номера БСО.
	*
	* @param bsoRule   правило формирования БСО
	* @param bsoLength длина БСО
	* @return следующий номер БСО.
	*/
   @Transactional
   public String getNextBsoNumber(BsoNumberRule bsoRule, Integer bsoLength) {
	  String bsoNumber = repository.generateBsoNumber(bsoRule, bsoLength);
	  return bsoNumber;
   }

   @Override
   public BsoNumberRule toDomain(BsoNumberRuleDTO dto) {
	  return IBsoNumberRuleMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public BsoNumberRuleDTO toDTO(BsoNumberRule domain) {
	  return IBsoNumberRuleMatcher.INSTANCE.toDTO(domain);
   }

   /**
	* Поиск правила номера БСО.
	*
	* @param bsoTypeId    тип БСО
	* @param departmentId ИД подразделения
	* @return
	*/
   public BsoNumberRule find(Long bsoTypeId, Long departmentId) {
	  Map<String, Object> params = new HashMap<>();
	  params.put("bsoTypeId", bsoTypeId);
	  params.put("departmentId", departmentId);
	  List<BsoNumberRule> bsoNumberRules = repository.getDomains(params);
	  if (bsoNumberRules.isEmpty()) {
		 return null;
	  }
	  return bsoNumberRules.get(0);
   }

   /**
	* Получить кол-во БСО для определенного правила.
	*
	* @param ruleId ИД правила
	* @return
	*/
   public int getCountBsoForRule(Long ruleId) {
	  return repository.getCountBsoForRule(ruleId);
   }
}
