package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsImpactCode;
import ru.armd.pbk.repositories.viss.gfts.GtfsImpactCodeRepository;

/**
 * Загрузчик "Коды управляющих воздействий".
 */
@Component
@Scope("prototype")
public class GtfsImpactCodeLoader
	extends GtfsLoader<GtfsImpactCode> {

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsImpactCodeLoader(GtfsImpactCodeRepository domainRepository) {
	  super(domainRepository, false);
   }

   @Override
   protected GtfsImpactCode createDomain(String[] fields) {
	  GtfsImpactCode domain = new GtfsImpactCode();
	  domain.setWorkDate(getWorkDate());
	  domain.setImpactCode(getStringValue(fields[1]));
	  domain.setImpactId(getIntegerValue(fields[0]));
	  domain.setImpactName(fields[2]);
	  return domain;
   }

}
