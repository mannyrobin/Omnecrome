package ru.armd.pbk.dto;

import ru.armd.pbk.core.dto.IDTO;

/**
 * Данные о версии приложения.
 */
@Deprecated
public class VersionInfoDTO
	implements IDTO {
   String pomVersion;
   String buildNumber;
   String buildTag;
   String buildDate;

   public String getPomVersion() {
	  return pomVersion;
   }

   public void setPomVersion(String pomVersion) {
	  this.pomVersion = pomVersion;
   }

   public String getBuildNumber() {
	  return buildNumber;
   }

   public void setBuildNumber(String buildNumber) {
	  this.buildNumber = buildNumber;
   }

   public String getBuildTag() {
	  return buildTag;
   }

   public void setBuildTag(String buildTag) {
	  this.buildTag = buildTag;
   }

   public String getBuildDate() {
	  return buildDate;
   }

   public void setBuildDate(String buildDate) {
	  this.buildDate = buildDate;
   }

}
