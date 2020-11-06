package ru.armd.pbk.mappers.report.standard;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;

import java.util.Date;

/**
 * Маппер стандартного отчёта "Количество бригад по местам встречи".
 */
@IsMapper
public interface So3Mapper
	extends SoMapper<BaseDomain> {

   /**
	* Получает кол-во заданий для бригады.
	*
	* @param brigadeId id бригады
	* @return кол-во заданий для бригады
	*/
   int getBrigadeTaskCount(@Param("brigadeId") Long brigadeId);

   /**
	* Получить разницу между кол-вом контролеров по расписанию и кол-вом
	* контролеров по бригадам.
	*
	* @param deptId   id подразделения
	* @param workDate дата
	* @param shiftId  id смены
	* @return разницу между кол-вом контролеров по расписанию и кол-вом
	* контролеров по бригадам
	*/
   int getDifferenceForBrigadeState(@Param("deptId") Long deptId, @Param("workDate") Date workDate,
									@Param("shiftId") Long shiftId);
}
