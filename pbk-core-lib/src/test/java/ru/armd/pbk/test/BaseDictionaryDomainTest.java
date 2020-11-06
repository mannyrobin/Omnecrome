package ru.armd.pbk.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import ru.armd.pbk.domain.core.Permission;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseDictionaryDomainTest {

   private static final Logger LOGGER = Logger.getLogger(BaseDictionaryDomainTest.class);

   @org.junit.Test
   public void testToAuditString()
	   throws Exception {
	  Permission permission = new Permission();
	  permission.setId(123L);
	  permission.setCod("c1");
	  permission.setName("Name12");
	  LOGGER.info(permission.toAuditString());

   }

   @Test
   public void testDateFormat() {
	  String name = new SimpleDateFormat("MMMM - YYYY").format(new Date());
	  System.out.println(name);
	  LOGGER.info(name);
   }

   @Test
   public void testDate() {
	  String name = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date(1456303247));

	  System.out.println(name);
	  LOGGER.info(name);

	  name = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date(1456303254));
	  System.out.println(name);
	  LOGGER.info(name);
   }

}