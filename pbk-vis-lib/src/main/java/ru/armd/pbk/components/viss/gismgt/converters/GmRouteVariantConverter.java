package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmRouteVariant;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRouteVariant;
import ru.armd.pbk.gismgt.schema.TRouteVariant;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteVariantMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

/**
 * Конвертор из TRouteVariant в RouteVariant.
 */
@Component
public class GmRouteVariantConverter
	extends AbstractGmConverter<RRouteVariant, GmRouteVariant> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteVariantConverter.class);

   @Autowired
   private GmRouteVariantMapper mapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteVariantConverter() {
	  super(VisAuditType.VIS_GISMGT_ROUTE_VAR);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRouteVariant> getMapper() {
	  return mapper;
   }

	@Override
	protected GmRouteVariant convert(RRouteVariant gis) {
		TRouteVariant body = gis.getBody();
		GmRouteVariant domain = new GmRouteVariant();
		domain.setMuid(getLongValueFromString(body.getMuid()));
		domain.setVersion(body.getVersion().intValue());
		domain.setIsDelete(getIntValue(body.getSignDeleted()));
		domain.setRouteMuid(getId(body.getRoute().getValue().getHeader().getIdentSet()));
		domain.setStartDate(getDateValue(body.getStartDate()));
		domain.setEndDate(getDateValue(body.getEndDate()));
		domain.setTypeMuid(getId(body.getType().getValue().getHeader().getIdentSet()));
		domain.setComment(getStringValue(body.getComment()));
		domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
		return domain;
	}

   @Override
   protected Boolean merge(GmRouteVariant to, GmRouteVariant from) {
	  Boolean result = super.merge(to, from);
	  if (from.getRouteMuid() != null && !from.getRouteMuid().equals(to.getRouteMuid())) {
		 result = true;
		 to.setRouteMuid(from.getRouteMuid());
	  }
	  if (from.getTypeMuid() != null && !from.getTypeMuid().equals(to.getTypeMuid())) {
		 result = true;
		 to.setTypeMuid(from.getTypeMuid());
	  }
	  if (from.getOrderMuid() != null && !from.getOrderMuid().equals(to.getOrderMuid())) {
		 result = true;
		 to.setOrderMuid(from.getOrderMuid());
	  }
	  if (from.getStartDate() != null && !from.getStartDate().equals(to.getStartDate())) {
		 result = true;
		 to.setStartDate(from.getStartDate());
	  }
	  if (from.getEndDate() != null && !from.getEndDate().equals(to.getEndDate())) {
		 result = true;
		 to.setEndDate(from.getEndDate());
	  }
	  if (from.getComment() != null && !from.getComment().equals(to.getComment())) {
		 result = true;
		 to.setComment(from.getComment());
	  }
	  return result;
   }

   @Override
   protected GmRouteVariant fillMain(Long muid) {
	  GmRouteVariant domain = new GmRouteVariant();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRouteVariant toGis(ObjectMessage objectMessage) {
	  RRouteVariant gis = new RRouteVariant();
	  RRouteVariant.Header header = new RRouteVariant.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRouteVariant());
	  gis.setHeader(header);
	  return gis;
   }
}
