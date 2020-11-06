angular.module('armada').service("ArMapService", ['YaMapService', 'OtherMapService', 'AppConfig', 'Notification', function (YaMapService, OtherMapService, AppConfig, Notification) {

	var colors = {};

	function getRandomColor() {
		var letters = '0123456789ABCDEF'.split('');
		var color = '#';
		for (var i = 0; i < 6; i++) {
			color += letters[Math.floor(Math.random() * 16)];
		}
		return color;
	}

	function getPointColor(type) {
		if (type == 'venue') {
			return AppConfig.MAP_COLORS.VENUE;
		}
		if (type == 'department') {
			return AppConfig.MAP_COLORS.DEPARTMENT;
		}
		if (type == 'stop') {
			return AppConfig.MAP_COLORS.STOP;
		}
		return getRandomColor();
	}

	return {
		initService: function (mapParams) {
			var result = {};
			if (!angular.isDefined(mapParams.mapType) || mapParams.mapType == 'ya') {
				result.service = YaMapService;
			} else if (mapParams.mapType == 'other') {
				result.service = OtherMapService;
			}

			result.initMap = function (deffered, id, isNotReset) {
				mapParams['elementId'] = id;
				//промис на промисе и промисом погоняет
				return result.service.init(mapParams, deffered, isNotReset).then(function (map) {
					result.map = map;
					return map;
				});
			};
			result.addPoint = function (pointJson) {
				if (pointJson == null || pointJson.geoJsonPoint == null || pointJson.geoJsonPoint.coordinates == null || pointJson.geoJsonPoint.coordinates.length < 1) {
					return;
				}
				if (pointJson.geoJsonPoint.coordinates[0] > 90 || pointJson.geoJsonPoint.coordinates[0] < -90 || pointJson.geoJsonPoint.coordinates[1] > 90 || pointJson.geoJsonPoint.coordinates[1] < -90) {
					Notification.info("Точка " + pointJson.name + " не будет отображена, т.к. имеет некорректные координаты");
					return;
				}
				var map = result.map;
				if (map != undefined) {
					return result.service.addPoint(map, pointJson, getPointColor(pointJson.type));
				} else {
				}
			};
			result.changePointLocation = function (pointObj, lng, lat) {
				var map = result.map;
				if (map != undefined) {
					result.service.changePointLocation(map, pointObj, lng, lat);
				} else {
				}
			};
			result.addOnClickFunc = function (onClickFunc) {
				var map = result.map;
				if (map != undefined) {
					result.service.addOnClickFunc(map, onClickFunc);
				}
			};
			result.addPoints = function (pointsJsonArray) {
				var map = result.map;
				if (map != undefined) {
					return result.service.addPoints(map, pointsJsonArray);
				} else {

				}
			};
			result.addRouteLine = function (line, stops, isBound) {
				if (line == null || line.geoJsonLine == null || line.geoJsonLine.coordinates == null || line.geoJsonLine.coordinates.length < 1) {
					return;
				}
				for (var i = 0; i < line.geoJsonLine.coordinates.length; i++) {
					if (line.geoJsonLine.coordinates[i][0] > 90 || line.geoJsonLine.coordinates[i][0] < -90 || line.geoJsonLine.coordinates[i][1] > 90 || line.geoJsonLine.coordinates[i][1] < -90) {
						Notification.info("Одна из траекторий маршрута " + line.routeNumber + " не будет отображена, т.к. имеет точки с некорректными координатами");
						return;
					}
				}
				if (stops != null) {
					for (var i = 0; i < stops.length; i++) {
						if (stops == null || stops[i].geoJsonPoint.coordinates[0] > 90 || stops[i].geoJsonPoint.coordinates[0] < -90 || stops[i].geoJsonPoint.coordinates[1] > 90 || stops[i].geoJsonPoint.coordinates[1] < -90) {
							stops.splice(i, 1);
						}
					}
				}
				var map = result.map;
				if (map != undefined) {
					if (colors['route_' + line.id + '_' + line.trajectoryTypeId] == null) {
						colors['route_' + line.id + '_' + line.trajectoryTypeId] = getRandomColor();
					}
					return result.service.addRouteLine(map, line, stops, isBound, colors['route_' + line.id + '_' + line.trajectoryTypeId]);
				} else {

				}
			};

			result.removeAll = function (isNotReset) {
				var map = result.map;
				if (map != undefined) {
					return result.service.removeAll(map, isNotReset);
				} else {

				}
			};

			result.remove = function (object) {
				var map = result.map;
				if (map != undefined) {
					return result.service.remove(map, object);
				} else {

				}
			};

			result.add = function (object) {
				var map = result.map;
				if (map != undefined) {
					return result.service.add(map, object);
				} else {

				}
			};

			result.addPolygon = function (polygon) {
				if (polygon == null || polygon.length < 1) {
					return;
				}
				for (var k = 0; k < polygon.length; k++) {
					if (polygon[k] == null || polygon[k].coordinates == null) {
						Notification.info("Полигон не будет отображен, т.к. имеет некорректные координаты");
						return;
					}
					for (var i = 0; i < polygon[k].coordinates.length; i++) {
						for (var j = 0; j < polygon[k].coordinates[i].length; j++) {
							if (polygon[k].coordinates[i][j][0] > 90 || polygon[k].coordinates[i][j][0] < -90 || polygon[k].coordinates[i][j][1] > 90 || polygon[k].coordinates[i][j][1] < -90) {
								Notification.info("Полигон не будет отображен, т.к. имеет некорректные координаты");
								return;
							}
						}
					}
				}
				var map = result.map;
				if (map != undefined) {
					if (colors[polygon.type + '_' + polygon.id] == null) {
						colors[polygon.type + '_' + polygon.id] = getRandomColor();
					}
					return result.service.addPolygon(map, polygon, colors[polygon.type + '_' + polygon.id]);
				} else {

				}
			};

			result.setCenterBounds = function () {
				var map = result.map;
				if (map != undefined) {
					return result.service.setCenterBounds(map);
				} else {

				}
			};

			return result;
		}
	}
}]);