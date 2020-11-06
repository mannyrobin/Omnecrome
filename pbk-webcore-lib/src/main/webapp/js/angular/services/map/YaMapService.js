angular.module('armada').service("YaMapService", ['AppConfig', function (AppConfig) {

	var routeIds = [];

	return {
		init: function (mapParams, deffered) {
			var centerCoords = angular.isDefined(mapParams.centre) ? mapParams.centre : [AppConfig.DEFAULT_MAP_LOCATION.lng, AppConfig.DEFAULT_MAP_LOCATION.lat];
			var defaultMapZoom = angular.isDefined(mapParams.zoom) ? mapParams.zoom : AppConfig.DEFAULT_MAP_ZOOM;
			ymaps.ready(function () {
				var map = new ymaps.Map(mapParams.elementId, {
					center: centerCoords,
					controls: ["rulerControl", "searchControl", "geolocationControl", "zoomControl", "fullscreenControl"],
					zoom: defaultMapZoom
				});

				deffered.resolve(map);
			});
			return deffered.promise;
		},
		addOnClickFunc: function (map, onClickFunc) {
			map.events.add('click', function (e) {
				var coords = e.get('coords');
				onClickFunc(coords[0], coords[1]);
			});
		},
		addPoint: function (map, pointObj, color) {
			if (angular.isDefined(pointObj)) {
				if (pointObj.geoJsonPoint == null) {
					map.geoObjects.add(pointObj);
					return pointObj;
				} else {
					var point = new ymaps.Placemark(pointObj.geoJsonPoint.coordinates);
					point.properties.set('hintContent', pointObj.type == 'stop' ? "Остановка: " + pointObj.nameWithoutDistrict + "<br/>Проходящие маршруты: " + pointObj.routeNames : pointObj.name);
					point.properties.set('balloonContent', pointObj.type == 'stop' ? "Остановка: " + pointObj.nameWithoutDistrict + "<br/>Проходящие маршруты: " + pointObj.routeNames : pointObj.name);
					point.options.set('iconColor', color);
					if (angular.isDefined(pointObj.preset)) {
						point.properties.set('iconContent', pointObj.name);
						point.options.set('preset', pointObj.preset);
					}
					map.geoObjects.add(point);
					map.setCenter(point.geometry.getCoordinates());
					return point;
				}
			}
		},
		changePointLocation: function (map, pointObj, lng, lat) {
			pointObj.geometry.setCoordinates([lng, lat]);
		},
		addPoints: function (map, pointsArray) {
			if (angular.isDefined(pointsArray)) {
				var pointsCollection = new ymaps.GeoObjectCollection({}, {});
				var points = [];
				for (var i = 0; i < pointsArray.length; i++) {
					var point = new ymaps.Placemark(pointsArray[i].geoJsonPoint.coordinates);
					point.properties.set('hintContent', pointsArray[i].name);
					point.properties.set('balloonContent', pointsArray[i].name);
					points[i] = point;
					pointsCollection.add(point);
				}
				var pointsClusterer = new ymaps.Clusterer();
				pointsClusterer.add(points);
				map.setBounds(pointsClusterer.getBounds(), {
					checkZoomRange: true
				});
				map.geoObjects.add(pointsClusterer);
				return pointsCollection;
			}
		},

		removeAll: function (map) {
			map.geoObjects.removeAll();
			routeIds = [];
		},

		addPolygon: function (map, polygons, color) {
			var p = new ymaps.GeoObjectCollection({}, {
				fillColor: color,
				opacity: 0.5,
				geodesic: true
			});
			for (var i = 0; i < polygons.length; i++) {
				p.add(new ymaps.Polygon(polygons[i]));
			}
			map.geoObjects.add(p);
			map.setBounds(p.getBounds());
			return p;
		},

		addRouteLine: function (map, trajectories, stops, isBound, color) {
			var routeCollection = new ymaps.GeoObjectCollection({}, {});
			var routeStartEndPoint = [];
			var startEndClusterer = new ymaps.Clusterer();
			startEndClusterer.id = trajectories.id;
			routeIds.push(trajectories.id);
			if (stops != null) {
				var routeStops = [];

				var routeCollection = new ymaps.GeoObjectCollection({}, {});

				if (stops.length > 0) {
					for (var i = 0; i < stops.length; i++) {
						var routeStop = new ymaps.Placemark(stops[i].geoJsonPoint.coordinates);
						routeStop.options.set('iconColor', AppConfig.MAP_COLORS.STOP_IN_ROUTE);
						routeStop.properties.set('hintContent', 'Остановка: ' + stops[i].nameWithoutDistrict + "<br/>Проходящие маршруты: " + stops[i].routeNames);
						routeStop.properties.set('balloonContent', 'Остановка: ' + stops[i].nameWithoutDistrict + "<br/>Проходящие маршруты: " + stops[i].routeNames);
						routeStops[i] = routeStop;
					}
				}

				var stopsClusterer = new ymaps.Clusterer();
				stopsClusterer.add(routeStops);

				var zoom = map.getZoom();
				if (map.getZoom() >= 15 && stops.length > 0) {
					map.geoObjects.add(stopsClusterer);
				} else {
					map.geoObjects.add(startEndClusterer);
				}
			}

			map.events.add('boundschange', function (e) {
				var newZoom = e.get('newZoom');
				var oldZoom = e.get('oldZoom');
				if (newZoom >= 15 && oldZoom < 15 && routeIds.indexOf(startEndClusterer.id) >= 0) {
					if (angular.isDefined(stopsClusterer)) {
						map.geoObjects.add(stopsClusterer);
					}
					map.geoObjects.remove(startEndClusterer);
				}
				if (newZoom < 15 && oldZoom >= 15 && routeIds.indexOf(startEndClusterer.id) >= 0) {
					if (angular.isDefined(stopsClusterer)) {
						map.geoObjects.remove(stopsClusterer);
					}
					map.geoObjects.add(startEndClusterer);
				}
			});

			var start = new ymaps.Placemark(trajectories.geoJsonLine.coordinates[0]);

			start.properties.set('hintContent', 'Начало');
			start.properties.set('balloonContent', 'Начало');
			start.properties.set('iconContent', trajectories.routeNumber + " (" + trajectories.routeType + ")");
			start.options.set('preset', 'islands#blueStretchyIcon');
			routeStartEndPoint.push(start);
			var end = new ymaps.Placemark(trajectories.geoJsonLine.coordinates[trajectories.geoJsonLine.coordinates.length - 1]);

			end.properties.set('hintContent', 'Конец');
			end.properties.set('balloonContent', 'Конец');
			end.properties.set('iconContent', trajectories.routeNumber + " (" + trajectories.routeType + ")");
			end.options.set('preset', 'islands#blueStretchyIcon');
			routeStartEndPoint.push(end);

			startEndClusterer.add(routeStartEndPoint);
			map.geoObjects.add(startEndClusterer);

			var routePolyline = new ymaps.Polyline(
				trajectories.geoJsonLine.coordinates, {}, {strokeWidth: 5}
			);

			routeCollection.add(routePolyline);
			routePolyline.options.set('strokeColor', color);
			routePolyline.properties.set('hintContent', 'Маршрут №' + trajectories.routeNumber + ' (' + trajectories.trajectoryTypeName + ')');
			routePolyline.properties.set('balloonContent', 'Маршрут №' + trajectories.routeNumber + ' (' + trajectories.trajectoryTypeName + ')');
			map.geoObjects.add(routePolyline);
			if (!angular.isDefined(isBound) || isBound == true) {
				map.setBounds(startEndClusterer.getBounds(), {
					checkZoomRange: true
				});
			}
			return routeCollection;
		},

		remove: function (map, object) {
			map.geoObjects.remove(object);
		},

		add: function (map, object) {
			map.geoObjects.add(object);
		},

		setCenterBounds: function (map) {
			map.setBounds(map.geoObjects.getBounds());
		}
	}
}]);