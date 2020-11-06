package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsChangeEquipment;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsChangeEquipmentMapper;

/**
 * Репозиторий изменения бортового оборудования на ТС.
 */
@Repository
public class GtfsChangeEquipmentRepository
	extends BaseDomainRepository<GtfsChangeEquipment> {

   @Autowired
   GtfsChangeEquipmentRepository(GtfsChangeEquipmentMapper mapper) {
	  super(VisAuditType.VIS_GTFS_CHANGE_EQUIPMENT, mapper);
   }
}
