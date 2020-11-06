package ru.armd.pbk.repositories.nsi.branch;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.branch.Branch;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.branch.BranchMapper;

/**
 * Репозиторий филиалов.
 */
@Repository
public class BranchRepository
	extends BaseDomainRepository<Branch> {

   public static final Logger LOGGER = Logger.getLogger(BranchRepository.class);

   @Autowired
   BranchRepository(BranchMapper mapper) {
	  super(NsiAuditType.NSI_BRANCH, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
