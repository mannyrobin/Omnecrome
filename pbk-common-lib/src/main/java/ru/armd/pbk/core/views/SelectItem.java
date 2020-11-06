package ru.armd.pbk.core.views;

/**
 * Класс используется для отображения элементов в списках.
 */
public class SelectItem
	implements ISelectItem {

   private Long id;
   private String name;

   /**
	* Конструктор по умолчанию.
	*/
   public SelectItem() {
   }

   /**
	* Конструктор.
	*
	* @param id   Id элемента.
	* @param name Название элемента.
	*/
   public SelectItem(Long id, String name) {
	  super();
	  this.id = id;
	  this.name = name;
   }

   @Override
   public Long getId() {
	  return id;
   }

   @Override
   public void setId(Long id) {
	  this.id = id;
   }

   @Override
   public String getName() {
	  return name;
   }

   @Override
   public void setName(String name) {
	  this.name = name;
   }

}
