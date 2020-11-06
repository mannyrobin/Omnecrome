package ru.armd.pbk.utils.date;

import org.joda.time.LocalDate;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Диапазон дат.
 */
public class DateRange
	implements Iterable<LocalDate> {
   private final LocalDate start;
   private final LocalDate end;

   /**
	* Конструктор.
	*
	* @param start Дата начала.
	* @param end   Дата окончания.
	*/
   public DateRange(LocalDate start, LocalDate end) {
	  this.start = start;
	  this.end = end;
   }

   @Override
   public Iterator<LocalDate> iterator() {
	  return new LocalDateRangeIterator(start, end);
   }

   /**
	* Реализация итератора дат.
	*/
   private static class LocalDateRangeIterator
	   implements Iterator<LocalDate> {
	  private LocalDate current;
	  private final LocalDate end;

	  /**
	   * Конструктор.
	   *
	   * @param start Дата начала.
	   * @param end   Дата окончания.
	   */
	  private LocalDateRangeIterator(LocalDate start, LocalDate end) {
		 this.current = start;
		 this.end = end;
	  }

	  @Override
	  public boolean hasNext() {
		 return current != null;
	  }

	  @Override
	  public LocalDate next() {
		 if (current == null) {
			throw new NoSuchElementException();
		 }
		 LocalDate ret = current;
		 current = current.plusDays(1);
		 if (current.compareTo(end) > 0) {
			current = null;
		 }
		 return ret;
	  }

	  @Override
	  public void remove() {
		 throw new UnsupportedOperationException();
	  }
   }
}
