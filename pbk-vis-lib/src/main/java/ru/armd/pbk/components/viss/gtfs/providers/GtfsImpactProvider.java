package ru.armd.pbk.components.viss.gtfs.providers;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsImpactLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsImpact;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер "Управляющие воздействия".
 */
@Component
public class GtfsImpactProvider
	extends GtfsBaseProvider<GtfsImpact> {

   @Autowired
   GtfsImpactProvider(ObjectFactory<GtfsImpactLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_IMPACT);
   }

}
