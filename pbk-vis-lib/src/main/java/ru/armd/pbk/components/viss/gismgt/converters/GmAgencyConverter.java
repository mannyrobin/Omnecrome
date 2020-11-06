package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmAgency;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RAgency;
import ru.armd.pbk.gismgt.schema.TAgency;
import ru.armd.pbk.mappers.viss.gismgt.GmAgencyMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TAgency в Agency.
 */
@Component
public class GmAgencyConverter
	extends AbstractGmConverter<RAgency, GmAgency> {

   public static final Logger LOGGER = Logger.getLogger(GmAgencyConverter.class);

   @Autowired
   private GmAgencyMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmAgencyConverter() {
	  super(VisAuditType.VIS_GISMGT_AGENCY);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmAgency> getMapper() {
	  return mapper;
   }

   @Override
   protected Boolean merge(GmAgency to, GmAgency from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  if (from.getOrganizationFormMuid() != null && !from.getOrganizationFormMuid().equals(to.getOrganizationFormMuid())) {
		 result = true;
		 to.setOrganizationFormId(from.getOrganizationFormMuid());
	  }
	  if (from.getLegalAddress() != null && !from.getLegalAddress().equals(to.getLegalAddress())) {
		 result = true;
		 to.setLegalAddress(from.getLegalAddress());
	  }
	  if (from.getActualAddress() != null && !from.getActualAddress().equals(to.getActualAddress())) {
		 result = true;
		 to.setActualAddress(from.getActualAddress());
	  }
	  if (from.getContactInformation() != null && !from.getContactInformation().equals(to.getContactInformation())) {
		 result = true;
		 to.setContactInformation(from.getContactInformation());
	  }
	  return result;
   }

   @Override
   protected GmAgency fillMain(Long muid) {
	  GmAgency domain = new GmAgency();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected GmAgency convert(RAgency gis) {
	  TAgency body = gis.getBody();
	  GmAgency domain = new GmAgency();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  domain.setActualAddress(getStringValue(body.getActualAddress()));
	  domain.setContactInformation(getStringValue(body.getContactInformation()));
	  domain.setLegalAddress(getStringValue(body.getLegalAddress()));
	  if (body.getOrganizationForm() != null) {
		 domain.setOrganizationFormId(getId(body.getOrganizationForm().getValue().getHeader().getIdentSet()));
	  }
	  return domain;
   }

   @Override
   protected RAgency toGis(ObjectMessage objectMessage) {
	  RAgency gis = new RAgency();
	  RAgency.Header header = new RAgency.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTAgency());
	  gis.setHeader(header);
	  return gis;
   }
} 
