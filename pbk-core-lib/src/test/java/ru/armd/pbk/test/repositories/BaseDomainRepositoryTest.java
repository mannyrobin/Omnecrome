package ru.armd.pbk.test.repositories;

import org.junit.Assert;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.test.BaseCoreTest;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.Date;

public abstract class BaseDomainRepositoryTest<Domain extends BaseDomain>
	extends BaseCoreTest {

   protected abstract Domain initDomain(Long id);

   protected abstract BaseDomainRepository<Domain> getDomainRepository();

   protected Domain createTest() {
	  Domain domain = initDomain(null);
	  domain = getDomainRepository().save(domain);
	  Assert.assertNotNull("Id is null", domain.getId());
	  return domain;
   }

   protected Domain getByIdTest(Long id) {
	  Domain domainById = getDomainRepository().getById(id);
	  Assert.assertNotNull("domainById is null", domainById);
	  return domainById;
   }

   protected Domain updateTest(Long id)
	   throws InterruptedException {
	  Domain domain = getByIdTest(id);
	  Date updateDate = domain.getUpdateDate();
	  domain.setUpdateDate(DateUtils.addDays(updateDate, 10));
	  domain = getDomainRepository().save(domain);
	  Assert.assertTrue("domain not updated", (domain.getUpdateDate().getTime() - updateDate.getTime() > 0));
	  return domain;
   }

   protected void deleteTest(Long id) {
	  int count = getDomainRepository().delete(id);
	  Assert.assertTrue("domain not deleted", count > 0);
   }

}
