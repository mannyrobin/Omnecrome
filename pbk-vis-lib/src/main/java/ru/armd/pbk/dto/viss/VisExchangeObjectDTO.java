package ru.armd.pbk.dto.viss;

import ru.armd.pbk.core.dto.BaseDictionaryDTO;

/**
 * ДТО Типа обмена с ВИС.
 */
public class VisExchangeObjectDTO
	extends BaseDictionaryDTO {

   private Long visId;
   private String vis;

   public Long getVisId() {
	  return visId;
   }

   public void setVisId(Long visId) {
	  this.visId = visId;
   }

   public String getVis() {
	  return vis;
   }

   public void setVis(String vis) {
	  this.vis = vis;
   }
}
