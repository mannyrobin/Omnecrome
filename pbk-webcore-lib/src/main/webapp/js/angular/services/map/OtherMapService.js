angular.module('armada').service("OtherMapService", ['AppConfig', 'UtilService', '$location', function (AppConfig, UtilService, $location) {

	var pointsStore = {
		"type": "FeatureCollection",
		"features": {}
	};
	var pointsStartEndRoute = {
		"type": "FeatureCollection",
		"features": []
	};
	var pointsRoute = {
		"type": "FeatureCollection",
		"features": []
	};
	var routesStore = {
		"type": "FeatureCollection",
		"features": []
	};
	var polygonsStrore = {
		"type": "FeatureCollection",
		"features": []
	};

	var layers = [];

	var popup = new mapboxgl.Popup({
		closeButton: false,
		closeOnClick: false
	});

	var onClickMap = null;

	function addRouteStartEndPoint(map, id, routeNumber, routeType, startPoint, endPoint) {
		pointsStartEndRoute.features['pointsStartEndRoute_' + id] = [];
		if (startPoint != null) {
			var pointStart = {
				'id': 'pointStartRoute_' + id,
				'type': 'Feature',
				"properties": {
					"description": "<p>Маршрут " + routeNumber + "</p>" + "<p>Начало</p>",
					"title": routeNumber + " (" + routeType + ")",
					"icon": AppConfig.MAP_ICONS.STOP_IN_ROUTE
				},
				'geometry': {
					'type': 'Point',
					'coordinates': startPoint
				}
			};
			pointsStartEndRoute.features.push(pointStart);
		}
		if (endPoint != null) {
			var pointEnd = {
				'id': 'pointEndRoute_' + id,
				'type': 'Feature',
				"properties": {
					"description": "<p>Маршрут " + routeNumber + "</p>" + "</p>" + "<p>Конец</p>",
					"title": routeNumber + " (" + routeType + ")",
					"icon": AppConfig.MAP_ICONS.STOP_IN_ROUTE
				},
				'geometry': {
					'type': 'Point',
					'coordinates': endPoint
				}
			};
			pointsStartEndRoute.features.push(pointEnd);
		}
		if (map.getSource('pointsStartEndRoute') == null) {
			map.addSource('pointsStartEndRoute', {
				'type': 'geojson',
				'data': []
			});

			var layer = {
				'id': 'pointsStartEndRoute',
				'source': 'pointsStartEndRoute',
				'type': "symbol",
				"layout": {
					"icon-image": "{icon}",
					"text-field": "{title}",
					"text-font": ["Open Sans Bold"],
					"text-size": 10,
					"text-offset": [0, 0.6],
					"text-anchor": "top"
				}
			};

			layers.push(layer);
			map.addLayer(layer);
		}
		if (map.getZoom() < 14) {
			map.getSource('pointsStartEndRoute').setData(pointsStartEndRoute);
		}
	}

	function addRoutePoints(map, points) {
		if (points != null) {
			var point = {};
			for (var i = 0; i < points.length; i++) {
				if (points[i] != null) {
					point = {
						'id': 'pointsRoute',
						'type': 'Feature',
						"properties": {
							"description": "<p>Остановка: " + points[i].nameWithoutDistrict + "</p>" + "<p>Проходящие маршруты: " + points[i].routeNames + "</p>",
							"title": '',
							"icon": AppConfig.MAP_ICONS.STOP_IN_ROUTE
						},
						'geometry': {
							'type': 'Point',
							'coordinates': points[i].geoJsonPoint.coordinates
						}
					};
				}
				pointsRoute.features.push(point);
			}
			if (map.getSource('pointsRoute') == null) {
				map.addSource('pointsRoute', {
					'type': 'geojson',
					'data': []
				});

				var layer = {
					'id': 'pointsRoute',
					'source': 'pointsRoute',
					'type': "symbol",
					"layout": {
						"icon-image": "{icon}",
						"text-field": "{title}",
						"text-font": ["Open Sans Bold"],
						"text-size": 10,
						"text-offset": [0, 0.6],
						"text-anchor": "top"
					}
				};

				layers.push(layer);
				map.addLayer(layer);
			}
			if (map.getZoom() >= 14) {
				map.getSource('pointsRoute').setData(pointsRoute);
			}
		}
	}

	return {
		init: function (mapParams, deffered, isNotReset) {
			if (!UtilService.checkWebGLSupport()) {
				deffered.resolve(undefined);
				return deffered.promise;
			}
			var map = new mapboxgl.Map({
				container: mapParams.elementId, // container id
				style: {
					"version": 8,
					"sprite": "/images/sprite",
					"glyphs": "/css/fonts/{fontstack}/{range}.pbf",
					"sources": {
						"raster-tiles": {
							"type": "raster",
							"tiles": [$location.protocol() + "://" + $location.host() + ":83/tiles/{z}/{x}/{y}.png"],
							"tileSize": 256
						}
					},
					"layers": [
						{
							"id": "simple-tiles",
							"type": "raster",
							"source": "raster-tiles",
							"minzoom": 0,
							"maxzoom": 22
						}]
				},
				center: [AppConfig.DEFAULT_MAP_LOCATION.lng, AppConfig.DEFAULT_MAP_LOCATION.lat], // starting position
				zoom: AppConfig.DEFAULT_MAP_ZOOM // starting zoom
			});

			if (!isNotReset) {
				pointsStore = {
					"type": "FeatureCollection",
					"features": {}
				};
				pointsStartEndRoute = {
					"type": "FeatureCollection",
					"features": []
				};
				pointsRoute = {
					"type": "FeatureCollection",
					"features": []
				};
				routesStore = {
					"type": "FeatureCollection",
					"features": []
				};
				polygonsStrore = {
					"type": "FeatureCollection",
					"features": []
				};

				layers = [];

				popup.remove();

				popup = new mapboxgl.Popup({
					closeButton: false,
					closeOnClick: false
				});
			}

			onClickMap = null;
			map.addControl(new mapboxgl.Navigation());
			map.on('mousemove', function (e) {
				if (layers != null) {
					for (var i = 0; i < layers.length; i++) {
						if (layers[i].type == "symbol" || layers[i].type == "line") {
							var features = map.queryRenderedFeatures(e.point, {layers: [layers[i].id]});
							if (features.length) {
								map.getCanvas().style.cursor = 'pointer';
								return;
							}
						}
					}
				}
				map.getCanvas().style.cursor = "";
			});
			map.on('click', function (e) {
				if (layers != null) {
					for (var i = 0; i < layers.length; i++) {
						if (layers[i].type == "symbol" || layers[i].type == "line") {
							var features = map.queryRenderedFeatures(e.point, {layers: [layers[i].id]});
							if (features.length) {
								popup = new mapboxgl.Popup().setLngLat(e.lngLat).setHTML(features[0].properties.description).addTo(map);
								return;
							}
						}
					}
					if (onClickMap != null) {
						onClickMap(e.lngLat.lng, e.lngLat.lat);
					}
				}
			});
			map.on('zoom', function () {
				var newZoom = map.getZoom();
				if (newZoom < 14) {
					if (map.getSource('pointsStartEndRoute') != null) {
						map.getSource('pointsStartEndRoute').setData(pointsStartEndRoute);
					}
					if (map.getSource('pointsRoute') != null) {
						map.getSource('pointsRoute').setData({
							"type": "FeatureCollection",
							"features": []
						});
					}
				} else {
					if (map.getSource('pointsStartEndRoute') != null) {
						map.getSource('pointsStartEndRoute').setData({
							"type": "FeatureCollection",
							"features": []
						});
					}
					if (map.getSource('pointsRoute') != null) {
						map.getSource('pointsRoute').setData(pointsRoute);
					}
				}
			});
			deffered.resolve(map);
			return deffered.promise;
		},
		changePointLocation: function (map, pointObj, lng, lat) {
			if (pointObj != null) {
				pointsStore.features[pointObj.type + '_' + pointObj.id].geometry.coordinates = [lng, lat];
				map.getSource('point_' + pointObj.type + '_' + pointObj.id).setData(pointsStore.features[pointObj.type + '_' + pointObj.id]);
				map.flyTo({center: pointsStore.features[pointObj.type + '_' + pointObj.id].geometry.coordinates});
			}
		},
		addOnClickFunc: function (map, onClickFunc) {
			onClickMap = onClickFunc;
		},
		addPoint: function (map, pointObj) {
			if (angular.isDefined(pointObj)) {
				var point = {
					'id': pointObj.type + '_' + pointObj.id,
					'type': 'Feature',
					"properties": {
						"description": pointObj.type == "stop" ? "<p>Остановка: " + pointObj.nameWithoutDistrict + "</p>" + "<p>Проходящие маршруты: " + pointObj.routeNames + "</p>" : "<p>" + pointObj.name + "</p>",
						"title": '',
						"icon": pointObj.type
					},
					'geometry': {
						'type': 'Point',
						'coordinates': pointObj.geoJsonPoint.coordinates
					}
				};

				pointsStore.features[point.id] = point;

				if (map.getSource('point_' + pointObj.type + '_' + pointObj.id) == null) {
					map.addSource('point_' + pointObj.type + '_' + pointObj.id, {
						'type': 'geojson',
						'data': point
					});

					var layer = {
						'id': 'point_' + pointObj.type + '_' + pointObj.id,
						'source': 'point_' + pointObj.type + '_' + pointObj.id,
						'type': "symbol",
						"layout": {
							"icon-image": "{icon}",
							"text-field": "{title}",
							"text-font": ["Open Sans Bold"],
							"text-size": 10,
							"text-offset": [0, 0.6],
							"text-anchor": "top"
						}
					};

					map.addLayer(layer);
					layers.push(layer);
				} else {
					map.getSource('point_' + pointObj.type + '_' + pointObj.id).setData(point);
				}
				map.flyTo({center: point.geometry.coordinates});
				return pointObj;
			}
		},

		addPoints: function (map, pointsJsonArray) {
			//TODO
		},
		addRouteLine: function (map, trajectories, stops, isBound, color) {
			var route = {
				'id': 'route_' + trajectories.id,
				'type': 'Feature',
				"properties": {
					"description": "<p>Маршрут №" + trajectories.routeNumber + " (" + trajectories.trajectoryTypeName + ")</p>"
				},
				'geometry': {
					'type': 'LineString',
					'coordinates': trajectories.geoJsonLine.coordinates
				}
			};

			routesStore.features.push(route);
			if (map.getSource('line_route' + '_' + trajectories.id + '_' + trajectories.trajectoryTypeId + '_' + trajectories.trajectoryNumber) == null) {
				map.addSource('line_route' + '_' + trajectories.id + '_' + trajectories.trajectoryTypeId + '_' + trajectories.trajectoryNumber, {
					'type': 'geojson',
					'data': route
				});

				var layer = {
					'id': 'line_route' + '_' + trajectories.id + '_' + trajectories.trajectoryTypeId + '_' + trajectories.trajectoryNumber,
					"type": "line",
					"source": 'line_route' + '_' + trajectories.id + '_' + trajectories.trajectoryTypeId + '_' + trajectories.trajectoryNumber,
					"layout": {
						"line-join": "round",
						"line-cap": "round"
					},
					"paint": {
						"line-color": color,
						"line-width": 5
					}
				};
				map.addLayer(layer);
				layers.push(layer);
			} else {
				map.getSource('line_route' + '_' + trajectories.id + '_' + trajectories.trajectoryTypeId + '_' + trajectories.trajectoryNumber).setData(route);
			}

			addRouteStartEndPoint(map, trajectories.id + '_' + trajectories.trajectoryTypeId + '_' + trajectories.trajectoryNumber, trajectories.routeNumber, trajectories.routeType, trajectories.geoJsonLine.coordinates[0], trajectories.geoJsonLine.coordinates[trajectories.geoJsonLine.coordinates.length - 1]);
			addRoutePoints(map, stops);
			if (!angular.isDefined(isBound) || isBound == true) {
				map.flyTo({center: route.geometry.coordinates[0]});
			}
		},
		removeAll: function (map, isNotReset) {
			for (var i = 0; i < layers.length; i++) {
				if (map.getLayer(layers[i].id) != null) {
					map.removeLayer(layers[i].id);
					map.removeSource(layers[i].id);
				}
			}
			if (!isNotReset) {
				pointsStore.features = {};
				pointsStartEndRoute.features = [];
				pointsRoute.features = [];
				routesStore.features = [];
				polygonsStrore.features = [];
				layers = [];
				popup.remove();

				popup = new mapboxgl.Popup({
					closeButton: false,
					closeOnClick: false
				});
			}
		},

		addPolygon: function (map, polygons, color) {
			for (var k = 0; k < polygons.length; k++) {
				var polygon = {
					'id': polygons.type + '_' + polygons.id + '_' + k,
					'type': 'Feature',
					'geometry': {
						'type': 'Polygon',
						'coordinates': [[]]
					}
				};
				for (var i = 0; i < polygons[k].coordinates.length; i++) {
					for (var j = 0; j < polygons[k].coordinates[i].length; j++) {
						polygon.geometry.coordinates[0].push(polygons[k].coordinates[i][j]);
					}
				}
				polygonsStrore.features.push(polygon);
				if (map.getSource('polygon_' + polygons.type + '_' + polygons.id + '_' + k) == null) {
					map.addSource('polygon_' + polygons.type + '_' + polygons.id + '_' + k, {
						'type': 'geojson',
						'data': polygon
					});

					var layer = {
						'id': 'polygon_' + polygons.type + '_' + polygons.id + '_' + k,
						'type': 'fill',
						'source': 'polygon_' + polygons.type + '_' + polygons.id + '_' + k,
						'layout': {},
						'paint': {
							'fill-color': color,
							'fill-opacity': 0.4
						}
					};
					map.addLayer(layer);
					layers.push(layer);
				} else {
					map.getSource('polygon_' + polygons.type + '_' + polygons.id + '_' + k).setData(polygon);
				}
			}

			map.flyTo({center: polygon.geometry.coordinates[0][0]});
		},
		remove: function (map, pointObj) {
			if (pointObj != null && (pointObj.type == 'venue' || pointObj.type == 'stop' || pointObj.type == 'department')) {
				var id = 'point_' + pointObj.type + '_' + pointObj.id;
				if (map.getLayer(id) != null) {
					map.removeLayer(id);
				}
				if (map.getSource(id) != null) {
					map.removeSource(id);
				}
				for (var i = 0; i < layers.length; i++) {
					if (layers[i].id == id) {
						layers.splice(i, 1);
						break;
					}
				}
				delete pointsStore.features[id];
			}
		},

		setCenterBounds: function (map) {
			var maxLng = null;
			var minLng = null;
			var maxLat = null;
			var minLat = null;

			angular.forEach(pointsStore.features, function (point) {
				if (maxLng == null || point.geometry.coordinates[0] < maxLng) {
					maxLng = point.geometry.coordinates[0];
				}
				if (minLng == null || point.geometry.coordinates[0] > minLng) {
					minLng = point.geometry.coordinates[0];
				}
				if (maxLat == null || point.geometry.coordinates[1] < maxLat) {
					maxLat = point.geometry.coordinates[1];
				}
				if (minLat == null || point.geometry.coordinates[1] > minLat) {
					minLat = point.geometry.coordinates[1];
				}
			});

			angular.forEach(pointsStartEndRoute.features, function (point) {
				if (maxLng == null || point.geometry.coordinates[0] < maxLng) {
					maxLng = point.geometry.coordinates[0];
				}
				if (minLng == null || point.geometry.coordinates[0] > minLng) {
					minLng = point.geometry.coordinates[0];
				}
				if (maxLat == null || point.geometry.coordinates[1] < maxLat) {
					maxLat = point.geometry.coordinates[1];
				}
				if (minLat == null || point.geometry.coordinates[1] > minLat) {
					minLat = point.geometry.coordinates[1];
				}
			});

			angular.forEach(polygonsStrore.features, function (polygon) {
				angular.forEach(polygon.geometry.coordinates[0], function (point) {
					if (maxLng == null || point.geometry.coordinates[0] < maxLng) {
						maxLng = point.geometry.coordinates[0];
					}
					if (minLng == null || point.geometry.coordinates[0] > minLng) {
						minLng = point.geometry.coordinates[0];
					}
					if (maxLat == null || point.geometry.coordinates[1] < maxLat) {
						maxLat = point.geometry.coordinates[1];
					}
					if (minLat == null || point.geometry.coordinates[1] > minLat) {
						minLat = point.geometry.coordinates[1];
					}
				});
			});

			map.fitBounds([[minLng, minLat], [maxLng, maxLat]]);
		}
	}
}]);