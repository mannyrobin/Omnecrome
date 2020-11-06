package ru.armd.pbk.utils.log4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Самописный класс для логирования.
 */
public class DailyFileAppender
	extends FileAppender {

   private static final int BUFFER_SIZE = 8192;
   private static final String DATE_PATTERN = "'.'yyyy-MM-dd";

   private String datePattern;
   private long nextCheck;
   private Date now;
   private SimpleDateFormat sdf;
   private String file = "pbk-logs/pbk-log.log";

   /**
	* Конструктор по умолчанию.
	*/
   public DailyFileAppender() {
	  this.datePattern = DATE_PATTERN;
	  this.nextCheck = System.currentTimeMillis() - 1L;
	  this.now = new Date();
   }

   /**
	* Конструктор.
	*
	* @param layout      Лейоут.
	* @param file        Файл.
	* @param datePattern Формат даты.
	* @throws IOException
	*/
   public DailyFileAppender(Layout layout, String file, String datePattern)
	   throws IOException {
	  this();
	  this.datePattern = datePattern;
	  this.file = file;
	  super.fileAppend = true;
	  super.fileName = null;
	  super.bufferedIO = false;
	  super.bufferSize = BUFFER_SIZE;
	  super.layout = layout;
	  this.activateOptions();
   }

   public void setDatePattern(String pattern) {
	  datePattern = pattern;
   }

   public String getDatePattern() {
	  return datePattern;
   }

   @Override
   public final void activateOptions() {
	  if (StringUtils.isNotBlank(datePattern) && StringUtils.isNotBlank(file)) {
		 this.now.setTime(System.currentTimeMillis());
		 this.sdf = new SimpleDateFormat(datePattern);
		 try {
			super.setFile(generateFileName(), true, bufferedIO, bufferSize);
		 } catch (IOException e) {
			LogLog.error("setFile(" + fileName + "," + fileAppend + ") call failed.", e);
		 }
	  } else {
		 LogLog.error("Either File or DatePattern options are not set for appender [" + super.name + "].");
	  }
   }

   private String generateFileName() {
	  return this.file + sdf.format(now);
   }

   @Override
   public void setFile(String file) {
	  this.file = StringUtils.strip(file);
   }

   protected void rollOver()
	   throws IOException {
	  if (this.datePattern == null) {
		 super.errorHandler.error("Missing DatePattern option in rollOver().");
		 return;
	  }
	  String datedFilename = generateFileName();
	  if (super.fileName.equals(datedFilename)) {
		 return;
	  }
	  closeFile();
	  try {
		 super.setFile(generateFileName(), true, bufferedIO, bufferSize);
	  } catch (IOException e) {
		 LogLog.error("setFile(" + fileName + "," + fileAppend + ") call failed.", e);
	  }
   }

   protected void subAppend(LoggingEvent event) {
	  long currentTimeMillis = System.currentTimeMillis();
	  if (currentTimeMillis >= this.nextCheck) {
		 this.now.setTime(currentTimeMillis);
		 Calendar nextCheckDate = Calendar.getInstance();
		 nextCheckDate.setTime(now);
		 nextCheckDate.add(Calendar.DATE, 1);
		 nextCheckDate.set(Calendar.HOUR_OF_DAY, nextCheckDate.getActualMinimum(Calendar.HOUR_OF_DAY));
		 nextCheckDate.set(Calendar.MINUTE, nextCheckDate.getActualMinimum(Calendar.MINUTE));
		 nextCheckDate.set(Calendar.SECOND, nextCheckDate.getActualMinimum(Calendar.SECOND));
		 nextCheckDate.set(Calendar.MILLISECOND, nextCheckDate.getActualMinimum(Calendar.MILLISECOND));
		 this.nextCheck = nextCheckDate.getTimeInMillis();
		 try {
			rollOver();
		 } catch (IOException ioe) {
			LogLog.error("rollOver() failed.", ioe);
		 }
	  }
	  super.subAppend(event);
   }
}
