package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gismgt.GmParks2Routes;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RParks2Routes;
import ru.armd.pbk.gismgt.schema.TParks2Routes;
import ru.armd.pbk.mappers.viss.gismgt.GmParks2RoutesMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Конвертор из TParks2Routes в Parks2Routes.
 */
@Component
public class GmParks2RoutesConverter
	extends AbstractGmConverter<RParks2Routes, GmParks2Routes> {

   public static final Logger LOGGER = Logger.getLogger(GmParks2RoutesConverter.class);

   @Autowired
   private GmParks2RoutesMapper mapper;

   @Autowired
   private GmRouteConverter routeConverter;

   /**
	* Конструктор по умолчанию.
	*/
   public GmParks2RoutesConverter() {
	  super(VisAuditType.VIS_GISMGT_P2R);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmParks2Routes> getMapper() {
	  return mapper;
   }

   @Override
   protected GmParks2Routes convert(RParks2Routes gis) {
	  TParks2Routes body = gis.getBody();
	  int size = 0;
	  if (body.getRoutes() != null && body.getParks() != null) {
		 size = body.getRoutes().size();
	  }
	  for (int i = 0; i < size; i++) {
		 GmParks2Routes domain = new GmParks2Routes();
		 domain.setMuid(getLongValueFromString(body.getMuid()) + i);
		 domain.setIsDelete(getIntValue(body.getSignDeleted()));
		 domain.setVersion(body.getVersion().intValue());
		 saveChildren(body, domain, i);
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("parkMuid", domain.getParkMuid());
		 params.put("routeMuid", domain.getRouteMuid());
		 if (mapper.getDomain(params) == null) {
			save(domain, OPERATION_CREATE);
		 }
	  }
	  return null;
   }

   protected void saveChildren(TParks2Routes gis, GmParks2Routes domain, int i) {
	  if (gis.getRoutes().get(i) != null) {
		 if (gis.getRoutes().get(i).getBody() != null) {
			routeConverter.convertAndSaveToDB(gis.getRoutes().get(i)
				, gis.getRoutes().get(i).getHeader().getOperation().name());
		 }
		 domain.setRouteMuid(getId(gis.getRoutes().get(i).getHeader().getIdentSet()));
	  }
	  if (gis.getParks().get(0) != null) {
		 domain.setParkMuid(getLongValueFromString(gis.getParks().get(0).getBody().getMuid()));
	  }
   }

   @Override
   protected Boolean merge(GmParks2Routes to, GmParks2Routes from) {
	  Boolean result = super.merge(to, from);
	  if (from.getParkMuid() != null && !from.getParkMuid().equals(to.getParkMuid())) {
		 result = true;
		 to.setParkMuid(from.getParkMuid());
	  }
	  if (from.getRouteMuid() != null && !from.getRouteMuid().equals(to.getRouteMuid())) {
		 result = true;
		 to.setRouteMuid(from.getRouteMuid());
	  }
	  return result;
   }

   @Override
   protected GmParks2Routes fillMain(Long muid) {
	  GmParks2Routes domain = new GmParks2Routes();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RParks2Routes toGis(ObjectMessage objectMessage) {
	  return null;
   }
}
