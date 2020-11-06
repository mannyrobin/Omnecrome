package ru.armd.pbk.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Структура для хранения данных по импорту сущностей из файла.
 *
 * @param <T> тип сущностей.
 */
public class ImportResult<T> {
   private Set<T> created = new HashSet<>();
   private Set<T> updated = new HashSet<>();
   private int allCount = 0;
   private int successCount = 0;
   private int processingCount = 0;

   public Set<T> getCreated() {
	  return created;
   }

   public void setCreated(Set<T> created) {
	  this.created = created;
   }

   public Set<T> getUpdated() {
	  return updated;
   }

   public void setUpdated(Set<T> updated) {
	  this.updated = updated;
   }

   /**
	* Слияние с другим результатом.
	*
	* @param other другой результат.
	*/
   public void merge(ImportResult<T> other) {
	  if (other == null) {
		 return;
	  }
	  this.created.addAll(other.created);
	  this.updated.addAll(other.updated);
	  this.allCount += other.allCount;
	  this.successCount += other.successCount;
	  this.processingCount += other.processingCount;
   }

   public int getAllCount() {
	  return allCount;
   }

   public void setAllCount(int allCount) {
	  this.allCount = allCount;
   }

   public int getSuccessCount() {
	  return successCount;
   }

   public void setSuccessCount(int successCount) {
	  this.successCount = successCount;
   }

   public int getProcessingCount() {
	  return processingCount;
   }

   public void setProcessingCount(int processingCount) {
	  this.processingCount = processingCount;
   }
}