package ru.armd.pbk.core.views;

import java.util.List;

/**
 * Базовый интерфейс для предствалений отчетов.
 */
public interface IReportView
	extends IView {

   /**
	* Получить список значений полей представления.
	*
	* @return список значений полей представления.
	*/
   List<Object> getReportList();

   /**
	* Получить список заголовков представления.
	*
	* @return список заголовков представления.
	*/
   List<String> getReportHeaders();

}
