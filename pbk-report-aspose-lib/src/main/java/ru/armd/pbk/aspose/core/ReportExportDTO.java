package ru.armd.pbk.aspose.core;

import javax.servlet.http.Cookie;

/**
 * Класс для заполнения формы выгрузки отчета.
 */
public class ReportExportDTO {

   private String dummy; // Не используется
   private boolean allowPrint;
   private String url;
   private String report;
   private String objectIds;
   private String cookies;
   private String title;

   public String getDummy() {
	  return dummy;
   }

   public void setDummy(String dummy) {
	  this.dummy = dummy;
   }

   public boolean getAllowPrint() {
	  return allowPrint;
   }

   public void setAllowPrint(boolean allowPrint) {
	  this.allowPrint = allowPrint;
   }

   public String getUrl() {
	  return url;
   }

   public void setUrl(String url) {
	  this.url = url;
   }

   public String getReport() {
	  return report;
   }

   public void setReport(String report) {
	  this.report = report;
   }

   public String getObjectIds() {
	  return objectIds;
   }

   public void setObjectIds(String objectIds) {
	  this.objectIds = objectIds;
   }

   public void setObjectIds(String[] objectIds) {
	  this.objectIds = "";
	  for (String id : objectIds) {
		 this.objectIds += id + ";";
	  }
   }

   public String getCookies() {
	  return cookies;
   }

   public void setCookies(String cookies) {
	  this.cookies = cookies;
   }

   public void setCookies(Cookie[] cookies) {
	  this.cookies = "";
	  for (Cookie cookie : cookies) {
		 this.cookies += cookie.getName() + "=" + cookie.getValue() + "; ";
	  }
   }

   public String getTitle() {
	  return title;
   }

   public void setTitle(String title) {
	  this.title = title;
   }
}
