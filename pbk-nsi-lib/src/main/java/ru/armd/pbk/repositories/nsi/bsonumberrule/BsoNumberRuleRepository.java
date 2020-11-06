package ru.armd.pbk.repositories.nsi.bsonumberrule;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.core.Setting;
import ru.armd.pbk.domain.nsi.bsonumberrule.BsoNumberRule;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.bsonumberrule.BsoNumberRuleMapper;
import ru.armd.pbk.repositories.core.SettingsRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий правил формирования номеров БСО.
 */
@Repository
public class BsoNumberRuleRepository
	extends BaseDomainRepository<BsoNumberRule> {

   public static final Logger LOGGER = Logger.getLogger(BsoNumberRuleRepository.class);

   BsoNumberRuleMapper ruleMapper;

   @Autowired
   private SettingsRepository settingsRepository;

   @Autowired
   BsoNumberRuleRepository(BsoNumberRuleMapper mapper) {
	  super(NsiAuditType.NSI_BSO_NUMBER_RULE, mapper);
	  this.ruleMapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Преобразование в строку номера БСО.
	*
	* @param bsoRule правило БСО
	* @param count   длина номера БСО.
	* @return
	*/
   public String formatBsoNumber(BsoNumberRule bsoRule, Integer count) {
	  StringBuilder builder = new StringBuilder();
	  // Format number
	  if (bsoRule.getBsoTypeCode() != null) {
		 builder.append(bsoRule.getBsoTypeCode());
	  }
	  if (bsoRule.getBranchCode() != null) {
		 builder.append(bsoRule.getBranchCode());
	  }
	  if (bsoRule.getDepartmentCode() != null) {
		 builder.append(bsoRule.getDepartmentCode());
	  }
	  builder.append(leadingZero(bsoRule.getIncrement().toString(), count));
	  return builder.toString();
   }

   /**
	* Получение текущего номера БСО.
	*
	* @param bsoTypeId тип БСО
	* @param deptId    ИД подразделения
	* @param year      год
	* @return
	*/
   public String currentBsoNumber(Long bsoTypeId, Long deptId, Date year) {
	  String result = null;
	  Map<String, Object> params = new HashMap<>();
	  params.put("bsoTypeId", bsoTypeId);
	  params.put("departmentId", deptId);
	  List<BsoNumberRule> bsoRules = getDomains(params);
	  if (bsoRules.size() != 1) {
		 throw new PBKException(bsoRules.size() + " BSO rules returned");
	  }
	  Setting bsoLengthSetting = settingsRepository.getByCode("BSO_MAX_LENGTH");
	  Integer bsoLength = Integer.valueOf(bsoLengthSetting.getValue());
	  result = formatBsoNumber(bsoRules.get(0), bsoLength);
	  return result;
   }

   /**
	* Генерация номера БСО.
	*
	* @param bsoRule   правило формирования БСО.
	* @param bsoLength длина номера БСО.
	* @return
	*/
   public synchronized String generateBsoNumber(BsoNumberRule bsoRule, Integer bsoLength) {
	  String result = null;
	  StringBuilder code = new StringBuilder(bsoRule.getBsoTypeCode() == null ? "" : bsoRule.getBsoTypeCode())
		  .append(bsoRule.getBranchCode() == null ? "" : bsoRule.getBranchCode())
		  .append(bsoRule.getDepartmentCode() == null ? "" : bsoRule.getDepartmentCode());
	  List<Long> lasts = ruleMapper.getLastNumberForRule(bsoRule.getId(), code.toString(), bsoRule.getIncrement() == null ? 1L : bsoRule.getIncrement());
	  if (!lasts.isEmpty()) {
		 bsoRule.setIncrement(lasts.get(0) + 1);
	  }
	  save(bsoRule);
	  result = formatBsoNumber(bsoRule, bsoLength);
	  return result;
   }

   private String leadingZero(String str, int size) {
	  String result = str;
	  for (int i = result.length(); i < size; i++) {
		 result = "0" + result;
	  }
	  return result;
   }

   /**
	* Получить кол-во БСО для определенного правила.
	*
	* @param ruleId ИД правила
	* @return
	*/
   public int getCountBsoForRule(Long ruleId) {
	  return ruleMapper.getBsosByRule(ruleId).size();
   }
}
