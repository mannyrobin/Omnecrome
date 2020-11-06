package ru.armd.pbk.mappers.viss.calc;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;

/**
 * Маппер для подготовки схемы и таблиц рассчета ПП на дату.
 */
@IsMapper
public interface CalcMapper {

   /**
	* Создает схему необходимую для расчтена ПП на дату.
	*
	* @param schema название схемы.
	*/
   void createSchema(@Param("schema") String schema);

   /**
	* Проверяет на существование схемы
	* расчета ПП на дату.
	*
	* @param schema название схемы.
	* @return
	*/
   Boolean isSchemaExists(@Param("schema") String schema);

}
