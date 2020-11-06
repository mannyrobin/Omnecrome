package ru.armd.pbk.dto.nsi;

import ru.armd.pbk.core.dto.BaseDictionaryDTO;

/**
 * DTO пола.
 *
 * @author nikita_chebotaryov
 */
public class SexDTO
	extends BaseDictionaryDTO {

   private String name;
   private String description;

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

}
