package ru.armd.pbk.components.viss.gkuop.employee;

import com.google.gson.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseJsonDomainLoader;
import ru.armd.pbk.domain.nsi.Gkuop;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.repositories.nsi.GkuopRepository;
import ru.armd.pbk.utils.ImportResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Загрузчик для сотрудников ГКУ ОП.
 */
@Component
public class GkuopEmployeeLoader
	extends BaseJsonDomainLoader<Gkuop> {

   private GkuopRepository repository;

   @Autowired
   GkuopEmployeeLoader(GkuopRepository domainRepository) {
	  super(domainRepository);
	  this.repository = domainRepository;
   }

	@Override
	protected JsonArray getDomainsArray(JsonObject object) {
		return object.getAsJsonArray();
	}

	@Override
	public ImportResult<Gkuop> importFile(InputStream is) {
		ImportResult<Gkuop> importResult = new ImportResult<Gkuop>();
		try {
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(IOUtils.toString(is, "UTF-8"));
			this.doProcessJsonObject(jsonElement.getAsJsonArray(), importResult);
		}
		catch (JsonSyntaxException | IOException e) {
			this.logAudit(AuditLevel.ERROR, this.getAuditType(), AuditObjOperation.OTHER, null, e.getMessage(), e);
		}
		return importResult;
	}

	protected void doProcessJsonObject(JsonArray array, ImportResult<Gkuop> importResult) {
		for (JsonElement element : array) {
			this.doProcessJsonElement(element, importResult);
			importResult.setAllCount(importResult.getAllCount() + 1);
		}
	}

	@Override
	protected Gkuop createDomain(JsonObject element) {
		Gkuop domain = new Gkuop();
		domain.setGkuopDeptName(this.getStringValue(element.get("podrName")));
		domain.setPersonalNumber(this.getStringValue(element.get("personalNumber")));
		domain.setPositionName(this.getStringValue(element.get("position")));
		domain.setVisGkuopId(this.getStringValue(element.get("id")));
		if (this.getStringValue(element.get("name")) != null) {
			String[] fio = this.getStringValue(element.get("name")).split(" ");
			if (fio.length == 3) {
				domain.setPatronumic(fio[2]);
			} else {
				domain.setPatronumic("null");
			}
			if (fio.length >= 2) {
				domain.setName(fio[1]);
			} else {
				domain.setName("null");
			}
			domain.setSurname(fio[0]);
		}
		return domain;
	}

	@Override
	protected Gkuop getExistedDomain(Gkuop newDomain) {
		return (Gkuop)this.repository.getDomain(this.getParams(newDomain));
	}

	protected Map<String, Object> getParams(Gkuop newDomain) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("gkuopId", newDomain.getVisGkuopId());
		return params;
	}

	@Override
	protected void updateDomain(Gkuop newDomain, Gkuop existedDomain) {
		super.updateDomain(newDomain, existedDomain);
		newDomain.setHeadId(existedDomain.getHeadId());
		newDomain.setVersionEndDate(existedDomain.getVersionEndDate());
		newDomain.setVersionStartDate(existedDomain.getVersionStartDate());
		newDomain.setForPlanUse(existedDomain.getForPlanUse());
	}

}
