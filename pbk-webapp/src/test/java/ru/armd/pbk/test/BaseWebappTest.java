package ru.armd.pbk.test;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.armd.pbk.core.test.BaseTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/test-spring-config/application-context.xml"})
public class BaseWebappTest
	extends BaseTest {

   private static final Logger LOGGER = Logger.getLogger(BaseWebappTest.class);

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Autowired
   private WebApplicationContext wac;

   private MockMvc mockMvc;

   @Before
   public void setUp()
	   throws Exception {
	  loginUser(AUTH_USER_NAME, AUTH_USER_PASS);
	  this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
   }

   //@Test
   public void test()
	   throws Exception {
	  this.mockMvc.perform(get("api/pbk/info/accounts//version").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
		  .andExpect(status().isOk());
   }

}
