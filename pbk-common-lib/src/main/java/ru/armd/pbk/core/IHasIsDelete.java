package ru.armd.pbk.core;

/**
 * Маркировка, сущеностей которые имеют признак IsDelete.
 */
public interface IHasIsDelete {

   /**
	* Получить признак IsDelete.
	*
	* @return признак IsDelete.
	*/
   int getIsDelete();

   /**
	* Установить признак IsDelete.
	*
	* @param isDelete признак IsDelete.
	*/
   void setIsDelete(int isDelete);

}
