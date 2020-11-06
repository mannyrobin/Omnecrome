package ru.armd.pbk.mappers.plan;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.views.plan.TimesheetReportView;

import java.util.Date;
import java.util.List;

@IsMapper
public interface TimesheetReportMapper {

   List<TimesheetReportView> getTimesheetReportView(@Param("deptId") Long deptId,
													@Param("dateFrom") Date dateFrom,
													@Param("dateTo") Date dateTo);

}
