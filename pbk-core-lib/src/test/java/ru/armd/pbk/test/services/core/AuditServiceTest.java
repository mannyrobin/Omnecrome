package ru.armd.pbk.test.services.core;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.services.core.AuditService;
import ru.armd.pbk.test.BaseCoreTest;

public class AuditServiceTest
	extends BaseCoreTest {

   @Autowired
   private AuditService auditService;

   @Test
   public void testRemoveOldValues() {
	  auditService.removeOldValues();
   }
}
