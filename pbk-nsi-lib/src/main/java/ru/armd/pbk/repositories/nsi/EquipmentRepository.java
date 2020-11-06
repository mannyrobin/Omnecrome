package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.domain.nsi.Equipment;
import ru.armd.pbk.domain.nsi.EquipmentShort;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.EquipmentMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий бортового оборудования.
 */
@Repository
public class EquipmentRepository
	extends BaseVersionDomainRepository<Equipment> {

   public static final Logger LOGGER = Logger.getLogger(EquipmentRepository.class);

   EquipmentMapper mapper;

   @Autowired
   EquipmentRepository(EquipmentMapper mapper) {
	  super(NsiAuditType.NSI_EQUIPMENT, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить ASDU бортовое оборудование по ID.
	*
	* @param id - ID ASDU бортового оборудования.
	* @return ASDU бортовое оборудование.
	*/
   public Equipment getByAsduEquipmentId(String id) {
	  Map<String, Object> params = new HashMap<>();
	  params.put("asduEquipmentId", id);
	  List<Equipment> domains = getDomains(params);
	  if (domains.size() == 1) {
		 return domains.get(0);
	  } else if (domains.size() <= 1) {
		 return null;
	  }
	  throw new PBKException("getByAsduVenicleId return more 1 domain");
   }

   /**
	* Приведение АСДУ к Map<>.
	*
	* @return
	*/
   public Map<String, Long> getAsduToPbkKeyMap() {
	  List<EquipmentShort> list = mapper.getDomainsShort();
	  Map<String, Long> map = new HashMap<String, Long>();
	  for (EquipmentShort eq : list) {
		 map.put(eq.getAsduEquipmentId(), eq.getHeadId());
	  }
	  return map;
   }
}