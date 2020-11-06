package ru.armd.pbk.components.viss.gismgt.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmDistrictEgko;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.REGKODistrict;
import ru.armd.pbk.gismgt.schema.TEGKODistrict;
import ru.armd.pbk.mappers.viss.gismgt.GmDistrictEgkoMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

@Component
public class GmDistrictEgkoConverter
	extends AbstractGmConverter<REGKODistrict, GmDistrictEgko> {

   @Autowired
   private GmDistrictEgkoMapper mapper;

   @Autowired
   private GmDistrictConverter districtConvertder;

   public GmDistrictEgkoConverter() {
	  super(VisAuditType.VIS_GISMGT_DISTRICT_EGKO);
   }

   @Override
   protected IGmMapper<GmDistrictEgko> getMapper() {
	  return mapper;
   }

   @Override
   protected GmDistrictEgko convert(REGKODistrict gis) {
	  TEGKODistrict body = gis.getBody();
	  GmDistrictEgko domain = null;
	   if (body != null) {
		   domain = new GmDistrictEgko();
		   domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
		   domain.setMuid(getLongValueFromString(body.getMuid()));
		   domain.setIsDelete(getIntValue(body.getSignDeleted()));
		   domain.setVersion(body.getVersion().intValue());
		   domain.setName(getStringValue(body.getName()));
		   domain.setCaption(getStringValue(body.getCaption()));
		   domain.setWktGeom(getStringValue(body.getWktGeom()));
		   saveChildren(body, domain);
	   }
	   return domain;
   }

   @Override
   protected Boolean merge(GmDistrictEgko to, GmDistrictEgko from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  if (from.getCaption() != null && !from.getCaption().equals(to.getCaption())) {
		 result = true;
		 to.setCaption(from.getCaption());
	  }
	  if (from.getWktGeom() != null && !from.getWktGeom().equals(to.getWktGeom())) {
		 result = true;
		 to.setWktGeom(from.getWktGeom());
	  }
	  if (from.getDistrictMuid() != null && !from.getDistrictMuid().equals(to.getDistrictMuid())) {
		 result = true;
		 to.setDistrictMuid(from.getDistrictMuid());
	  }
	  return result;
   }

   @Override
   protected REGKODistrict toGis(ObjectMessage objectMessage) {
	  REGKODistrict domain = new REGKODistrict();
	  REGKODistrict.Header header = new REGKODistrict.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  domain.setBody(objectMessage.getBody().getTEGKODistrict());
	  domain.setHeader(header);
	  return domain;
   }

   @Override
   protected GmDistrictEgko fillMain(Long muid) {
	  GmDistrictEgko domain = new GmDistrictEgko();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   protected void saveChildren(TEGKODistrict gis, GmDistrictEgko domain) {
	  if (gis.getDistrict() != null) {
		 if (gis.getDistrict().getValue().getBody() != null) {
			districtConvertder.convertAndSaveToDB(gis.getDistrict().getValue()
				, gis.getDistrict().getValue().getHeader().getOperation().name());
		 } else {
			districtConvertder.saveId(getId(gis.getDistrict().getValue().getHeader().getIdentSet()));
		 }
		 domain.setDistrictMuid(getId(gis.getDistrict().getValue().getHeader().getIdentSet()));
	  }
   }
}
