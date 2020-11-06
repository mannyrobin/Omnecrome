package ru.armd.pbk.components.viss.gtfs.providers;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsReplaceVehicleLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsReplaceVehicle;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер "Замена ТС".
 */
@Component
public class GtfsReplaceVehicleProvider
	extends GtfsBaseProvider<GtfsReplaceVehicle> {

   @Autowired
   GtfsReplaceVehicleProvider(ObjectFactory<GtfsReplaceVehicleLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_REPLACE_VEHICLE);
   }

}
