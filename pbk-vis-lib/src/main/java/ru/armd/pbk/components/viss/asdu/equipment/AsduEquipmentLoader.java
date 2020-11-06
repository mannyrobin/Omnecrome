package ru.armd.pbk.components.viss.asdu.equipment;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvDomainLoader;
import ru.armd.pbk.domain.nsi.Equipment;
import ru.armd.pbk.domain.nsi.Venicle;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.nsi.EquipmentRepository;
import ru.armd.pbk.repositories.nsi.VenicleRepository;

/**
 * Лоадер для водителей.
 */
@Component
public class AsduEquipmentLoader
	extends BaseCsvDomainLoader<Equipment> {

   private static final Logger LOGGER = Logger.getLogger(AsduEquipmentLoader.class);

   private EquipmentRepository equipmentRepository;

   private VenicleRepository venicleRepository;

   @Autowired
   AsduEquipmentLoader(EquipmentRepository equipmentRepository, VenicleRepository venicleRepository) {
	  super(equipmentRepository);
	  this.equipmentRepository = equipmentRepository;
	  this.venicleRepository = venicleRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public AuditType getAuditType() {
	  return VisAuditType.VIS_ASDU_EQUIPMENT;
   }

   @Override
   protected Equipment createDomain(String[] fields) {
	  // EQUIPMENT_ID;VEHICLE_ID;TYPE;GOSNUM;
	  Equipment equipment = new Equipment();
	  equipment.setAsduEquipmentId(getStringValue(fields[0]));
	  equipment.setAsduVenicleId(getStringValue(fields[1]));
	  Venicle venicle = venicleRepository.getByAsduVenicleId(getStringValue(fields[1]));
	  if (venicle != null) {
		 equipment.setVenicleId(venicle.getId());
	  }
	  // TODO: type
	  // TODO: gosnum
	  return equipment;
   }

   @Override
   protected Equipment getExistedDomain(Equipment newDomain) {
	  return equipmentRepository.getByAsduEquipmentId(newDomain.getAsduEquipmentId());
   }

}
