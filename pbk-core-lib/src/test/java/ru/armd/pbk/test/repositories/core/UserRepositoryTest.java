package ru.armd.pbk.test.repositories.core;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.core.User;
import ru.armd.pbk.repositories.core.UserRepository;
import ru.armd.pbk.test.repositories.BaseDomainRepositoryTest;
import ru.armd.pbk.utils.date.DateUtils;

import java.util.Date;

@Transactional
public class UserRepositoryTest
	extends BaseDomainRepositoryTest<User> {

   @Autowired
   protected UserRepository userRepository;

   @Override
   protected BaseDomainRepository<User> getDomainRepository() {
	  return userRepository;
   }

   @Override
   protected User initDomain(Long id) {
	  User user = new User();
	  user.setLogin("Test User Login");
	  user.setName("Test User Name");
	  user.setExpirationDate(DateUtils.addDays(new Date(), 100));
	  return user;
   }

   @Test
   public void domainTest()
	   throws InterruptedException {
	  User user = createTest();
	  user = getByIdTest(user.getId());
	  user = updateTest(user.getId());
	  deleteTest(user.getId());
   }
}
