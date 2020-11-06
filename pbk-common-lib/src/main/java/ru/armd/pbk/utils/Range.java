package ru.armd.pbk.utils;

/**
 * Блок данных, используемый при записи ответа
 * в http-range запросах.
 */
public class Range {

   private long start;
   private long end;
   private long length;
   private long total;

   /**
	* Коснтруктор.
	*
	* @param start байт, с которого начинается блок данных.
	* @param end   байт, на котором заканчивается блок данных.
	* @param total общее количество байтов.
	*/
   public Range(long start, long end, long total) {
	  this.start = start;
	  this.end = end;
	  this.length = end - start + 1;
	  this.total = total;
   }

   /**
	* Конструктор.
	*
	* @param range блок данных, записанный в строку.
	* @param total общее количество байтов.
	*/
   public Range(String range, Long total) {
	  start = sublong(range, 0, range.indexOf("-"));
	  end = sublong(range, range.indexOf("-") + 1, range.length());

	  if (start == -1) {
		 start = total - end;
		 end = total - 1;
	  } else if (end == -1 || end > length - 1) {
		 end = total - 1;
	  }

	  if (start > end) {
		 throw new IllegalArgumentException();
	  }

	  this.length = end - start + 1;
	  this.total = total;
   }

   private long sublong(String value, int beginIndex, int endIndex) {
	  String substring = value.substring(beginIndex, endIndex);
	  return (substring.length() > 0) ? Long.parseLong(substring) : -1;
   }

   public long getStart() {
	  return start;
   }

   public long getEnd() {
	  return end;
   }

   public long getLength() {
	  return length;
   }

   public long getTotal() {
	  return total;
   }

}
