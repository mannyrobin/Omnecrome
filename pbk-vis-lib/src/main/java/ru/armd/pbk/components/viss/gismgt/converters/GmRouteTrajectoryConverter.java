package ru.armd.pbk.components.viss.gismgt.converters;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.nsi.Route;
import ru.armd.pbk.domain.nsi.district.District;
import ru.armd.pbk.domain.nsi.district.DistrictRoute;
import ru.armd.pbk.domain.viss.gismgt.GmRoute;
import ru.armd.pbk.domain.viss.gismgt.GmRouteTrajectory;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.RRegion;
import ru.armd.pbk.gismgt.schema.RRouteTrajectories2StopPlaces;
import ru.armd.pbk.gismgt.schema.RRouteTrajectory;
import ru.armd.pbk.gismgt.schema.TRouteTrajectories2StopPlaces;
import ru.armd.pbk.gismgt.schema.TRouteTrajectory;
import ru.armd.pbk.mappers.viss.gismgt.GmRegionMapper;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteMapper;
import ru.armd.pbk.mappers.viss.gismgt.GmRouteTrajectoryMapper;
import ru.armd.pbk.mappers.viss.gismgt.IGmMapper;
import ru.armd.pbk.repositories.nsi.RouteRepository;
import ru.armd.pbk.repositories.nsi.district.DistrictRepository;
import ru.armd.pbk.repositories.nsi.district.DistrictRouteRepository;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * Конвертор из TRouteTrajectory в RouteTrajectory.
 */
@Component
public class GmRouteTrajectoryConverter
	extends AbstractGmConverter<RRouteTrajectory, GmRouteTrajectory> {

   public static final Logger LOGGER = Logger.getLogger(GmRouteTrajectoryConverter.class);

   private Map<Long, Route> routeCache = new HashMap<>();
   private Map<Long, District> districtCache = new HashMap<>();

   @Autowired
   private GmRouteTrajectoryMapper mapper;

   @Autowired
   private GmRouteTrajectories2StopPlacesConverter routeTrajectories2StopPlacesConverter;

   @Autowired
   private GmRegionMapper gmRegionMapper;

   @Autowired
   private DistrictRepository districtRepository;

   @Autowired
   private DistrictRouteRepository districtRouteRepository;

   @Autowired
   private RouteRepository routeRepository;

   @Autowired
   private GmRouteMapper gmRouteMapper;

   /**
	* Конструктор по умолчанию.
	*/
   public GmRouteTrajectoryConverter() {
	  super(VisAuditType.VIS_GISMGT_ROUTE_TRAJ_TYPE);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IGmMapper<GmRouteTrajectory> getMapper() {
	  return mapper;
   }

   @Override
   public void convertAndSaveToDB(RRouteTrajectory gis, String operation) {
	  super.convertAndSaveToDB(gis, operation);
	  saveRegions(gis.getBody());
   }

	@Override
	protected GmRouteTrajectory convert(RRouteTrajectory gis) {
		TRouteTrajectory body = gis.getBody();
		GmRouteTrajectory domain = new GmRouteTrajectory();
		domain.setMuid(getLongValueFromString(body.getMuid()));
		domain.setVersion(body.getVersion().intValue());
		domain.setIsDelete(getIntValue(body.getSignDeleted()));
		domain.setWktGeom(getStringValue(body.getWktGeom()));
		domain.setRouteRoundMuid(getId(body.getRouteRound().getValue().getHeader().getIdentSet()));
		try {
			domain.setRouteNullRoundMuid(getId(body.getRouteNullRound().getValue().getHeader().getIdentSet()));
		} catch (Exception e) {
			LOGGER.debug("Нет информации о поле.");
		}
		domain.setTrajectoryTypeMuid(getId(body.getTrajectoryType().getValue().getHeader().getIdentSet()));
		if (body.getStopPlaces() != null) {
			for (RRouteTrajectories2StopPlaces place : body.getStopPlaces().getLinkedStopPlaces()) {
				TRouteTrajectories2StopPlaces stopPlace = place.getBody();
				if (stopPlace.getRouteTrajectory() == null) {
					stopPlace.setRouteTrajectory(createRRouteTrajectory(gis));
				}
				routeTrajectories2StopPlacesConverter.convertAndSaveToDB(place
						, place.getHeader().getOperation().name());
			}
		}
		domain.setFixedLength(getFloatValue(body.getFixedLength()));
		domain.setSpecification(getStringValue(body.getSpecification()));
		domain.setLength(getFloatValue(body.getLength()));
		domain.setAsduId(getAsduId(gis.getHeader().getIdentSet()));
		return domain;
	}

   @Override
   protected Boolean merge(GmRouteTrajectory to, GmRouteTrajectory from) {
	  Boolean result = super.merge(to, from);
	  if (from.getWktGeom() != null && !from.getWktGeom().equals(to.getWktGeom())) {
		 result = true;
		 to.setWktGeom(from.getWktGeom());
	  }
	  if (from.getRouteRoundMuid() != null && !from.getRouteRoundMuid().equals(to.getRouteRoundMuid())) {
		 result = true;
		 to.setRouteRoundMuid(from.getRouteRoundMuid());
	  }
	  if (from.getRouteNullRoundMuid() != null && !from.getRouteNullRoundMuid().equals(to.getRouteNullRoundMuid())) {
		 result = true;
		 to.setRouteNullRoundMuid(from.getRouteNullRoundMuid());
	  }
	  if (from.getTrajectoryTypeMuid() != null && !from.getTrajectoryTypeMuid().equals(to.getTrajectoryTypeMuid())) {
		 result = true;
		 to.setTrajectoryTypeMuid(from.getTrajectoryTypeMuid());
	  }
	  if (from.getLength() != null && !from.getLength().equals(to.getLength())) {
		 result = true;
		 to.setLength(from.getLength());
	  }
	  if (from.getFixedLength() != null && !from.getFixedLength().equals(to.getFixedLength())) {
		 result = true;
		 to.setFixedLength(from.getFixedLength());
	  }
	  if (from.getSpecification() != null && !from.getSpecification().equals(to.getSpecification())) {
		 result = true;
		 to.setSpecification(from.getSpecification());
	  }
	  return result;
   }

   protected void saveRegions(TRouteTrajectory gis) {
	  if (gis.getRegions() != null) {
		 Map<String, Object> params = new HashMap<>();
		 for (RRegion region : gis.getRegions().getRegions()) {
			District district = getDistrict(getId(region.getHeader().getIdentSet()));
			if (district == null) {
			   continue;
			}
			Route route = getRoute(getLongValueFromString(gis.getMuid()));
			if (route == null) {
			   continue;
			}
			params.put("routeId", route.getHeadId());
			params.put("districtId", district.getHeadId());
			DistrictRoute districtRoute = districtRouteRepository.getDomain(params);
			if (districtRoute != null) {
			   continue;
			}
			districtRoute = new DistrictRoute();
			districtRoute.setRouteId(route.getHeadId());
			districtRoute.setDistrictId(district.getHeadId());
			districtRouteRepository.save(districtRoute);
		 }
	  }
   }

   protected JAXBElement<RRouteTrajectory> createRRouteTrajectory(RRouteTrajectory gis) {
	  RRouteTrajectory header = new RRouteTrajectory();
	  header.setHeader(gis.getHeader());
	   return new JAXBElement<>(new QName("http://mappl.ru/2015/XMLSchema/GISMGT", "routeTrajectory"),
			   RRouteTrajectory.class, TRouteTrajectories2StopPlaces.class, header);
   }

   @Override
   protected GmRouteTrajectory fillMain(Long muid) {
	  GmRouteTrajectory domain = new GmRouteTrajectory();
	  domain.setMuid(muid);
	  domain.setIsDelete(0);
	  domain.setVersion(0);
	  return domain;
   }

   @Override
   protected RRouteTrajectory toGis(ObjectMessage objectMessage) {
	  RRouteTrajectory gis = new RRouteTrajectory();
	  RRouteTrajectory.Header header = new RRouteTrajectory.Header();
	  header.setIdentSet(objectMessage.getHeader().getIdentSet());
	  header.setObjectType(objectMessage.getHeader().getObjectType());
	  header.setOperation(objectMessage.getHeader().getOperation());
	  gis.setBody(objectMessage.getBody().getTRouteTrajectory());
	  gis.setHeader(header);
	  return gis;
   }

	protected Route getRoute(Long key) {
		if (!routeCache.containsKey(key)) {
			Map<String, Object> params = new HashMap<>();
			GmRoute gmRoute = gmRouteMapper.getRouteByRouteTrajectoryMuid(key);
			if (gmRoute != null) {
				params.put("gmId", gmRoute.getId());
				Route route = routeRepository.getDomain(params);
				if (route != null) {
					routeCache.put(key, route);
					return route;
				} else {
					LOGGER.debug("ГИС МГТ маршрут с ID " + gmRoute.getId() + " не соотносится ни с одним ПБК маршрутом");
					return null;
				}
			} else {
				LOGGER.debug("Траектория с MUID " + key + " не соотносится ни с одним маршрутом");
			}
		}
		return routeCache.get(key);
	}

   protected District getDistrict(Long key) {
	  if (!districtCache.containsKey(key)) {
		 Map<String, Object> params = new HashMap<>();
		 params.put("gmId", gmRegionMapper.getByMuid(key).getId());
		 districtCache.put(key, districtRepository.getDomain(params));
	  }
	  return districtCache.get(key);
   }
}
