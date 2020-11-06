package ru.armd.pbk.services.nsi.branch;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.branch.Branch;
import ru.armd.pbk.dto.nsi.branch.BranchDTO;
import ru.armd.pbk.matcher.nsi.IBranchMatcher;
import ru.armd.pbk.repositories.nsi.branch.BranchRepository;

/**
 * Сервисы филиалов.
 */
@Service
public class BranchService
	extends BaseDomainService<Branch, BranchDTO> {

   private static final Logger LOGGER = Logger.getLogger(BranchService.class);

   @Autowired
   BranchService(BranchRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Branch toDomain(BranchDTO dto) {
	  return IBranchMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public BranchDTO toDTO(Branch domain) {
	  return IBranchMatcher.INSTANCE.toDTO(domain);
   }
}
