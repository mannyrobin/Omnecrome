package ru.armd.pbk.core.domain;

import ru.armd.pbk.core.IHasCod;
import ru.armd.pbk.core.IHasName;
import ru.armd.pbk.utils.StringUtils;

/**
 * Данные общего справочника.
 */
public abstract class BaseDictionaryDomain
	extends BaseDomain
	implements IHasCod, IHasName {

   private String cod;
   private String name;
   private String description;

   /**
	* Конструктор по умолчанию.
	*/
   public BaseDictionaryDomain() {
   }

   @Override
   public String getCod() {
	  return cod;
   }

   @Override
   public void setCod(String cod) {
	  this.cod = cod;
   }

   @Override
   public String getName() {
	  return name;
   }

   @Override
   public void setName(String name) {
	  this.name = name;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("cod: ").append(StringUtils.nvl(cod, FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("name: ").append(StringUtils.nvl(name, FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(description, FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
