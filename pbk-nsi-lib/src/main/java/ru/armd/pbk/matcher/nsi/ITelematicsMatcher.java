package ru.armd.pbk.matcher.nsi;

import org.joda.time.DateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.domain.nsi.Telematics;
import ru.armd.pbk.dto.nsi.TelematicsDTO;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Мапер для сопоставления различных типов сущности телематики.
 */
@Mapper
public abstract class ITelematicsMatcher {

   public static final ITelematicsMatcher INSTANCE = Mappers.getMapper(ITelematicsMatcher.class);

   /**
	* Базовый перевод ДТО телематики в домен телематики.
	*
	* @param dto - ДТО телематики.
	* @return - домен телематики.
	*/
   @Mappings( {@Mapping(target = "pointTime", ignore = true),
	   @Mapping(target = "createUserId", ignore = true),
	   @Mapping(target = "createDate", ignore = true),
	   @Mapping(target = "updateUserId", ignore = true),
	   @Mapping(target = "updateDate", ignore = true)})
   public abstract Telematics baseToDomain(TelematicsDTO dto);

   /**
	* Базовый перевод домена телематики в ДТО телематики.
	*
	* @param domain - домен телематики.
	* @return ДТО телематики.
	*/
   @Mappings( {@Mapping(target = "pointTimeDTO", ignore = true),
	   @Mapping(target = "pointDateDTO", ignore = true)})
   public abstract TelematicsDTO baseToDTO(Telematics domain);

   /**
	* Перевод Домена телематики в ДТО телематики.
	*
	* @param domain - домен телематики.
	* @return - ДТО телематики.
	*/
   public TelematicsDTO toDTO(Telematics domain) {
	  TelematicsDTO dto = baseToDTO(domain);

	  Calendar dateTime = new GregorianCalendar();
	  dateTime.setTime(domain.getPointTime());
	  dateTime.set(Calendar.HOUR, 0);
	  dateTime.set(Calendar.MINUTE, 0);
	  dateTime.set(Calendar.SECOND, 0);
	  dto.setPointDateDTO(dateTime.getTime());

	  dateTime.set(0, 0, 0,
		  domain.getPointTime().getHours(),
		  domain.getPointTime().getMinutes(),
		  domain.getPointTime().getSeconds());
	  dateTime.add(Calendar.HOUR, 1);

	  dto.setPointTimeDTO(dateTime.getTime());
	  return dto;
   }

   /**
	* Перевод ДТО телематики в домен телематики.
	*
	* @param dto - ДТО телематики.
	* @return - домен телематики.
	*/
   public Telematics toDomain(TelematicsDTO dto) {
	  Telematics domain = baseToDomain(dto);
	  if (dto.getPointDateDTO() != null && dto.getPointTimeDTO() != null) {
		 Calendar date = new GregorianCalendar(0, 0, 0, 0, 0, 0);
		 date.setTime(dto.getPointDateDTO());

		 date.set(Calendar.HOUR, dto.getPointTimeDTO().getHours());
		 date.set(Calendar.MINUTE, dto.getPointTimeDTO().getMinutes());

		 DateTime dateTime = new DateTime(date.getTime());

		 domain.setPointTime(dateTime.toDate());
	  }
	  return domain;
   }
}