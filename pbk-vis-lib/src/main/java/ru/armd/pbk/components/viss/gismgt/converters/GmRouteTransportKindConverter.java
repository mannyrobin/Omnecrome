package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.nsi.RouteTsKind;
import ru.armd.pbk.domain.viss.gismgt.GmRouteTransportKind;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRouteTransportKind;
import ru.armd.pbk.gismgt.schema.RTransportKind;
import ru.armd.pbk.gismgt.schema.TObjectType;
import ru.armd.pbk.gismgt.schema.TRouteTransportKind;
import ru.armd.pbk.gismgt.schema.TTransportKind;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteTransportKindMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;
import ru.armd.pbk.repositories.nsi.RouteTsKindRepository;

/**
 * Конвертор из TRouteTransportKind в RouteTransportKind.
 */
@Component
public class GmRouteTransportKindConverter
	extends AbstractPbkGmConverter<RRouteTransportKind, GmRouteTransportKind, RouteTsKind> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteTransportKindConverter.class);

   @Autowired
   private GmRouteTransportKindMapper mapper;

   @Autowired
   private RouteTsKindRepository repository;

   @Autowired
   private GmTransportKindConverter transportKindConverter;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteTransportKindConverter() {
	  super(VisAuditType.VIS_GISMGT_ROUTE_TS_KIND);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRouteTransportKind> getMapper() {
	  return mapper;
   }

   @Override
   protected GmRouteTransportKind convert(RRouteTransportKind gis) {
	  TRouteTransportKind body = gis.getBody();
	  GmRouteTransportKind domain = new GmRouteTransportKind();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setShortName(getStringValue(body.getShortName()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  transportKindConverter.convertAndSaveToDB(createTransportKind(gis), OPERATION_UPDATE);
	  return domain;
   }

   @Override
   protected Boolean merge(GmRouteTransportKind to, GmRouteTransportKind from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  if (from.getShortName() != null && !from.getShortName().equals(to.getShortName())) {
		 result = true;
		 to.setShortName(from.getShortName());
	  }
	  return result;
   }


   @Override
   protected void pbkUpdate(RouteTsKind pbkDomain, GmRouteTransportKind domain) {
	  pbkInsert(pbkDomain, domain);
   }


   @Override
   protected void pbkInsert(RouteTsKind pbkDomain, GmRouteTransportKind domain) {
	  if (pbkDomain == null) {
		 pbkDomain = new RouteTsKind();
	  }
	  pbkDomain.setName(domain.getName());
	  pbkDomain.setRouteTsKindId(domain.getId());
	  pbkDomain.setCod(domain.getMuid().toString());
	  repository.save(pbkDomain);
   }

   @Override
   protected IDomainRepository<RouteTsKind> getPbkRepository() {
	  return repository;
   }

   protected RTransportKind createTransportKind(RRouteTransportKind gis) {
	  TRouteTransportKind body = gis.getBody();
	  RTransportKind result = new RTransportKind();
	  RTransportKind.Header header = new RTransportKind.Header();
	  header.setIdentSet(gis.getHeader().getIdentSet());
	  header.setObjectType(TObjectType.T_TRANSPORT_KIND);
	  header.setOperation(gis.getHeader().getOperation());
	  result.setHeader(header);
	  TTransportKind tsKind = new TTransportKind();
	  tsKind.setMuid(body.getMuid());
	  tsKind.setName(body.getName());
	  tsKind.setSignDeleted(body.getSignDeleted());
	  tsKind.setVersion(body.getVersion());
	  result.setBody(tsKind);
	  return result;
   }

   @Override
   protected GmRouteTransportKind fillMain(Long muid) {
	  GmRouteTransportKind domain = new GmRouteTransportKind();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRouteTransportKind toGis(ObjectMessage objectMessage) {
	  RRouteTransportKind gis = new RRouteTransportKind();
	  RRouteTransportKind.Header header = new RRouteTransportKind.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRouteTransportKind());
	  gis.setHeader(header);
	  return gis;
   }
}
