/*
 * Decompiled with CFR 0.139.
 * 
 * Could not load the following classes:
 *  com.sap.document.sap.rfc.functions.ZPMSINTEGRPBK
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Component
 *  ru.armd.pbk.core.domain.BaseDomain
 *  ru.armd.pbk.core.repositories.IDomainRepository
 *  ru.armd.pbk.domain.nsi.TsCapacity
 *  ru.armd.pbk.domain.nsi.TsModel
 *  ru.armd.pbk.domain.nsi.TsType
 *  ru.armd.pbk.domain.nsi.Venicle
 *  ru.armd.pbk.domain.nsi.VenicleWorkDay
 *  ru.armd.pbk.repositories.nsi.TsCapacityRepository
 *  ru.armd.pbk.repositories.nsi.TsModelRepository
 *  ru.armd.pbk.repositories.nsi.TsTypeRepository
 *  ru.armd.pbk.repositories.nsi.VenicleRepository
 *  ru.armd.pbk.repositories.nsi.VenicleWorkDayRepository
 */
package ru.armd.pbk.components.viss.easu.converters;

import com.sap.document.sap.rfc.functions.ZPMSINTEGRPBK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.core.repositories.IDomainRepository;
import ru.armd.pbk.domain.nsi.*;
import ru.armd.pbk.repositories.nsi.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class EasuTsVeniclesConverter
extends AbstractEasuConverter<ZPMSINTEGRPBK, Venicle> {
    private static final String NULL_DATE = "0000-00-00";
    @Autowired
    private VenicleRepository repository;
    @Autowired
    private TsModelRepository modelRepository;
    @Autowired
    private TsTypeRepository tsTypeRepository;
    @Autowired
    private TsCapacityRepository capacityRepository;
    @Autowired
    private VenicleWorkDayRepository venicleWorkDayRepository;

    @Override
    public Venicle save(ZPMSINTEGRPBK easu) {
        Venicle domain = (Venicle)super.save(easu);
        if (!easu.getDATABEGIN().equals(NULL_DATE) || !easu.getDATAEND().equals(NULL_DATE)) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date startDate = dateFormat.parse(easu.getDATABEGIN());
                Date endDate = dateFormat.parse(easu.getDATAEND());
                Calendar start = Calendar.getInstance();
                start.setTime(startDate);
                Calendar end = Calendar.getInstance();
                end.setTime(endDate);
                Date workDate = start.getTime();
                while (start.before(end)) {
                    if (this.venicleWorkDayRepository.getDomain(this.getWorkDayParams(domain.getHeadId(), workDate)) == null) {
                        VenicleWorkDay venicleWorkDay = new VenicleWorkDay();
                        venicleWorkDay.setWorkDay(workDate);
                        venicleWorkDay.setVenicleId(domain.getHeadId());
                        this.venicleWorkDayRepository.save(venicleWorkDay);
                    }
                    start.add(5, 1);
                    workDate = start.getTime();
                }
            }
            catch (ParseException dateFormat) {
                // empty catch block
            }
        }
        return domain;
    }

    @Override
    protected Venicle convert(ZPMSINTEGRPBK easu) {
        Venicle domain = new Venicle();
        TsModel model = (TsModel)this.modelRepository.getDomain(this.getModelParams(easu));
        if (model == null) {
            model = new TsModel();
            model.setMake(easu.getPARENTCLASS());
            model.setPassengerCountMax(Integer.valueOf(easu.getMAXOCCUP()));
            model.setModel(easu.getCLASS());
            TsCapacity capasity = (TsCapacity)this.capacityRepository.getDomain(this.getCapacityParams(easu));
            if (capasity == null) {
                capasity = new TsCapacity();
                capasity.setName(easu.getVMEST());
                capasity.setTsTypeId(((TsType)this.tsTypeRepository.getDomain(this.getTsTypeParams(easu))).getId());
                capasity.setCod(easu.getVIDTC() + " " + easu.getVMEST());
                capasity = (TsCapacity)this.capacityRepository.save(capasity);
            }
            model.setTsCapacityId(capasity.getId());
            model = (TsModel)this.modelRepository.save(model);
        }
        domain.setDepoNumber(easu.getEQFNR());
        domain.setTsModelId(model.getId());
        domain.setAsduVenicleId(easu.getEQFNR());
        return domain;
    }

    @Override
    protected Boolean merge(Venicle from, Venicle to) {
        Boolean result = false;
        if (!from.getDepoNumber().equals(to.getDepoNumber())) {
            result = true;
            to.setDepoNumber(from.getDepoNumber());
        }
        if (!from.getTsModelId().equals(to.getTsModelId())) {
            result = true;
            to.setTsModelId(from.getTsModelId());
        }
        return result;
    }

    @Override
    protected IDomainRepository<Venicle> getRepository() {
        return this.repository;
    }

    @Override
    protected Map<String, Object> getParams(ZPMSINTEGRPBK easu) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("depoNumber", easu.getEQFNR());
        return params;
    }

    protected Map<String, Object> getModelParams(ZPMSINTEGRPBK easu) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("model", easu.getCLASS());
        return params;
    }

    protected Map<String, Object> getTsTypeParams(ZPMSINTEGRPBK easu) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("name", easu.getVIDTC());
        return params;
    }

    protected Map<String, Object> getCapacityParams(ZPMSINTEGRPBK easu) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("name", easu.getVMEST());
        params.put("tsTypeId", ((TsType)this.tsTypeRepository.getDomain(this.getTsTypeParams(easu))).getId());
        return params;
    }

    protected Map<String, Object> getWorkDayParams(Long venicleId, Date workDate) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("venicleId", venicleId);
        params.put("workDate", workDate);
        return params;
    }
}

