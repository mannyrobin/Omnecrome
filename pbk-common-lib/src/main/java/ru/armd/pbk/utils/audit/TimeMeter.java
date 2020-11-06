package ru.armd.pbk.utils.audit;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс для замеров времени.
 */
public class TimeMeter {

   private static final DateFormat FORMATTER = new SimpleDateFormat("HH:mm:ss:SSS");

   private long startTime = 0;
   private StringBuilder sb = new StringBuilder();

   /**
	* Конструктор.
	*
	* @param startTime Время начала отсчета.
	*/
   public TimeMeter(long startTime) {
	  this.startTime = startTime;
   }

   /**
	* Конструктор по умолчанию.
	*/
   public TimeMeter() {
	  this(System.currentTimeMillis());
   }

   /**
	* Возвращает время начало отсчета.
	*
	* @return
	*/
   public long getStartTime() {
	  return startTime;
   }

   /**
	* Возвращает сообщение для интервала.
	*
	* @return
	*/
   public String getMessageInterval() {
	  return sb.toString();
   }

   /**
	* Запускает новый отсчет вермени.
	*
	* @return
	*/
   public long startInterval() {
	  startTime = System.currentTimeMillis();
	  return getStartTime();
   }

   /**
	* Перезапускает счетчик.
	*
	* @return
	*/
   public long restartTimeMeter() {
	  sb.setLength(0);
	  return startInterval();
   }

   /**
	* Возвращает текущий период.
	*
	* @return
	*/
   public long calcInterval() {
	  long result = System.currentTimeMillis() - startTime;
	  return result;
   }

   /**
	* Добавляет точку отсчета.
	*
	* @param pPointName Сообщение точки отсчета
	* @return
	*/
   public String appendPrintInterval(String pPointName) {
	  String pointName = pPointName;
	  Date date = new Date(calcInterval());
	  pointName = pointName != null ? pointName : "point";
	  StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
	  StackTraceElement e = stacktrace[2];// coz 0th will be getStackTrace so
	  // 1st
	  String result = "[" + Thread.currentThread().getName() + "] - " + e.getClassName() + "." + e.getMethodName()
		  + "():" + e.getLineNumber() + " {" + pointName + "} execute in " + FORMATTER.format(date);
	  sb.append(result).append("\r");
	  return result;
   }

   /**
	* Распечатывает результат счетчика.
	*
	* @param logger Логгер.
	* @return
	*/
   public String printInterval(Logger logger) {
	  Date date = new Date(calcInterval());
	  String result = "Total execute: " + FORMATTER.format(date) + "\r" + getMessageInterval();
	  if (logger != null) {
		 logger.debug(result);
	  }
	  return result;
   }
}
