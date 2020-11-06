package ru.armd.pbk.components.viss.gtfs.providers;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsImpactCodeLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsImpactCode;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер "Коды управляющих воздействий".
 */
@Component
public class GtfsImpactCodeProvider
	extends GtfsBaseProvider<GtfsImpactCode> {

   @Autowired
   GtfsImpactCodeProvider(ObjectFactory<GtfsImpactCodeLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_IMPACT_CODE);
   }

}
