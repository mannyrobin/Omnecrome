package ru.armd.pbk.test.repositories;

import org.junit.Assert;
import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;

import java.util.List;

/**
 * Created by dmitry_kurkin on 15.06.2016.
 */
public abstract class BaseVersionDomainRepositoryTest<VersionDomain extends BaseVersionDomain>
	extends BaseDomainRepositoryTest<VersionDomain> {
   public abstract BaseVersionDomainRepository<VersionDomain> getVersionRepository();

   public VersionDomain saveVersion(VersionDomain versionDomain) {
	  VersionDomain actual = null;
	  if (versionDomain.getHeadId() == null) {
		 actual = getVersionRepository().getActual(versionDomain.getHeadId());
	  }
	  VersionDomain newVersion = getVersionRepository().saveVersion(versionDomain);
	  if (actual != null) {
		 VersionDomain previousActual = getVersionRepository().getById(actual.getId());
		 Assert.assertEquals(previousActual.getVersionEndDate(), getActual(versionDomain.getHeadId()).getVersionStartDate());
	  }
	  return newVersion;
   }

   public VersionDomain getActual(Long headId) {
	  VersionDomain actual = getVersionRepository().getActual(headId);
	  Assert.assertNotNull(actual);
	  return actual;
   }

   public List<VersionDomain> getHistory(Long headId, long expectedSize) {
	  List<VersionDomain> history = getVersionRepository().getHistory(headId);
	  Assert.assertNotNull(history);
	  Assert.assertEquals((long) history.size(), expectedSize);
	  return history;
   }
}
