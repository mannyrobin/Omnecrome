package ru.armd.pbk.domain.nsi.stop;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

public class StopPlace
	extends BaseDomain {

   private String wktGeom;

   public String getWktGeom() {
	  return wktGeom;
   }

   public void setWktGeom(String wtkGeom) {
	  this.wktGeom = wtkGeom;
   }

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("wktGeom: ").append(StringUtils.nvl(getWktGeom(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
