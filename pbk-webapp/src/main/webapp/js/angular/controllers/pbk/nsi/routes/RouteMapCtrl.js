angular.module('armada')
	.controller('RouteMapCtrl', ['$controller', '$scope', '$stateParams', 'AppConfig', 'ArMapService', 'RoutesService', 'DepartmentsService', 'WktParseService',
		function ($controller, $scope, $stateParams, AppConfig, ArMapService, RoutesService, DepartmentsService, WktParseService) {

			$scope.mapKey = "test";
			$scope.mapServiceYa = ArMapService.initService({mapType: "ya"});
			$scope.mapServiceOther = ArMapService.initService({mapType: "other"});
			$scope.isMapYa = true;
			$scope.isMapOther = false;
			$scope.mapService = $scope.mapServiceYa;
			$scope.department = null;
			$scope.all = {id: -1, name: "Не выбрано"};

			$scope.onLoad = function () {
				if ($scope.department != null) {
					$scope.mapService.addPoint($scope.department);
				}
				RoutesService.getRouteTrajectory($stateParams.itemId).then(function (trajectories) {
					for (var i = 0; i < trajectories.length; i++) {
						trajectories[i].trajectoryNumber = i;
						function processTrajectory(trajectory) {
							var onStops = function (stops) {
								if (stops.length > 0) {
									$scope.mapService.addRouteLine(trajectory, stops);
								}
							};
							RoutesService.getRouteStops(trajectories[i].id, trajectories[i].trajectoryTypeId).then(onStops);
						}

						var trajectory = trajectories[i];
						processTrajectory(trajectory);
					}
				});
			};

			$scope.fields = [{
				name: "departmenId",
				type: "select",
				load: function () {
					return DepartmentsService.getSelectItemForMap([
						{
							name: "routeId",
							value: $stateParams.itemId,
							type: "id"
						}
					]);
				},
				label: "МОСД",
				required: false,
				onChange: function () {
					if ($scope.department != null) {
						$scope.mapService.remove($scope.department);
						$scope.department = null;
					}
					if ($scope.fields[0].value.id != -1) {
						if ($scope.fields[0].value.geoJsonPoint == undefined) {
							DepartmentsService.getItem($scope.fields[0].value.id).then(function (response) {
								if (response.longitude != null && response.latitude != null) {
									$scope.fields[0].value.geoJsonPoint = WktParseService.wktToGeoJson('POINT(' + response.longitude + ' ' + response.latitude + ')');
									$scope.fields[0].value.name = response.name;
									$scope.fields[0].value.type = "department";
									$scope.department = $scope.mapService.addPoint($scope.fields[0].value);
									$scope.department.id = $scope.fields[0].value.id;
									$scope.department.geoJsonPoint = $scope.fields[0].value.geoJsonPoint;
									$scope.department.name = $scope.fields[0].value.name;
									$scope.department.type = "department";
								}
							});
						} else {
							$scope.department = $scope.mapService.addPoint($scope.fields[0].value);
							$scope.department.id = $scope.fields[0].value.id;
							$scope.department.geoJsonPoint = $scope.fields[0].value.geoJsonPoint;
							$scope.department.name = $scope.fields[0].value.name;
							$scope.department.type = "department";
						}
					}
				},
				afterLoad: function () {
					$scope.addAllToFilter(0);
					$scope.department = null;
				}
			}, {
				name: "mapType",
				type: "mapType",
				label: "Тип карты",
				required: false,
				onChange: function () {
					$scope.isMapYa = $scope.fields[1].value.id == 0;
					$scope.isMapOther = $scope.fields[1].value.id == 1;
					$scope.mapService.removeAll();
					$scope.mapService = $scope.fields[1].value.id == 1 ? $scope.mapServiceOther : $scope.mapServiceYa;
					$scope.onLoad();
				}
			}];

			$scope.addAllToFilter = function (i) {
				$scope.fields[i].list.unshift($scope.all);
				if ($scope.fields[i].value == null) {
					$scope.fields[i].value = $scope.all;
				}
			};

			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: null}, $scope.fields, null);
		}]);