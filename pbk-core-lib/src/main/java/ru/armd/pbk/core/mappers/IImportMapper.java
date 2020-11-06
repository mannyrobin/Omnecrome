package ru.armd.pbk.core.mappers;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;
import java.util.List;

/**
 * Интерфейс маппер с общимии методами для загрузки данных из ВИС.
 *
 * @param <Domain> Домен.
 */
public interface IImportMapper<Domain extends BaseDomain>
	extends IMapper {

   /**
	* Вставить объекты в таблицу.
	*
	* @param domains - объекты.
	* @return
	*/
   int insertChunck(List<Domain> domains);

   /**
	* Вставить объекты в таблицу.
	*
	* @param domains - объекты.
	* @return
	*/
   int insertChunckOnDate(@Param("domains") List<Domain> domains, @Param("workDate") Date workDate);

}
