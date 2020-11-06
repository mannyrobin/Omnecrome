package ru.armd.pbk.mappers.report.standard;


import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.views.report.So20CheckPlanView;

import java.util.List;

/**
 * Created by Yakov Volkov.
 * Маппер стандартного отчёта "Статистика проверок маршрута".
 */
@IsMapper
public interface So20Mapper
	extends IDomainMapper<BaseDomain> {

   List<So20CheckPlanView> getCheckPlanView();
}
