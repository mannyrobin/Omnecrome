package ru.armd.pbk.components.viss.gtfs.providers;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsChangeEquipmentLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsChangeEquipment;
import ru.armd.pbk.enums.core.VisAuditType;


/**
 * Провайдер изменения бортового оборудования на ТС.
 */
@Component
public class GtfsChangeEquipmentProvider
	extends GtfsBaseProvider<GtfsChangeEquipment> {

   @Autowired
   GtfsChangeEquipmentProvider(ObjectFactory<GtfsChangeEquipmentLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_CHANGE_EQUIPMENT);
   }

}
