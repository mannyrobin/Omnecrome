package ru.armd.pbk.domain.viss;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.utils.StringUtils;

/**
 * Домен файла.
 */
public class VisFile
	extends BaseDomain {

   private String name;
   private Long size;
   private String description;
   private String streamId;

   @Override
   public String toAuditString() {
	  StringBuilder sb = new StringBuilder(super.toAuditString());
	  sb.append("name: ").append(StringUtils.nvl(getName(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("size: ").append(StringUtils.nvl(getSize(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("description: ").append(StringUtils.nvl(getDescription(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  sb.append("streamId: ").append(StringUtils.nvl(getStreamId(), FIELD_NULL)).append(FIELD_SEPARATOR);
	  return sb.toString();
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public Long getSize() {
	  return size;
   }

   public void setSize(Long size) {
	  this.size = size;
   }

   public String getDescription() {
	  return description;
   }

   public void setDescription(String description) {
	  this.description = description;
   }

   public String getStreamId() {
	  return streamId;
   }

   public void setStreamId(String streamId) {
	  this.streamId = streamId;
   }
}
