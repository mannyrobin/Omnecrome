angular.module('armada')
	.controller('TaskRoutesCtrl', ['$controller', '$scope', '$stateParams', 'TasksService', 'ArMapService', 'RoutesService', 'WktParseService', 'DepartmentsService', 'VenuesService',
		function ($controller, $scope, $stateParams, TasksService, ArMapService, RoutesService, WktParseService, DepartmentsService, VenuesService) {

			angular.element("#requestSpinner").css('visibility', 'visible');

			$scope.bound = false;

			$scope.mapKey = "test";
			$scope.mapServiceYa = ArMapService.initService({mapType: "ya"});
			$scope.mapServiceOther = ArMapService.initService({mapType: "other"});
			$scope.isMapYa = true;
			$scope.isMapOther = false;
			$scope.mapService = $scope.mapServiceYa;
			$scope.department = null;
			$scope.venue = null;
			var taskId = $stateParams.taskId != null ? $stateParams.taskId : $stateParams.itemId;

			$scope.addAllToFilter = function (i) {
				var all = {id: -1, name: "Не выбрано"};
				$scope.fields[i].list.unshift(all);
				if ($scope.fields[i].value == null) {
					$scope.fields[i].value = all;
				}
			};

			/* Поля ввода */
			$scope.fields = [{
				name: "taskRouteType",
				type: "select",
				load: TasksService.getTaskRouteType,
				label: "Тип маршрута",
				required: false,
				afterLoad: function () {
					if ($scope.fields[0].value == null) {
						$scope.fields[0].value = $scope.fields[0].list[0];
					}
				},
				onChange: function () {
					angular.element("#requestSpinner").css('visibility', 'visible');
					$scope.mapService.removeAll();
					changeRouteType();
					$scope.fields[1].refresh();
				}
			}, {
				name: "taskRoute",
				type: "select",
				load: function () {
					return TasksService.getTaskRoutesForType($scope.fields[0].value != null ? $scope.fields[0].value.id : null, taskId);
				},
				label: "Номер маршрута",
				required: false,
				afterLoad: function () {
					$scope.addAllToFilter(1);
					$scope.fillAll();
				},
				onChange: function () {
					$scope.mapService.removeAll();
					if ($scope.fields[1].value.id == -1) {
						$scope.fillAll();
					} else {
						$scope.addRoute($scope.fields[1].value, true);
					}
				}
			}, {
				name: "mapType",
				type: "mapType",
				label: "Тип карты",
				required: false,
				onChange: function () {
					$scope.isMapYa = $scope.fields[2].value.id == 0;
					$scope.isMapOther = $scope.fields[2].value.id == 1;
					$scope.department = null;
					$scope.venue = null;
					$scope.mapService = $scope.fields[2].value.id == 1 ? $scope.mapServiceOther : $scope.mapServiceYa;
					$scope.mapService.removeAll();
					changeRouteType();
					$scope.fillAll();
				}
			}];

			$scope.fillAll = function () {
				if ($scope.fields[1].list == undefined) {
					return;
				}
				if ($scope.fields[1].value != null) {
					$scope.bound = false;
					if ($scope.fields[1].value.id == -1) {
						for (var i = 0; i < $scope.fields[1].list.length; i++) {
							if ($scope.fields[1].list[i].id == -1)
								continue;
							$scope.addRoute($scope.fields[1].list[i], false);
						}
					} else {
						$scope.addRoute($scope.fields[1].value, true);
					}
					if ($scope.bound) {
						$scope.mapService.setCenterBounds();
					}
				}
				angular.element("#requestSpinner").css('visibility', 'hidden');
			};

			$scope.addRoute = function (route, isBound) {
				if (route == null)
					return;
                if (route.routeTrajectories == null)
                    return;
				for (var i = 0; i < route.routeTrajectories.length; i++) {
					route.routeTrajectories[i].trajectoryNumber = i;
					$scope.mapService.addRouteLine(processTrajectories(route.routeTrajectories[i]), processStops(route.routeStops[route.routeTrajectories[i].trajectoryTypeId]), isBound);
					$scope.bound = true;
				}
			};

			function changeRouteType() {
				if ($scope.fields[0].value != null) {
					if ($scope.fields[0].value.id == 2) {
						if ($scope.department != null) {
							$scope.mapService.remove($scope.department);
							$scope.department = null;
						}
						if ($scope.venue == null) {
							TasksService.getAdditionalInfo(taskId).then(function (result) {
								if (result.venueId != null) {
									VenuesService.getItem(result.venueId).then(function (response) {
										if (response != null && response.longitude != null && response.latitude != null) {
											var point = {};
											point.id = response.id;
											point.geoJsonPoint = WktParseService.wktToGeoJson('POINT(' + response.longitude + ' ' + response.latitude + ')');
											point.name = response.name;
											point.type = 'venue';
											$scope.venue = $scope.mapService.addPoint(point);
										}
									});
								}
							});
						} else {
							$scope.mapService.addPoint($scope.venue);
						}
					} else {
						if ($scope.fields[0].value.id == 1) {
							if ($scope.venue != null) {
								$scope.mapService.remove($scope.venue);
								$scope.venue = null;
							}
							if ($scope.department == null) {
								TasksService.getAdditionalInfo(taskId).then(function (result) {
									if (result.deptId != null) {
										DepartmentsService.getItem(result.deptId).then(function (response) {
											if (response != null && response.longitude != null && response.latitude != null) {
												var point = {};
												point.id = response.id;
												point.geoJsonPoint = WktParseService.wktToGeoJson('POINT(' + response.longitude + ' ' + response.latitude + ')');
												point.name = response.name;
												point.type = 'department';
												$scope.department = $scope.mapService.addPoint(point);
											}
										});
									}
								});
							} else {
								$scope.mapService.addPoint($scope.department);
							}
						}
					}
				}
			}

			function processStops(stops) {
				var results = [];
				for (var i = 0; i < stops.length; i++) {
					results.push({
						id: stops[i].id,
						routeId: stops[i].routeId,
						name: stops[i].name,
						routeNames: stops[i].routeNames,
						nameWithoutDistrict: stops[i].nameWithoutDistrict,
						geoJsonPoint: WktParseService.wktToGeoJson(stops[i].wktPointStr)
					});
				}
				return results;
			}

			function processTrajectories(trajectories) {
				var result = [];
				result.id = trajectories.id;
				result.routeNumber = trajectories.routeNumber;
				result.routeType = trajectories.routeType;
				result.geoJsonLine = WktParseService.wktToGeoJson(trajectories.wktLineStr);
				result.trajectoryTypeId = trajectories.trajectoryTypeId;
				result.trajectoryTypeName = trajectories.trajectoryTypeName;
				return result;
			}

			data.id = taskId;
			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, TasksService);

		}]);
