package ru.armd.pbk.components.viss.easu.triporder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvDomainLoader;
import ru.armd.pbk.domain.nsi.Duty;
import ru.armd.pbk.repositories.nsi.DutyRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Загрузчик нарядов.
 */
@Component
public class EasuTripOrderLoader
	extends BaseCsvDomainLoader<Duty> {

   @Autowired
   EasuTripOrderLoader(DutyRepository domainRepository) {
	  super(domainRepository);
   }

   @Override
   protected Duty createDomain(String[] fields) {
	  //PRKNU;ADDAT;COLNU;ROUNR_EXT;DEPNR_EXT;TIME_FROM;TIME_TO;RKIND;EQFNR;EQFNR2;SHNUM;PERNR;RTYPE
	  DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	  DateFormat timeormat = new SimpleDateFormat("hhmmss");
	  Duty domain = new Duty();
	  try {
		 domain.setDriverPersonalNumber(fields[11]);
		 domain.setEasuFhdRouteCode(fields[3]);
		 domain.setMoveCode(fields[4]);
		 domain.setMoveStartTime(timeormat.parse(fields[5]));
		 domain.setMoveEndTime(timeormat.parse(fields[6]));
		 domain.setTsDepoNumber(fields[8]);
		 domain.setWorkDate(dateFormat.parse(fields[1]));
	  } catch (ParseException e) {

	  }
	  return domain;
   }

   @Override
   protected Duty getExistedDomain(Duty newDomain) {
	  // уникально
	  return null;
   }

}
