'use strict';

/**
 * @ngdoc function
 * @name armada.controller:VenuesEditCtrl
 * @description
 *
 * Контроллер модального окна создания или редактирования маршрута Района Москвы.
 */
angular.module('armada')
	.controller('VenueRouteAddCtrl', ['$controller', '$scope', '$stateParams', '$modalInstance', 'data', 'VenueRoutesService', 'RoutesService', 'VenueRouteTypesService', 'ArMapService',
		'WktParseService', 'DistrictsService', 'VenuesService', 'DepartmentsService',
		function ($controller, $scope, $stateParams, $modalInstance, data, VenueRoutesService, RoutesService, VenueRouteTypesService, ArMapService, WktParseService, DistrictsService,
				  VenuesService, DepartmentsService) {

			angular.element("#requestSpinner").css('visibility', 'visible');

			$scope.districtId = data.item.districtId;
			$scope.districtVenueId = data.item.id;
			$scope.department = null;
			$scope.venue = null;

			$scope.isAllCountyRoute = false;

			$scope.mapKey = "test";
			$scope.mapServiceYa = ArMapService.initService({mapType: "ya"});
			$scope.mapServiceOther = ArMapService.initService({mapType: "other"});
			$scope.isMapYa = true;
			$scope.isMapOther = false;
			$scope.mapService = $scope.mapServiceYa;
			$scope.districtRoutes = [];
			$scope.countyRoutes = [];

			$scope.isLoadMap = false;

			$scope.onLoadMap = function () {
				$scope.isLoadMap = true;
				$scope.fillAll();
			}

			/* Поля ввода */
			$scope.fields = [{
				name: "venueRouteTypeId",
				type: "select",
				load: VenueRouteTypesService.getList,
				required: true,
				label: "Тип маршрута",
				onChange: function () {
					changeRouteType();
				}
			}, {
				name: "routeIds",
				type: "multiselect",
				load: function () {
					return RoutesService.getRoutesForDistrictAdd([{
						name: "districtId",
						value: $scope.districtId,
						type: "id"
					}, {
						name: "venueId",
						value: $stateParams.itemId,
						type: "id"
					}, {
						name: "venueRouteTypeId",
						value: $scope.fields[0].value == null || $scope.fields[0].value.id == -1 ? null : $scope.fields[0].value,
						type: "select"
					}
					])
				},
				afterLoad: function () {
					$scope.fillAll();
				},
				label: "Маршрут",
				required: true
			}, {
				name: "districtId",
				type: "text",
				defval: $scope.districtVenueId
			}, {
				name: "mapType",
				type: "mapType",
				label: "Тип карты",
				required: false,
				onChange: function () {
					$scope.isMapYa = $scope.fields[3].value.id == 0;
					$scope.isMapOther = $scope.fields[3].value.id == 1;
					$scope.department = null;
					$scope.venue = null;
					$scope.mapService = $scope.fields[3].value.id == 1 ? $scope.mapServiceOther : $scope.mapServiceYa;
					$scope.fillAll();
					changeRouteType();
				}
			}];

			$scope.fillAllClick = function () {
				$scope.isAllCountyRoute = !$scope.isAllCountyRoute;
				$scope.fillAll();
			}

			$scope.fillAll = function () {
				if (!$scope.isLoadMap)
					return
				var func = $scope.isAllCountyRoute ? RoutesService.getRoutesOfCounty : RoutesService.getRoutesForVenueDistrict;
				var arr = $scope.isAllCountyRoute ? $scope.countyRoutes : $scope.districtRoutes;
				$scope.mapService.removeAll(true);
				angular.element("#requestSpinner").css('visibility', 'visible');
				DistrictsService.getEgko($scope.districtId).then(function (polygon) {
					$scope.mapService.addPolygon(polygon);
					var load = function (filter, arr) {
						func(filter).then(function (response) {
							for (var i = 0; i < response.length; i++) {
								$scope.addRoute(response[i], false, arr);
							}
							angular.element("#requestSpinner").css('visibility', 'hidden');
						});
					};
					if (arr.length == 0) {
						load([{
							name: "districtId",
							value: $scope.districtId,
							type: "id"
						}, {
							name: "venueId",
							value: $stateParams.itemId,
							type: "id"
						}], arr);
					} else {
						for (var i = 0; i < arr.length; i++) {
							$scope.mapService.addRouteLine(arr[i], null, false);
							angular.element("#requestSpinner").css('visibility', 'hidden');
						}
					}
				});
			};

			$scope.addRoute = function (route, isBound, arr) {
				if (route == null || route.routeTrajectories == null)
					return;
				for (var i = 0; i < route.routeTrajectories.length; i++) {
					var traj = processTrajectories(route.routeTrajectories[i]);
					if (arr !== undefined) {
						arr.push(traj);
					}
					$scope.mapService.addRouteLine(traj, null, isBound);
				}
			};

			function processTrajectories(trajectories) {
				var result = [];
				result.id = trajectories.id;
				result.routeNumber = trajectories.routeNumber;
				result.geoJsonLine = WktParseService.wktToGeoJson(trajectories.wktLineStr);
				result.trajectoryTypeId = trajectories.trajectoryTypeId;
				result.trajectoryTypeName = trajectories.trajectoryTypeName;
				result.routeType = trajectories.routeType;
				return result;
			}

			function changeRouteType() {
				$scope.fields[1].refresh();
				if ($scope.fields[0].value != null) {
					if ($scope.fields[0].value.id == 1) {
						if ($scope.department != null) {
							$scope.mapService.remove($scope.department);
							$scope.department = null;
						}
						if ($scope.venue == null) {
							VenuesService.getItem($stateParams.itemId).then(function (response) {
								if (response != null && response.longitude != null && response.latitude != null) {
									var point = {};
									point.id = response.id;
									point.geoJsonPoint = WktParseService.wktToGeoJson('POINT(' + response.longitude + ' ' + response.latitude + ')');
									point.name = response.name;
									point.type = 'venue';
									$scope.venue = $scope.mapService.addPoint(point);
								}
							});
						} else {
							$scope.mapService.addPoint($scope.venue);
						}
					} else {
						if ($scope.venue != null) {
							$scope.mapService.remove($scope.venue);
							$scope.venue = null;
						}
						if ($scope.department == null) {
							DepartmentsService.getByVenueId($stateParams.itemId).then(function (response) {
								if (response != null && response.longitude != null && response.latitude != null) {
									var point = {};
									point.id = response.id;
									point.geoJsonPoint = WktParseService.wktToGeoJson('POINT(' + response.longitude + ' ' + response.latitude + ')');
									point.name = response.name;
									point.type = 'department';
									$scope.department = $scope.mapService.addPoint(point);
								}
							});
						} else {
							$scope.mapService.addPoint($scope.department);
						}
					}
				}
			}

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			data.item = {venueId: $stateParams.itemId};
			$scope.init(data, $scope.fields, VenueRoutesService, $modalInstance);
		}]);