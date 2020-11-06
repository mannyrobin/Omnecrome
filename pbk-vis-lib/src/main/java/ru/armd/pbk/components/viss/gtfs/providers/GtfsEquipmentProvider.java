package ru.armd.pbk.components.viss.gtfs.providers;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsEquipmentLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsEquipment;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер бортового оборудования.
 */
@Component
public class GtfsEquipmentProvider
	extends GtfsBaseProvider<GtfsEquipment> {

   @Autowired
   GtfsEquipmentProvider(ObjectFactory<GtfsEquipmentLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_EQUIPMENT);
   }

}
