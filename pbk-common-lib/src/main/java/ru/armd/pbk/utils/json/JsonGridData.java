package ru.armd.pbk.utils.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс с данными для работы с гридами.
 */
public class JsonGridData {

   @JsonProperty("total")
   private long total = 0;

   @JsonProperty("page")
   private long page = 0;

   @JsonProperty("records")
   private long records = 0;

   @JsonProperty("rows")
   private Object rows = null;

   /**
	* Конструктор по умолчанию.
	*/
   public JsonGridData() {
   }

   /**
	* Клнструктор.
	*
	* @param rows           Список объектов для отображения в гриде на странице.
	* @param totalDBRecords Общее кол-во записей в БД.
	* @param rowsOnPage     Кол-во записей на странице.
	* @param currentPage    Текущая страница.
	*/
   public JsonGridData(Object rows, long totalDBRecords, long rowsOnPage, long currentPage) {
	  double pageCeil = Math.ceil((double) totalDBRecords / rowsOnPage);

	  this.total = (pageCeil == 0) ? 1 : Math.round(pageCeil);
	  this.page = currentPage;
	  this.records = totalDBRecords;
	  this.rows = rows;
   }

   /**
	* Клнструктор.
	*
	* @param totalDBRecords Общее кол-во записей в БД.
	* @param rowsOnPage     Кол-во записей на странице.
	* @param currentPage    Текущая страница.
	*/
   public JsonGridData(long totalDBRecords, long rowsOnPage, long currentPage) {
	  double pageCeil = Math.ceil((double) totalDBRecords / rowsOnPage);

	  this.total = (pageCeil == 0) ? 1 : Math.round(pageCeil);
	  this.page = currentPage;
	  this.records = totalDBRecords;
   }

   public long getTotal() {
	  return total;
   }

   public void setTotal(long total) {
	  this.total = total;
   }

   public long getPage() {
	  return page;
   }

   public void setPage(long currentPage) {
	  this.page = currentPage;
   }

   public long getRecords() {
	  return records;
   }

   public void setRecords(long records) {
	  this.records = records;
   }

   public Object getRows() {
	  return rows;
   }

   public void setRows(Object rows) {
	  this.rows = rows;
   }
}
