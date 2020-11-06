package ru.armd.pbk.components.viss.dvr.dvr;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseJsonDomainLoader;
import ru.armd.pbk.components.viss.dvr.IDrvLoader;
import ru.armd.pbk.domain.nsi.Dvr;
import ru.armd.pbk.repositories.nsi.DvrRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Загрузчик видеорегистраторов.
 */
@Component
@Scope("prototype")
public class DvrLoader
	extends BaseJsonDomainLoader<Dvr>
	implements IDrvLoader {

   private DvrRepository domainRepository;

   @Autowired
   DvrLoader(DvrRepository domainRepository) {
	  super(domainRepository);
	  this.domainRepository = domainRepository;
   }

   @Override
   protected JsonArray getDomainsArray(JsonObject object) {
	  return object.getAsJsonArray("response");
   }

   @Override
   protected Dvr createDomain(JsonObject element) {
	  Dvr domain = new Dvr();
	  domain.setDvrNumber(getStringValue(element.get("number")));
	  return domain;
   }

   @Override
   protected Dvr getExistedDomain(Dvr newDomain) {
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("dvrNumber", newDomain.getDvrNumber());
	  return domainRepository.getDomain(params);
   }

}
