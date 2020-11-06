package ru.armd.pbk.domain.viss.gismgt;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Базовый домен для ГИС МГТ.
 */
public class GmBaseDomain
	extends BaseDomain {

   private Long muid;
   private Integer version;
   private String asduId;
   private Integer gmVersion;

   public Long getMuid() {
	  return muid;
   }

   public void setMuid(Long muid) {
	  this.muid = muid;
   }

   public Integer getVersion() {
	  return version;
   }

   public void setVersion(Integer version) {
	  this.version = version;
   }

   public String getAsduId() {
	  return asduId;
   }

   public void setAsduId(String asduId) {
	  this.asduId = asduId;
   }

    public Integer getGmVersion() {
        return gmVersion;
    }

    public void setGmVersion(Integer gmVersion) {
        this.gmVersion = gmVersion;
    }

    @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("muid: ").append(StringUtils.nvl(getMuid(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("version: ").append(StringUtils.nvl(getVersion(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("asduId: ").append(StringUtils.nvl(getAsduId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("gmVersion: ").append(StringUtils.nvl(getGmVersion(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }
}
