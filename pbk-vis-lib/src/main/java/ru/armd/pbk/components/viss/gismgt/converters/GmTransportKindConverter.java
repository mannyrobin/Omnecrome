package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.nsi.TsType;
import ru.armd.pbk.domain.viss.gismgt.GmTransportKind;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RTransportKind;
import ru.armd.pbk.gismgt.schema.TTransportKind;
import ru.armd.pbk.mappers.viss.gismgt.GmTransportKindMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;
import ru.armd.pbk.repositories.nsi.TsTypeRepository;

/**
 * Конвертор из TTransportKind в TransportKind.
 */
@Component
public class GmTransportKindConverter
	extends AbstractPbkGmConverter<RTransportKind, GmTransportKind, TsType> {

   public static final Logger LOGGER = Logger.getLogger(GmTransportKindConverter.class);

   @Autowired
   private GmTransportKindMapper mapper;

   @Autowired
   private TsTypeRepository repository;

   /**
	* Конструктор по умолчанию.
	*/
   public GmTransportKindConverter() {
	  super(VisAuditType.VIS_GISMGT_TS_KIND);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmTransportKind> getMapper() {
	  return mapper;
   }

   @Override
   protected GmTransportKind convert(RTransportKind gis) {
	  TTransportKind body = gis.getBody();
	  GmTransportKind domain = new GmTransportKind();
	  domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
	  domain.setMuid(getLongValueFromString(body.getMuid()));
	  domain.setIsDelete(getIntValue(body.getSignDeleted()));
	  domain.setVersion(body.getVersion().intValue());
	  domain.setName(getStringValue(body.getName()));
	  return domain;
   }

   @Override
   protected Boolean merge(GmTransportKind to, GmTransportKind from) {
	  Boolean result = super.merge(to, from);
	  if (from.getName() != null && !from.getName().equals(to.getName())) {
		 result = true;
		 to.setName(from.getName());
	  }
	  return result;
   }

   @Override
   protected void pbkInsert(TsType pbkDomain, GmTransportKind domain) {
	  if (pbkDomain == null) {
		 pbkDomain = new TsType();
	  }
	  pbkDomain.setName(domain.getName());
	  pbkDomain.setTsKindId(domain.getId());
	  pbkDomain.setCod(domain.getMuid().toString());
	  repository.save(pbkDomain);
   }

   @Override
   protected void pbkUpdate(TsType pbkDomain, GmTransportKind domain) {
	  pbkInsert(pbkDomain, domain);
   }

   @Override
   protected IDomainRepository<TsType> getPbkRepository() {
	  return repository;
   }

   @Override
   protected GmTransportKind fillMain(Long muid) {
	  GmTransportKind domain = new GmTransportKind();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RTransportKind toGis(ObjectMessage objectMessage) {
	  RTransportKind gis = new RTransportKind();
	  RTransportKind.Header header = new RTransportKind.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTTransportKind());
	  gis.setHeader(header);
	  return gis;
   }

}
