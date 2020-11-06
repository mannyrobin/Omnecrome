package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.nsi.Route;
import ru.armd.pbk.domain.nsi.RouteTsKind;
import ru.armd.pbk.domain.viss.gismgt.GmRoute;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRoute;
import ru.armd.pbk.gismgt.schema.TRoute;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteMapper;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteTransportKindMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;
import ru.armd.pbk.repositories.nsi.RouteRepository;
import ru.armd.pbk.repositories.nsi.RouteTsKindRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Конвертор из TRoute в Route.
 */
@Component
public class GmRouteConverter
	extends AbstractPbkGmConverter<RRoute, GmRoute, Route> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteConverter.class);

   @Autowired
   private GmRouteMapper mapper;

   @Autowired
   private RouteRepository repository;

   @Autowired
   private RouteTsKindRepository routeTsKindRepository;

   @Autowired
   private GmRouteTransportKindMapper gmRouteTransportKindMapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteConverter() {
	  super(VisAuditType.VIS_GISMGT_ROUTE);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRoute> getMapper() {
	  return mapper;
   }

	@Override
	protected GmRoute convert(RRoute gis) {
		TRoute body = gis.getBody();
		GmRoute domain = new GmRoute();
		domain.setMuid(getLongValueFromString(body.getMuid()));
		domain.setVersion(body.getVersion().intValue());
		domain.setIsDelete(getIntValue(body.getSignDeleted()));
		domain.setNumber(getStringValue(body.getNumber()));
		domain.setKindMuid(getId(body.getKind().getValue().getHeader().getIdentSet()));
		domain.setTransportKindMuid(getId(body.getTransportKind().getValue().getHeader().getIdentSet()));
		domain.setTransportationKindMuid(getId(body.getTransportationKind().getValue().getHeader().getIdentSet()));
		domain.setStateMuid(getId(body.getState().getValue().getHeader().getIdentSet()));
		domain.setState2Muid(getId(body.getState2().getValue().getHeader().getIdentSet()));
		try {
			domain.setCurrentRouteVariantMuid(getId(body.getCurrentRouteVariant().getValue().getHeader().getIdentSet()));
		} catch (Exception e) {
			LOGGER.debug("Нет информации о поле.");
		}
		domain.setAgencyMuid(getId(body.getAgency().getValue().getHeader().getIdentSet()));
		domain.setOpenDate(getDateValue(body.getOpenDate()));
		domain.setCloseDate(getDateValue(body.getCloseDate()));
		domain.setComment(getStringValue(body.getComment()));
		domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
		return domain;
	}

   @Override
   protected Boolean merge(GmRoute to, GmRoute from) {
	  Boolean result = super.merge(to, from);
	  if (from.getNumber() != null && !from.getNumber().equals(to.getNumber())) {
		 result = true;
		 to.setNumber(from.getNumber());
	  }
	  if (from.getKindMuid() != null && !from.getKindMuid().equals(to.getKindMuid())) {
		 result = true;
		 to.setKindMuid(from.getKindMuid());
	  }
	  if (from.getTransportKindMuid() != null && !from.getTransportKindMuid().equals(to.getTransportKindMuid())) {
		 result = true;
		 to.setTransportKindMuid(from.getTransportKindMuid());
	  }
	  if (from.getTransportationKindMuid() != null && !from.getTransportationKindMuid().equals(to.getTransportationKindMuid())) {
		 result = true;
		 to.setTransportationKindMuid(from.getTransportationKindMuid());
	  }
	  if (from.getStateMuid() != null && !from.getStateMuid().equals(to.getStateMuid())) {
		 result = true;
		 to.setStateMuid(from.getStateMuid());
	  }
	  if (from.getState2Muid() != null && !from.getState2Muid().equals(to.getState2Muid())) {
		 result = true;
		 to.setState2Muid(from.getState2Muid());
	  }
	  if (from.getCurrentRouteVariantMuid() != null && !from.getCurrentRouteVariantMuid().equals(to.getCurrentRouteVariantMuid())) {
		 result = true;
		 to.setCurrentRouteVariantMuid(from.getCurrentRouteVariantMuid());
	  }
	  if (from.getAgencyMuid() != null && !from.getAgencyMuid().equals(to.getAgencyMuid())) {
		 result = true;
		 to.setAgencyMuid(from.getAgencyMuid());
	  }
	  if (from.getOpenOrderMuid() != null && !from.getOpenOrderMuid().equals(to.getOpenOrderMuid())) {
		 result = true;
		 to.setOpenOrderMuid(from.getOpenOrderMuid());
	  }
	  if (from.getCloseOrderMuid() != null && !from.getCloseOrderMuid().equals(to.getCloseOrderMuid())) {
		 result = true;
		 to.setCloseOrderMuid(from.getCloseOrderMuid());
	  }
	  if (from.getComment() != null && !from.getComment().equals(to.getComment())) {
		 result = true;
		 to.setComment(from.getComment());
	  }
	  if (from.getOpenDate() != null && !from.getOpenDate().equals(to.getOpenDate())) {
		 result = true;
		 to.setOpenDate(from.getOpenDate());
	  }
	  //Если нам пришла дата закрытия маршрута null
	  if (from.getCloseDate() == null) {
		 //Если маршрут у нас закрыт, а у прешедшего маршрута номер не равен null
		 //то маршрут нужно переоткрыть
		 if (to.getCloseDate() != null && from.getNumber() != null && from.getMuid() != null) {
			result = true;
			to.setCloseDate(null);
		 }
	  } else {
		 if (!from.getCloseDate().equals(to.getCloseDate())) {
			result = true;
			to.setCloseDate(from.getCloseDate());
		 }
	  }
	  return result;
   }

   private int getNumber(String str) {
	  if (str != null && !str.isEmpty() && Character.isDigit(str.charAt(0))) {
		 StringBuilder number = new StringBuilder(Character.toString(str.charAt(0)));
		 for (int i = 1; i < str.length() && Character.isDigit(str.charAt(i)); i++) {
			number.append(str.charAt(i));
		 }
		 return Integer.parseInt(number.toString());
	  }
	  return 0;
   }

   @Override
   protected void pbkUpdate(Route pbkDomain, GmRoute domain) {
	  if (pbkDomain == null) {
		 pbkDomain = new Route();
	  }
	  fillPbkDomain(pbkDomain, domain);
	  repository.save(pbkDomain);
   }

   @Override
   protected void pbkInsert(Route pbkDomain, GmRoute domain) {
	  if (pbkDomain == null) {
		 pbkDomain = new Route();
		 pbkDomain.setAskpRouteCode(domain.getMuid().toString());
	  } else {
		 pbkDomain.setVersionStartDate(new Date());
	  }
	  fillPbkDomain(pbkDomain, domain);
	  repository.saveVersion(pbkDomain);
   }

   protected void fillPbkDomain(Route pbkDomain, GmRoute domain) {
	  pbkDomain.setRouteId(domain.getId());
	  pbkDomain.setRouteNumber(domain.getNumber());
	  pbkDomain.setRouteNumberInt(getNumber(domain.getNumber()));
	  Map<String, Object> params = new HashMap<>();
	  params.put("gmId", gmRouteTransportKindMapper.getByMuid(domain.getTransportKindMuid()).getId());
	  pbkDomain.setAsmppRouteCode(domain.getMuid().toString());
	  pbkDomain.setEasuFhdRouteCode(domain.getMuid().toString());
	   String asduId = domain.getAsduId();
	   if (asduId != null) {
		   pbkDomain.setAsduRouteId(asduId);
	   } else {
		   if (pbkDomain.getAsduRouteId() == null) {
			   pbkDomain.setAsduRouteId(domain.getId().toString());
		   }
	   }
	  RouteTsKind tsType = routeTsKindRepository.getDomain(params);
	  if (tsType != null) {
		 pbkDomain.setRouteTsKindId(tsType.getId());
	  }
   }

   @Override
   protected IDomainRepository<Route> getPbkRepository() {
	  return repository;
   }

   @Override
   protected GmRoute fillMain(Long muid) {
	  GmRoute domain = new GmRoute();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRoute toGis(ObjectMessage objectMessage) {
	  RRoute gis = new RRoute();
	  RRoute.Header header = new RRoute.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRoute());
	  gis.setHeader(header);
	  return gis;
   }

}
