package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.domain.nsi.department.Department;
import ru.armd.pbk.dto.nsi.department.DepartmentDTO;
import ru.armd.pbk.utils.WktGeomPoint;
import ru.armd.pbk.utils.WktGeomUtils;

/**
 * Мапер для сопостовления различных типов сущности "подразделение".
 */
@Mapper
public abstract class IDepartmentMatcher {

   public static final IDepartmentMatcher INSTANCE = Mappers.getMapper(IDepartmentMatcher.class);

   /**
	* Базовый перевод ДТО подразделения в домен подразделения.
	*
	* @param dto - ДТО.
	* @return - домен.
	*/
   @Mappings( {@Mapping(target = "wktGeom", ignore = true),
	   @Mapping(target = "forPlanUse", ignore = true),
	   @Mapping(target = "createUserId", ignore = true),
	   @Mapping(target = "createDate", ignore = true),
	   @Mapping(target = "updateUserId", ignore = true),
	   @Mapping(target = "updateDate", ignore = true),
	   @Mapping(target = "versionStartDate", source = "dto.versionStartDate"),
	   @Mapping(target = "versionEndDate", source = "dto.versionEndDate")})
   public abstract Department baseToDomain(DepartmentDTO dto);

   /**
	* Базовый перевод домена подразделения в ДТО подразделения.
	*
	* @param domain - домен.
	* @return - ДТО.
	*/
   @Mappings( {@Mapping(target = "id", source = "domain.id"),
	   @Mapping(target = "isDelete", source = "domain.isDelete"),
	   @Mapping(target = "longitude", ignore = true),
	   @Mapping(target = "latitude", ignore = true),
	   @Mapping(target = "name", source = "domain.name"),
	   @Mapping(target = "parentDeptId", source = "domain.parentDeptId"),
	   @Mapping(target = "description", source = "domain.description"),
	   @Mapping(target = "fullName", source = "domain.fullName"),
	   @Mapping(target = "easuFhdId", source = "domain.easuFhdId"),
	   @Mapping(target = "planCount", source = "domain.planCount"),
	   @Mapping(target = "headId", source = "domain.headId"),
	   @Mapping(target = "employeeSignId", source = "domain.employeeSignId"),
	   @Mapping(target = "forPlanUse", ignore = true)})
   public abstract DepartmentDTO baseToDTO(Department domain);

   /**
	* Перевод домена подразделения в ДТО подразделения.
	*
	* @param domain - домен.
	* @return - ДТО.
	*/
   public DepartmentDTO toDTO(Department domain) {
	  DepartmentDTO dto = baseToDTO(domain);
	  if (domain.getForPlanUse() == 1) {
		 dto.setForPlanUse(true);
	  } else {
		 dto.setForPlanUse(false);
	  }
	  WktGeomPoint point = WktGeomUtils.stringToWktGeomPoint(domain.getWktGeom());
	  if (point != null) {
		 dto.setLatitude(point.getLatitude());
		 dto.setLongitude(point.getLongitude());
	  }
	  return dto;
   }

   /**
	* Перевод ДТО подразделения в домен подразделения.
	*
	* @param dto - ДТО.
	* @return - домен.
	*/
   public Department toDomain(DepartmentDTO dto) {
	  Department domain = baseToDomain(dto);
	  if (dto.getForPlanUse()) {
		 domain.setForPlanUse(1);
	  } else {
		 domain.setForPlanUse(0);
	  }
	  domain.setWktGeom(WktGeomUtils.wktGeomPointToString(new WktGeomPoint(dto.getLatitude(), dto.getLongitude())));
	  return domain;
   }
}