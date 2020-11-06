package ru.armd.pbk.core;

/**
 * Интерфейс, позволяющий получать строку с инфорацией об объекте для аудита.
 */
public interface IAuditableString {

   String FIELD_SEPARATOR = "; ";
   String FIELD_NULL = "null";

   /**
	* Формирует строку с инфорацией об объекте.
	*
	* @return Строка.
	*/
   String toAuditString();
}
