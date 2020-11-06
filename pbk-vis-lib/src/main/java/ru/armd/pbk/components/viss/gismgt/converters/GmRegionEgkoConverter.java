package ru.armd.pbk.components.viss.gismgt.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmRegionEgko;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.REGKORegion;
import ru.armd.pbk.gismgt.schema.TEGKORegion;
import ru.armd.pbk.mappers.viss.gismgt.GmRegionEgkoMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

@Component
public class GmRegionEgkoConverter
	extends AbstractGmConverter<REGKORegion, GmRegionEgko> {

   @Autowired
   private GmRegionEgkoMapper mapper;

   @Autowired
   private GmRegionConverter regionConverter;

   public GmRegionEgkoConverter() {
	  super(VisAuditType.VIS_GISMGT_REGION_EGKO);
   }

   @Override
   protected IGmMapper<GmRegionEgko> getMapper() {
	  return mapper;
   }

   @Override
   protected GmRegionEgko convert(REGKORegion gis) {
	  TEGKORegion body = gis.getBody();
	  GmRegionEgko domain = new GmRegionEgko();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  domain.setCaption(getStringValue(body.getCaption()));
	  domain.setWktGeom(getStringValue(body.getWktGeom()));
	  saveChildren(body, domain);
	  return domain;
   }

   @Override
   protected Boolean merge(GmRegionEgko to, GmRegionEgko from) {
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
	  if (from.getRegionMuid() != null && !from.getRegionMuid().equals(to.getRegionMuid())) {
		 result = true;
		 to.setRegionMuid(from.getRegionMuid());
	  }
	  return result;
   }

   @Override
   protected REGKORegion toGis(ObjectMessage objectMessage) {
	  REGKORegion domain = new REGKORegion();
	  REGKORegion.Header header = new REGKORegion.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  domain.setBody(objectMessage.getBody().getTEGKORegion());
	  domain.setHeader(header);
	  return domain;
   }

   @Override
   protected GmRegionEgko fillMain(Long muid) {
	  GmRegionEgko domain = new GmRegionEgko();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   protected void saveChildren(TEGKORegion gis, GmRegionEgko domain) {
	  if (gis.getRegion() != null) {
		 if (gis.getRegion().getValue().getBody() != null) {
			regionConverter.convertAndSaveToDB(gis.getRegion().getValue()
				, gis.getRegion().getValue().getHeader().getOperation().name());
		 } else {
			regionConverter.saveId(getId(gis.getRegion().getValue().getHeader().getIdentSet()));
		 }
		 domain.setRegionMuid(getId(gis.getRegion().getValue().getHeader().getIdentSet()));
	  }
   }
}
