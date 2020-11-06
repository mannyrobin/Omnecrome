package ru.armd.pbk.mappers.dvr;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.dvr.DvrRecord;

/**
 * Маппер для записи с видеорегистратора.
 */
@IsMapper
public interface DvrRecordMapper
	extends IDomainMapper<DvrRecord> {

   /**
	* Привязать записи с ведорегистраторов к заданию.
	*
	* @param taskId - ИД задания.
	* @return количество привзяных.
	*/
   int bind(@Param("taskId") Long taskId);

}
