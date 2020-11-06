package ru.armd.pbk.components.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gismgt.converters.AbstractGmConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmAgencyConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmDirectionConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmDistrictConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmParkConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmParks2AgenciesConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmParks2RoutesConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRegionConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteKindConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteNullRoundConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteNullRoundTypeConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteRoundConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteRoundTypeConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteStateConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteTrajectories2StopPlacesConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteTrajectoryConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteTrajectoryTypeConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteTransportKindConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteTransportationKindConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteVariantConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmRouteVariantTypeConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmStopConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmStopModeConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmStopPlaceConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmStopStateConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmTerminalStationConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmTransportKindConverter;
import ru.armd.pbk.components.viss.gismgt.converters.GmVehicleTypeConverter;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.gismgt.parser.GisMgtJaxbUtils;
import ru.armd.pbk.gismgt.schema.ObjectMessage;
import ru.armd.pbk.gismgt.schema.ObjectMessages;
import ru.armd.pbk.gismgt.schema.TObjectType;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Компонент для загрузки XML БД ГИС МГТ.
 */
@Component
public class GismgtLoader
	extends BaseComponent
	implements InitializingBean {

   public static final Logger LOGGER = Logger.getLogger(GismgtLoader.class);

   @Autowired
   private GmRouteNullRoundTypeConverter routeNullRoundTypeConverter;

   @Autowired
   private GmDirectionConverter movementDirectionConverter;

   @Autowired
   private GmRouteTransportKindConverter routeTransportKindConverter;

   @Autowired
   private GmRouteKindConverter routeKindConverter;

   @Autowired
   private GmRouteStateConverter routeStateConverter;

   @Autowired
   private GmRouteTransportationKindConverter routeTransportationKindConverter;

   @Autowired
   private GmRouteTrajectoryTypeConverter routeTrajectoryTypeConverter;

   @Autowired
   private GmRouteVariantTypeConverter routeVariantTypeConverter;

   @Autowired
   private GmRouteRoundTypeConverter routeRoundTypeConverter;

   @Autowired
   private GmDistrictConverter districtConverter;

   @Autowired
   private GmRegionConverter regionConverter;

   @Autowired
   private GmStopModeConverter stopModeConverter;

   @Autowired
   private GmStopStateConverter stopStateConverter;

   @Autowired
   private GmTransportKindConverter transportKindConverter;

   @Autowired
   private GmAgencyConverter agencyConverter;

   @Autowired
   private GmParkConverter parkConverter;

   @Autowired
   private GmParks2AgenciesConverter parks2AgenciesConverter;

   @Autowired
   private GmParks2RoutesConverter parks2RoutesConverter;

   @Autowired
   private GmRouteConverter routeConverter;

   @Autowired
   private GmRouteNullRoundConverter routeNullRoundConverter;

   @Autowired
   private GmRouteRoundConverter routeRoundConverter;

   @Autowired
   private GmRouteTrajectories2StopPlacesConverter routeTrajectories2StopPlacesConverter;

   @Autowired
   private GmRouteTrajectoryConverter routeTrajectoryConverter;

   @Autowired
   private GmRouteVariantConverter routeVariantConverter;

   @Autowired
   private GmStopConverter stopConverter;

   @Autowired
   private GmStopPlaceConverter stopPlaceConverter;

   @Autowired
   private GmTerminalStationConverter terminalStationConverter;

   @Autowired
   private GmVehicleTypeConverter vehicleTypeConverter;


   private Map<TObjectType, AbstractGmConverter> converters = new HashMap<TObjectType, AbstractGmConverter>();

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   protected void parseObject(Object objectMessages)
	   throws NoSuchMethodException, SecurityException,
	   IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	  if (objectMessages instanceof ObjectMessages) {
		 List<ObjectMessage> objects = ((ObjectMessages) objectMessages).getObjectMessages();
		 for (ObjectMessage object : objects) {
			save(object);
		 }
	  }
   }

   protected void save(ObjectMessage object) {
	  try {
		 converters.get(object.getHeader().getObjectType()).save(object);
	  } catch (NullPointerException e) {
		 LOGGER.error(object.getHeader().getObjectType() + " не поддерживаемый тип TObjectType", e);
	  }
   }

   /**
	* Загрузить из xml.
	*
	* @param is - входной поток файла xml.
	* @throws JAXBException
	* @throws NoSuchMethodException
	* @throws SecurityException
	* @throws IllegalAccessException
	* @throws IllegalArgumentException
	* @throws InvocationTargetException
	*/
   public void loadFromXml(InputStream is)
	   throws JAXBException, NoSuchMethodException, SecurityException,
	   IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	  parseObject(GisMgtJaxbUtils.getObject(is));
   }

   @Override
   public void afterPropertiesSet()
	   throws Exception {
	  converters.put(TObjectType.T_AGENCY, agencyConverter);
	  converters.put(TObjectType.T_ROUTE_NULL_ROUND_TYPE, routeNullRoundTypeConverter);
	  converters.put(TObjectType.T_ROUTE_TRANSPORT_KIND, routeTransportKindConverter);
	  converters.put(TObjectType.T_ROUTE_TRANSPORTATION_KIND, routeTransportationKindConverter);
	  converters.put(TObjectType.T_ROUTE_KIND, routeKindConverter);
	  converters.put(TObjectType.T_ROUTE_STATE, routeStateConverter);
	  converters.put(TObjectType.T_ROUTE_TRAJECTORY_TYPE, routeTrajectoryTypeConverter);
	  converters.put(TObjectType.T_ROUTE_VARIANT_TYPE, routeVariantTypeConverter);
	  converters.put(TObjectType.T_STOP_MODE, stopModeConverter);
	  converters.put(TObjectType.T_DISTRICT, districtConverter);
	  converters.put(TObjectType.T_STOP_STATE, stopStateConverter);
	  converters.put(TObjectType.T_REGION, regionConverter);
	  converters.put(TObjectType.T_TRANSPORT_KIND, transportKindConverter);
	  converters.put(TObjectType.T_MOVEMENT_DIRECTION, movementDirectionConverter);
	  converters.put(TObjectType.T_PARK, parkConverter);
	  converters.put(TObjectType.T_PARKS_2_AGENCIES, parks2AgenciesConverter);
	  converters.put(TObjectType.T_PARKS_2_ROUTES, parks2RoutesConverter);
	  converters.put(TObjectType.T_ROUTE, routeConverter);
	  converters.put(TObjectType.T_ROUTE_NULL_ROUND, routeNullRoundConverter);
	  converters.put(TObjectType.T_ROUTE_ROUND, routeRoundConverter);
	  converters.put(TObjectType.T_ROUTE_TRAJECTORIES_2_STOP_PLACES, routeTrajectories2StopPlacesConverter);
	  converters.put(TObjectType.T_ROUTE_TRAJECTORY, routeTrajectoryConverter);
	  converters.put(TObjectType.T_ROUTE_VARIANT, routeVariantConverter);
	  converters.put(TObjectType.T_STOP_PLACE, stopPlaceConverter);
	  converters.put(TObjectType.T_STOP, stopConverter);
	  converters.put(TObjectType.T_TERMINAL_STATION, terminalStationConverter);
	  converters.put(TObjectType.T_VEHICLE_TYPE, vehicleTypeConverter);
	  converters.put(TObjectType.T_ROUTE_ROUND_TYPE, routeRoundTypeConverter);
   }
}
