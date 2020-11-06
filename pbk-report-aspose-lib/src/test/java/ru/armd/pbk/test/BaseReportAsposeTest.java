package ru.armd.pbk.test;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.armd.pbk.core.test.BaseTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-spring-config/application-context.xml", "classpath:/test-spring-config/aspose-report-context.xml"})
public class BaseReportAsposeTest
	extends BaseTest {

   private static final Logger LOGGER = Logger.getLogger(BaseReportAsposeTest.class);

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }


   @Before
   public void setUp()
	   throws Exception {
	  loginUser(AUTH_USER_NAME, AUTH_USER_PASS);
   }

}
