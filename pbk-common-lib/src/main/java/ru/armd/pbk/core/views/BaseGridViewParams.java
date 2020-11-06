package ru.armd.pbk.core.views;

import ru.armd.pbk.utils.date.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Базовые параметры для грида для постраничного отображения и фильтрации.
 */
public class BaseGridViewParams
	implements IParams {

   private Long page;
   private Long count;
   private String orderBy;
   private boolean orderByAsc;
   private Map<String, Object> filter;

   /**
	* Конструкор.
	*
	* @param page       Текущая страница.
	* @param count      Кол-во элеметов на странице.
	* @param orderBy    Поля для сортировки.
	* @param orderByAsc Направление сортировки.
	*/
   public BaseGridViewParams(Long page, Long count, String orderBy, boolean orderByAsc) {
	  this.page = page;
	  this.count = count;
	  this.orderBy = orderBy;
	  this.orderByAsc = orderByAsc;
   }

   /**
	* Конструкор.
	*
	* @param page       Текущая страница.
	* @param count      Кол-во элеметов на странице.
	* @param orderBy    Поля для сортировки.
	* @param orderByAsc Направление сортировки.
	* @param filter     Фильтры
	*/
   public BaseGridViewParams(Long page, Long count, String orderBy, String orderByAsc, Map<String, Object> filter) {
	  this(page, count, orderBy, orderByAsc.equalsIgnoreCase("asc"));
	  this.filter = filter;
   }

   public Long getPage() {
	  return page;
   }

   public void setPage(Long page) {
	  this.page = page;
   }

   public Long getCount() {
	  return count;
   }

   public void setCount(Long count) {
	  this.count = count;
   }

   public String getOrderBy() {
	  return orderBy;
   }

   public void setOrderBy(String orderBy) {
	  this.orderBy = orderBy;
   }

   public boolean isOrderByAsc() {
	  return orderByAsc;
   }

   public void setOrderByAsc(boolean orderByAsc) {
	  this.orderByAsc = orderByAsc;
   }

   @Override
   public String toString() {
	  return "BaseGridViewParams{" + "page=" + page + ", count=" + count + ", orderBy=" + orderBy + ", orderByAsc="
		  + orderByAsc + "}";
   }

   /**
	* Преобразовывает строку в дату.
	*
	* @param date Дата.
	* @return
	*/
   public static Date parseDate(String date) {
	  Date result = null;
	  if (date != null && date.length() > 0) {
		 try {
			result = (new SimpleDateFormat(DateUtils.DATE_FORMAT)).parse(date);
		 } catch (ParseException e) {
			throw new RuntimeException("Parse Date exception");
		 }
	  }
	  return result;
   }

   /**
	* Преобразовывает строку в время.
	*
	* @param time строка времени.
	* @return время.
	*/
   public static Date parseTime(String time) {
	  Date result = null;
	  if (time != null && time.length() > 0) {
		 try {
			result = (new SimpleDateFormat(DateUtils.TIME_FORMAT)).parse(time);
		 } catch (ParseException e) {
			throw new RuntimeException("Parse Time exception");
		 }
	  }
	  return result;
   }

   @Override
   public int hashCode() {
	  final int prime = 31;
	  int result = 1;
	  result = prime * result + ((this.page == null) ? 0 : this.page.hashCode());
	  result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
	  result = prime * result + ((this.orderBy == null) ? 0 : this.orderBy.hashCode());
	  result = prime * result + (this.orderByAsc ? 1231 : 1237);
	  // result = prime * result + (int) (searchId ^ (searchId >>> 32));
	  return result;
   }

   @Override
   public boolean equals(Object obj) {
	  if (this == obj) {
		 return true;
	  }
	  if (obj == null) {
		 return false;
	  }
	  if (getClass() != obj.getClass()) {
		 return false;
	  }
	  BaseGridViewParams other = (BaseGridViewParams) obj;
	  return this.hashCode() == other.hashCode();
   }

   public Map<String, Object> getFilter() {
	  return filter;
   }
}
