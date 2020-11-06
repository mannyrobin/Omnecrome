package ru.armd.pbk.exceptions;

/**
 * Базовый класс исключений валидации.
 */
public class PBKValidationException
	extends RuntimeException {
   private static final long serialVersionUID = 3347162459770144628L;

   private String name;

   /**
	* Конструктор.
	*
	* @param name    Имя поля.
	* @param message Сообщение.
	*/
   public PBKValidationException(String name, String message) {
	  super(message);
	  this.setName(name);
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }
}
