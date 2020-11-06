package ru.armd.pbk.utils.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.dto.core.RoleDTO;
import ru.armd.pbk.mappers.core.RoleMapper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Map;

public class RoleValidator
	implements ConstraintValidator<IRoleValidator, RoleDTO> {

   @Autowired
   RoleMapper mapper;

   @Override
   public void initialize(IRoleValidator constraintAnnotation) {

   }

   @Override
   public boolean isValid(RoleDTO value, ConstraintValidatorContext context) {
	  if (value == null || value.getId() != null) {
		 return true;
	  }
	  Map<String, Object> filter = new HashMap<String, Object>();
	  filter.put("id", value.getId());
	  if (mapper.isExist(filter)) {
		 return false;
	  }
	  return true;
   }

}
