package ru.armd.pbk.components.viss.easu;

import com.sap.document.sap.rfc.functions.ZPBKBALResponse;
import com.sap.document.sap.rfc.functions.ZPBKDEPResponse;
import com.sap.document.sap.rfc.functions.ZPBKPNRResponse;
import com.sap.document.sap.rfc.functions.ZPBKSDEPART;
import com.sap.document.sap.rfc.functions.ZPBKSPERNR;
import com.sap.document.sap.rfc.functions.ZPMINTEGRPBKTCResponse;
import com.sap.document.sap.rfc.functions.ZPMSINTEGRPBK;
import com.sap.document.sap.rfc.functions.utils.EasuFdhJaxbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.easu.converters.EasuDepartmentConverter;
import ru.armd.pbk.components.viss.easu.converters.EasuEmployeeConverter;
import ru.armd.pbk.components.viss.easu.converters.EasuEmployeeWorkModeConverter;
import ru.armd.pbk.components.viss.easu.converters.EasuTsVeniclesConverter;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.enums.viss.VisExchangeObjects;

import javax.xml.bind.JAXBException;
import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Загрузчик для ЕАСУ ФХД.
 */
@Component
public class EasuFhdLoader
	extends BaseComponent {

   @Autowired
   private EasuDepartmentConverter departmentConverter;

   @Autowired
   private EasuEmployeeConverter employeeConverter;

   @Autowired
   private EasuEmployeeWorkModeConverter employeeWorkModeConverter;

   @Autowired
   private EasuTsVeniclesConverter tsVeniclesConverter;

   @Autowired
   private EasuFhdSubProcessor processor;

   /**
	* Базовый конструктор.
	*/
   public EasuFhdLoader() {

   }

   /**
	* Загрузить из xml.
	*
	* @param is       - входной поток файла xml.
	* @param workDate дата.
	* @throws JAXBException
	* @throws NoSuchMethodException
	* @throws SecurityException
	* @throws IllegalAccessException
	* @throws IllegalArgumentException
	* @throws InvocationTargetException
	* @throws SOAPException
	* @throws IOException
	*/
   public void loadFromXml(InputStream is, Date workDate)
	   throws JAXBException, NoSuchMethodException, SecurityException,
	   IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, SOAPException {
	  parseObject(EasuFdhJaxbUtils.getSoapObject(is), workDate);
   }

   /**
	* Разобрать объект и загрузить.
	*
	* @param object   - объект.
	* @param workDate - дата.
	*/
   public void parseObject(Object object, Date workDate) {
	  if (object instanceof ZPBKDEPResponse) {
		 ZPBKDEPResponse departmentResponse = (ZPBKDEPResponse) object;
		 for (ZPBKSDEPART department : departmentResponse.getReturn().getDEPARTS().getItem()) {
			if (departmentConverter.save(department) != null) {
			   processor.doImport(VisExchangeObjects.EASU_FHD_EMPLOYEE, department.getOBJID(), workDate, workDate, true);
			}
		 }
	  } else if (object instanceof ZPBKPNRResponse) {
		 ZPBKPNRResponse employeeResponse = (ZPBKPNRResponse) object;
		 for (ZPBKSPERNR employee : employeeResponse.getReturn().getPERNRS().getItem()) {
			if (employeeConverter.save(employee) != null) {
			   processor.doImport(VisExchangeObjects.EASU_FHD_WORK_MODE, employee.getPERNR(), workDate, workDate, true);
			}
		 }
	  } else if (object instanceof ZPBKBALResponse) {
		 ZPBKBALResponse workModeResponse = (ZPBKBALResponse) object;
		 employeeWorkModeConverter.save(workModeResponse.getReturn().getBALANS().getItem(), workDate);
			/*for (ZPBKSBAL workMode : workModeResponse.getBALANS().getItem()){
				employeeWorkModeConverter.save(workMode);
			}*/
		 //employeeWorkModeConverter.postProcess();

	  } else if (object instanceof ZPMINTEGRPBKTCResponse) {
		 ZPMINTEGRPBKTCResponse tsResponse = (ZPMINTEGRPBKTCResponse) object;
		 for (ZPMSINTEGRPBK ts : tsResponse.getReturn().getETINTEGRPBKTC().getItem()) {
			tsVeniclesConverter.save(ts);
		 }
	  }
   }

}
