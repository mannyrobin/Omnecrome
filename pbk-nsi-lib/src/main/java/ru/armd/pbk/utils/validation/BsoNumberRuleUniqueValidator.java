package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.core.utils.validation.BaseValidator;
import ru.armd.pbk.domain.nsi.bsonumberrule.BsoNumberRule;
import ru.armd.pbk.dto.nsi.bsonumberrule.BsoNumberRuleDTO;
import ru.armd.pbk.dto.nsi.bsotype.BsoTypeDTO;
import ru.armd.pbk.dto.nsi.department.DepartmentDTO;
import ru.armd.pbk.repositories.core.SettingsRepository;
import ru.armd.pbk.services.nsi.bsonumberrule.BsoNumberRulesService;
import ru.armd.pbk.services.nsi.bsotype.BsoTypesService;
import ru.armd.pbk.services.nsi.department.DepartmentService;
import ru.armd.pbk.utils.ValidationUtilMessages;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Валидатор уникальности правила формирования номера БСО (по полям "Тип БСО", "Подразделение", "Код филиала").
 */
public class BsoNumberRuleUniqueValidator
	extends BaseValidator
	implements ConstraintValidator<BsoNumberRuleUnique, BsoNumberRuleDTO> {

   @Autowired
   private BsoNumberRulesService bsoRuleNumbersService;

   @Autowired
   private BsoTypesService bsoTypesService;

   @Autowired
   private DepartmentService departmentService;

   @Autowired
   private SettingsRepository settingsRepository;

   @Override
   public void initialize(BsoNumberRuleUnique constraintAnnotation) {
   }

   @Override
   public boolean isValid(BsoNumberRuleDTO value, ConstraintValidatorContext context) {
	  if (value != null) {
		 Map<String, Object> params = new HashMap<>();
		 params.put("bsoTypeId", value.getBsoTypeId());
		 params.put("departmentId", value.getDepartmentId());
		 params.put("branchId", value.getBranchId());
		 List<BsoNumberRule> existingRules = bsoRuleNumbersService.getDomains(params);
		 Long id = value.getId();
		 // Это попытка создания, и уже существует правило с требуемыми параметрами:
		 boolean existsForNew = id == null && !existingRules.isEmpty();
		 // Это попытка обновления, и уже существует правило с требуемыми параметрами, и не то, которое сейчас обновляется:
		 boolean existsAndNotCurrentUpdated = id != null && !existingRules.isEmpty() && !existingRules.get(0).getId().equals(id);
		 boolean existsAndCurrentUpdated = id != null && !existingRules.isEmpty() && existingRules.get(0).getId().equals(id);
		 if (existsForNew || existsAndNotCurrentUpdated) {
			DepartmentDTO department = departmentService.getDTOById(value.getDepartmentId());
			BsoTypeDTO bsoType = bsoTypesService.getDTOById(value.getBsoTypeId());
			String message = String.format(ValidationUtilMessages.BSO_NUMBER_RULE_ALREADY_EXISTS, bsoType.getName(), department.getName());
			addConstraintViolation(context, message);
			return false;
		 }
		 if (existsAndCurrentUpdated && value.getIncrement().compareTo(existingRules.get(0).getIncrement()) <= 0) {
			addConstraintViolation(context, String.format(ValidationUtilMessages.BSO_NUMBER_RULE_INC_LESS, existingRules.get(0).getIncrement()));
			return false;
		 }
		 Integer size = Integer.valueOf(settingsRepository.getByCode("BSO_MAX_LENGTH").getValue());
		 if (size < value.getIncrement().toString().length()) {
			String message = String.format(ValidationUtilMessages.BSO_NUMBER_RULE_VERY_BIG, size);
			addConstraintViolation(context, message);
			return false;
		 }

	  }
	  return true;
   }

}
