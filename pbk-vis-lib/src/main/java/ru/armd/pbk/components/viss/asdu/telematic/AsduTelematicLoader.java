/*
 * Decompiled with CFR 0.143.
 * 
 * Could not load the following classes:
 *  org.apache.log4j.Logger
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.context.annotation.Scope
 *  org.springframework.stereotype.Component
 *  ru.armd.pbk.core.domain.BaseDomain
 *  ru.armd.pbk.core.repositories.IDomainRepository
 *  ru.armd.pbk.domain.nsi.Telematics
 *  ru.armd.pbk.enums.core.AuditType
 *  ru.armd.pbk.enums.core.VisAuditType
 *  ru.armd.pbk.repositories.nsi.TelematicsRepository
 */
package ru.armd.pbk.components.viss.asdu.telematic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvDomainLoader;
import ru.armd.pbk.domain.nsi.Telematics;
import ru.armd.pbk.enums.core.AuditType;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.repositories.nsi.TelematicsRepository;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;
import java.util.*;

@Component
@Scope(value="prototype")
public class AsduTelematicLoader
extends BaseCsvDomainLoader<Telematics> {
    private static final Logger LOGGER = Logger.getLogger(AsduTelematicLoader.class);
    private HashMap<TelematicKey, TreeSet<Long>> data;
    private TelematicsRepository telematicsRepository;

    @Autowired
    AsduTelematicLoader(TelematicsRepository telematicsRepository) {
        super(telematicsRepository);
        this.telematicsRepository = telematicsRepository;
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    public AuditType getAuditType() {
        return VisAuditType.VIS_ASDU_TELEMATIC;
    }

    @Override
    protected Telematics createDomain(String[] fields) {
        Telematics telematics = new Telematics();
        telematics.setPointTime(new Date(this.getLongValue(fields[0]) * 1000L));
        telematics.setPointLongitude(this.getDoubleValue(fields[2]));
        telematics.setPointLatitude(this.getDoubleValue(fields[3]));
        return telematics;
    }

    @Override
    protected Telematics getExistedDomain(Telematics newDomain) {
        return null;
    }

    public ImportResult<Telematics> importFile(InputStream is, Date workDate) {
        this.data = new HashMap();
        ImportResult<Telematics> importResult = super.importFile(is);
        for (TreeSet<Long> set : this.data.values()) {
            Long first = set.first();
            TreeSet toremove = new TreeSet();
            for (Long value : set) {
                long diff = value - first;
                if (diff < 45L) {
                    if (diff > 0L) {
                        toremove.add(value);
                    }
                } else {
                    toremove.remove(first);
                }
                first = value;
            }
            toremove.remove(set.last());
            set.removeAll(toremove);
        }
        LinkedList<Telematics> tels = new LinkedList<Telematics>();
        for (TelematicKey key : this.data.keySet()) {
            for (Long value : this.data.get(key)) {
                Telematics telematics = new Telematics();
                telematics.setEquipmentId(Long.valueOf(key.equipmentId));
                telematics.setPointLongitude(Double.valueOf(key.lon));
                telematics.setPointLatitude(Double.valueOf(key.lat));
                telematics.setPointTime(new Date(value * 1000L));
                telematics.setWorkDate(workDate);
                tels.add(telematics);
            }
            if (tels.size() <= 200) continue;
            this.telematicsRepository.insertBulk(tels, workDate);
            tels.clear();
        }
        this.telematicsRepository.insertBulk(tels, workDate);
        tels.clear();
        this.data = null;
        return importResult;
    }

    @Override
    protected void doProcessFields(String[] fields) {
        TelematicKey key = new TelematicKey(this.getLongValue(fields[1]), this.getDoubleValue(fields[2]), this.getDoubleValue(fields[3]));
        if (!this.data.containsKey(key)) {
            this.data.put(key, new TreeSet());
        }
        Set set = this.data.get(key);
        set.add(this.getLongValue(fields[0]));
    }

    class TelematicKey {
        public long equipmentId;
        public double lon;
        public double lat;

        public TelematicKey(long equipmentId, double lon, double lat) {
            this.equipmentId = equipmentId;
            this.lat = lat;
            this.lon = lon;
        }

        public int hashCode() {
            int prime = 31;
            int result = 1;
            result = 31 * result + ((Object)((Object)this.getOuterType())).hashCode();
            result = 31 * result + (int)(this.equipmentId ^ this.equipmentId >>> 32);
            long temp = Double.doubleToLongBits(this.lat);
            result = 31 * result + (int)(temp ^ temp >>> 32);
            temp = Double.doubleToLongBits(this.lon);
            result = 31 * result + (int)(temp ^ temp >>> 32);
            return result;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            TelematicKey other = (TelematicKey)obj;
            if (!((Object)((Object)this.getOuterType())).equals((Object)other.getOuterType())) {
                return false;
            }
            if (this.equipmentId != other.equipmentId) {
                return false;
            }
            if (Double.doubleToLongBits(this.lat) != Double.doubleToLongBits(other.lat)) {
                return false;
            }
            return Double.doubleToLongBits(this.lon) == Double.doubleToLongBits(other.lon);
        }

        private AsduTelematicLoader getOuterType() {
            return AsduTelematicLoader.this;
        }
    }

}

