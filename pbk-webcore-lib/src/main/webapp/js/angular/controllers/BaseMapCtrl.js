'use strict';

/**
 * @ngdoc function
 * @name armada.controller:VenueLocationCtrl
 * @description
 *
 * Местоположение
 */
angular.module('armada')
	.controller('BaseMapCtrl', ['$controller', '$scope', 'AppConfig', 'MapService',
		function ($controller, $scope, AppConfig, MapService) {

			$scope.init = function (data, fields, actions, modalInstance, item) {
				$scope.location = {lat: null, lng: null};

				$scope.centerCoords = [AppConfig.DEFAULT_MAP_LOCATION.lng, AppConfig.DEFAULT_MAP_LOCATION.lat];
				$scope.defaultMapZoom = AppConfig.DEFAULT_MAP_ZOOM;
				$scope.coordinates = [];

				var yaMarker = null;
				var yaMap = null;

				$scope.afterYandexMapInit = function (map) {
					completeYandexMap(map, $scope.location)
				};

				function completeYandexMap(map, location) {
					yaMap = map;
					if ($scope.onClickMap != null) {
						map.events.add('click', function (e) {
							var coords = e.get('coords');
							var lat = coords[1].toPrecision(6);
							var lng = coords[0].toPrecision(6);
							MapService.setFloatLocations(lat, lng, $scope.location);
							yaMarker = MapService.setYaCursor(lat, lng, yaMap, yaMarker);
							$scope.onClickMap(lat, lng);
							$scope.$apply();
						});
					}
					yaMarker = MapService.setYaCursor(location.lat, location.lng, yaMap, yaMarker);
				}

				/* Инициализация */
				$controller('BaseEditCtrl', {$scope: $scope});
				$scope.init(data, fields, actions, modalInstance, item);

				if ($scope.id != null) {
					if ($scope.actions.getItem) {
						$scope.actions.getItem($scope.id).then(function (result) {
							MapService.setFloatLocations(result.latitude, result.longitude, $scope.location);
							yaMarker = MapService.setYaCursor($scope.location.lat, $scope.location.lng, yaMap, yaMarker);
						});
					}
				}

			}
		}]);