package ru.armd.pbk.repositories;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.enums.core.ReportAuditType;
import ru.armd.pbk.mappers.report.standard.So19Mapper;

/**
 * Репозиторий стандартного отчёта "Количество совместных бригад с ГКУ ОП".
 */
@Repository
public class So19Repository
	extends BaseDomainRepository<BaseDomain> {

	public static final Logger LOGGER = Logger.getLogger(So19Repository.class);

	@Autowired
	So19Repository(So19Mapper mapper) {
		super(ReportAuditType.REPORT_STANDARD_SO19, mapper);
	}

	@Override
	public Logger getLogger() {
		return LOGGER;
	}


}