package ru.armd.pbk.test.viss.easu;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.services.plans.employees.PlanEmployeeService;
import ru.armd.pbk.test.BaseVisTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmpDeptMoveTest
	extends BaseVisTest {

   @Autowired
   private PlanEmployeeService planEmployeeService;

   @Test
   public void test()
	   throws ParseException {
	  SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	  planEmployeeService.moveEmployee(492l, 33l, 28l, df.parse("20161205"));
   }

}
