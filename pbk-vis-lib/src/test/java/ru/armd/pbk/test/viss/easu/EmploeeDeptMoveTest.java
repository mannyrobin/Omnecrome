package ru.armd.pbk.test.viss.easu;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.components.viss.easu.EasuFhdLoader;
import ru.armd.pbk.services.plans.employees.PlanEmployeeService;
import ru.armd.pbk.test.BaseVisTest;

import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class EmploeeDeptMoveTest
	extends BaseVisTest {

   @Autowired
   private EasuFhdLoader loader;

   @Autowired
   PlanEmployeeService service;

   @Test
   public void test()
	   throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, JAXBException, IOException, SOAPException {
	  String filePath = getTestsDataDir();
	  filePath += "easu\\20170111140106493_EASU_FHD_WORK_MODE_20170111_soap.xml";
	  File file = new File(filePath);
	  loader.loadFromXml(new FileInputStream(file), new Date());
   }

}
