package ru.armd.pbk.repositories.viss.gfts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gtfs.GtfsEquipment;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gtfs.GtfsEquipmentMapper;

/**
 * Репозиторий бортового оборудования.
 */
@Repository
public class GtfsEquipmentRepository
	extends BaseDomainRepository<GtfsEquipment> {

   @Autowired
   GtfsEquipmentRepository(GtfsEquipmentMapper mapper) {
	  super(VisAuditType.VIS_GTFS_EQUIPMENT, mapper);
   }

}
