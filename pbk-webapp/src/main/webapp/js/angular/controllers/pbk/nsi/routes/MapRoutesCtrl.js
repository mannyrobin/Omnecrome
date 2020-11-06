angular.module('armada')
	.controller('MapRoutesCtrl', ['$controller', '$scope', '$stateParams', 'ArMapService', 'RoutesService',
		'DepartmentsService', 'CountiesService', 'DistrictsService', 'VenuesService', 'StopsService', 'WktParseService',
		function ($controller, $scope, $stateParams, ArMapService, RoutesService,
				  DepartmentsService, CountiesService, DistrictsService, VenuesService, StopsService, WktParseService) {

			$scope.county = null;
			$scope.point = null;
			$scope.venue = null;
			$scope.all = {id: -1, name: "Не выбрано"};
			$scope.districtRoutes = {};
			$scope.stopsString = '';

			$scope.mapServiceYa = ArMapService.initService({mapType: "ya"});
			$scope.mapServiceOther = ArMapService.initService({mapType: "other"});
			$scope.isMapYa = true;
			$scope.isMapOther = false;
            $scope.isPresentStops = false;
			$scope.mapService = $scope.mapServiceYa;
			$scope.filterDisable = 0;

			function changeMultiFilter(i) {
				if ($scope.fields[i].value == null || angular.isDefined($scope.fields[i].value) && $scope.fields[i].value.length == 0) {
					$scope.fields[i].value = [$scope.all];
				} else if ($scope.fields[i].value.length > 1 && $scope.fields[i].value[0].id == -1) {
					$scope.fields[i].value.shift();
				}
			}

			$scope.addAllToFilter = function (i) {
				$scope.fields[i].list.unshift($scope.all);
				if ($scope.fields[i].value == null) {
					$scope.fields[i].value = $scope.all;
				}
			};

			$scope.$watch('fields[4].value', function () {
				changeMultiFilter(4);
				$scope.fields[5].list = [];
				$scope.fields[5].value = null;
				$scope.addAllToFilter(5);
				$scope.point = null;
				if ($scope.fields[4].value != null && ($scope.fields[4].value.length == 2 && $scope.fields[4].value[0].id == -1 && $scope.fields[4].value[1].id > 0 || $scope.fields[4].value.length == 1 && $scope.fields[4].value[0].id > 0)) {
					RoutesService.getRouteTrajectory($scope.fields[4].value[0].id > 0 ? $scope.fields[4].value[0].id : $scope.fields[4].value[1].id).then(function (trajectories) {
						for (var i = 0; i < trajectories.length; i++) {
							function processTrajectory(trajectory) {
								var onStops = function (stops) {
									$scope.fields[5].list = stops.slice();
									$scope.addAllToFilter(5);
								};
								RoutesService.getRouteStops(trajectories[i].id, trajectories[i].trajectoryTypeId).then(onStops);
							}

							var trajectory = trajectories[i];
							processTrajectory(trajectory);
						}
					});
				}
			});

			/* Поля ввода */
			$scope.fields = [{
				name: "departmenId",
				type: "multiselect",
				load: function () {
					return DepartmentsService.getList([
						{
							name: "forPlanUse",
							value: "1",
							type: "text"
						}
					]);
				},
				label: "МОК",
				required: false,
				onChange: function () {
					changeMultiFilter(0);
					$scope.departments = [];
					$scope.county = null;
					$scope.fields[1].refresh();
					$scope.fields[2].refresh();
					$scope.fields[3].refresh();
					$scope.fields[4].refresh();
					if ($scope.fields[0].value.length != 1
						|| $scope.fields[0].value[0].id != -1) {

						angular.forEach($scope.fields[0].value, function (value, key) {
							if (value.geoJsonPoint == undefined) {
								DepartmentsService.getItem(value.id).then(function (response) {
									if (response.longitude != null && response.latitude != null) {
										value.geoJsonPoint = WktParseService.wktToGeoJson('POINT(' + response.longitude + ' ' + response.latitude + ')');
										value.name = response.name;
										value.type = "department";
										$scope.departments.push(value);
									}
								});
							} else {
								$scope.departments.push(value);
							}
						});
					}
				},
				afterLoad: function () {
					$scope.addAllToFilter(0);
					$scope.departments = [];
				}
			}, {
				name: "countyId",
				type: "select",
				load: function () {
					return CountiesService.getList([{
						name: "deptInId",
						value: !$scope.fields[0].value
						|| ($scope.fields[0].value.length == 1 && $scope.fields[0].value[0].id == -1)
							? null : $scope.fields[0].value,
						type: "multiselect"
					}]);
				},
				label: "Округ",
				required: false,
				onChange: function () {
					$scope.fields[2].refresh();
					$scope.fields[4].refresh();
					if ($scope.fields[1].value.id == -1) {
						$scope.county = null;
					} else {
						CountiesService.getEgko($scope.fields[1].value.id).then(function (polygon) {
							$scope.county = polygon;
						});
					}
				},
				afterLoad: function () {
					$scope.addAllToFilter(1);
				}
			}, {
				name: "districtId",
				type: "multiselect",
				label: "Район",
				load: function () {
					return DistrictsService.getList([{
						name: "countyId",
						value: $scope.fields[1].value == null || $scope.fields[1].value.id == -1 ? null : $scope.fields[1].value,
						type: "select"
					}
					]);
				},
				required: false,
				onChange: function () {
					changeMultiFilter(2);
					$scope.fields[4].refresh();
					if ($scope.fields[2].value.length > 0 && $scope.fields[2].value[$scope.fields[2].value.length - 1].id != -1 && $scope.fields[2].value[$scope.fields[2].value.length - 1].district == null) {
						DistrictsService.getEgko($scope.fields[2].value[$scope.fields[2].value.length - 1].id).then(function (polygon) {
							$scope.fields[2].value[$scope.fields[2].value.length - 1].district = polygon;
						});
					}
				},
				afterLoad: function () {
					changeMultiFilter(2);
				}
			}, {
				name: "venueId",
				type: "select",
				label: "Место встречи",
				load: function () {
					return VenuesService.getList([{
						name: "deptInId",
						value: !$scope.fields[0].value
						|| ($scope.fields[0].value.length == 1 && $scope.fields[0].value[0].id == -1)
							? null : $scope.fields[0].value,
						type: "multiselect"
					}
					]);
				},
				required: false,
				onChange: function () {
					$scope.fields[4].refresh();
					if ($scope.fields[3].value != null) {
						if ($scope.fields[3].value.id != -1) {
							if ($scope.fields[3].value.geoJsonPoint == undefined) {
								VenuesService.getItem($scope.fields[3].value.id).then(function (response) {
									if (response != null && response.longitude != null && response.latitude != null) {
										$scope.fields[3].value.geoJsonPoint = WktParseService.wktToGeoJson('POINT(' + response.longitude + ' ' + response.latitude + ')');
										$scope.fields[3].value.name = response.name;
										$scope.fields[3].value.type = "venue";
										$scope.venue = $scope.fields[3].value;
									}
								});
							} else {
								$scope.venue = $scope.fields[3].value;
							}
						}
					}
				},
				afterLoad: function () {
					$scope.venue = null;
					$scope.addAllToFilter(3);
				},
				lazyload: function (query) {
					return VenuesService.getList([{
						name: "name",
						value: query,
						type: "text"
					}]);
				}
			}, {
				name: "routeId",
				type: "multiselect",
				label: "Номер маршрута",
				load: function () {

					return RoutesService.getList([{
						name: "districtInId",
						value: $scope.fields[2].value == null
						|| $scope.fields[2].value[0] == $scope.all ? null : $scope.fields[2].value,
						type: "multiselect"
					}, {
						name: "venueInId",
						value: $scope.fields[3].value == null
						|| $scope.fields[3].value == $scope.all ? null : $scope.fields[3].value,
						type: "select"
					}, {
						name: "deptInId",
						value: !$scope.fields[0].value
						|| ($scope.fields[0].value.length == 1 && $scope.fields[0].value[0].id == -1)
							? null : $scope.fields[0].value,
						type: "multiselect"
					}]);

				},
				required: false,
				afterLoad: function () {
					changeMultiFilter(4);
					$scope.fields[5].list = [];
					$scope.fields[5].value = null;
					$scope.addAllToFilter(5);
					$scope.point = null;
					if ($scope.fields[2].value != null && $scope.fields[2].value.length > 0 && $scope.fields[2].value[0].id != -1) {
						$scope.fields[4].list.unshift({id: -2, name: "Все маршруты района"});
					}
				}
			}, {
				name: "stopId",
				type: "select",
				label: "Наименование остановочного пункта",
				required: false,
				onChange: function () {
					if ($scope.fields[5].value != null && $scope.fields[5].value.id != -1) {
						if ($scope.fields[5].value.geoJsonPoint == undefined) {
							StopsService.getItem($scope.fields[5].value.id).then(function (response) {
								if (response != null && response.longitude != null && response.latitude != null) {
									$scope.fields[5].value.geoJsonPoint = WktParseService.wktToGeoJson('POINT(' + response.longitude + ' ' + response.latitude + ')');
									$scope.fields[5].value.name = response.name;
									$scope.fields[5].value.type = 'stop';
									$scope.fields[5].value.routeNames = response.routeNames != null ? response.routeNames : '';
									$scope.fields[5].value.nameWithoutDistrict = response.nameWithoutDistrict;
									$scope.point = $scope.fields[5].value;
								}
							});
						} else {
							$scope.point = $scope.fields[5].value;
						}
					}

				},
				afterLoad: function () {
					$scope.point = null;
					$scope.addAllToFilter(5);
				},
				lazyload: function (query) {
					return StopsService.getList([{
						name: "stopName",
						value: query,
						type: "text"
					}]);
				}, isLazyload: function () {
					return $scope.fields[5].list == undefined || $scope.fields[5].list.length == 0 ||
						($scope.fields[5].list.length == 1 && $scope.fields[5].list[0].id == -1);
				}
			}, {
				name: "mapType",
				type: "mapType",
				label: "Тип карты",
				required: false,
				onChange: function () {
					$scope.isMapYa = $scope.fields[6].value.id == 0;
					$scope.isMapOther = $scope.fields[6].value.id == 1;
					$scope.mapService = $scope.fields[6].value.id == 1 ? $scope.mapServiceOther : $scope.mapServiceYa;
					$scope.mapService.removeAll();
				}
			}];

			$scope.clearAll = function () {
				$scope.point = null;
				$scope.venue = null;
				$scope.county = null;
				$scope.departments = [];
				$scope.fields[0].refresh();
				$scope.fields[1].refresh();
				$scope.fields[2].refresh();
				$scope.fields[3].refresh();
				$scope.fields[4].refresh();
				$scope.mapService.removeAll();
			};

			$scope.isShowClearButton = function () {
				for (var i = 0; i < $scope.fields.length; i++) {
					if ($scope.fields[i].type == 'mapType') {
						continue;
					}
					if ($scope.fields[i].type == 'multiselect') {
						if ($scope.fields[i].value != null && $scope.fields[i].value.length > 0 && $scope.fields[i].value[0].id != -1) {
							return true;
						}
					}
					else if ($scope.fields[i].value != null && $scope.fields[i].value.id != -1) {
						return true;
					}
				}
				return false;
			};

			$scope.filterClick = function () {
				$scope.mapService.removeAll();
				if (angular.isDefined($scope.fields[2].value) && $scope.fields[2].value != null) {
					for (var i = 0; i < $scope.fields[2].value.length; i++) {
						if ($scope.fields[2].value[i].district != undefined) {
							$scope.mapService.addPolygon($scope.fields[2].value[i].district);
						}
					}
				}
				if ($scope.county != null) {
					$scope.mapService.addPolygon($scope.county);
				}
				if ($scope.point != null) {
					$scope.mapService.addPoint($scope.point);
				}
				if ($scope.venue != null) {
					$scope.mapService.addPoint($scope.venue);
				}
				if ($scope.departments && $scope.departments.length > 0) {
					angular.forEach($scope.departments, function (value, key) {
						$scope.mapService.addPoint(value);
					});
				}
				if (angular.isDefined($scope.fields[4].value) && $scope.fields[4].value != null) {
					for (var i = 0; i < $scope.fields[4].value.length; i++) {
						if ($scope.fields[4].value[i].id != -1) {
							if ($scope.fields[4].value[i].id == -2) {
								fillDistrictRoute();
							}
							RoutesService.getRouteTrajectory($scope.fields[4].value[i].id).then(function (trajectories) {
								for (var i = 0; i < trajectories.length; i++) {

									trajectories[i].trajectoryNumber = i;
									function processTrajectory(trajectory) {
										$scope.mapService.addRouteLine(trajectory, null, true);
										var onStops = function (stops) {
											$scope.fields[5].list = stops.slice();
											$scope.addAllToFilter(5);
											if (stops.length > 0) {
												$scope.mapService.addRouteLine(trajectory, stops, false);
											}
											$scope.filterDisable += 1;
										};
										$scope.filterDisable -= 1;
										RoutesService.getRouteStops(trajectories[i].id, trajectories[i].trajectoryTypeId).then(onStops);
									}

									var trajectory = trajectories[i];
									processTrajectory(trajectory);
								}

							});
						}
					}
				}
			};

			function fillDistrictRoute() {
				for (var i = 0; i < $scope.fields[2].value.length; i++) {
					if ($scope.districtRoutes[$scope.fields[2].value[i].id] == undefined) {
						var loadStops = function (filter) {
							RoutesService.getRoutesByDistrict(filter).then(function (response) {
								$scope.districtRoutes[filter[0].value] = response.slice();
								$scope.fillAllDistrictRoute($scope.districtRoutes[filter[0].value]);
								$scope.filterDisable += 1;
							});
						};
						var load = function (filter) {
							angular.element("#requestSpinner").css('visibility', 'visible');
							RoutesService.getRoutesByDistrict(filter).then(function (response) {
								$scope.districtRoutes[filter[0].value] = response.slice();
                                $scope.stopsString = $scope.districtRoutes[filter[0].value];
                                $scope.isPresentStops = $scope.stopsString != null;
								$scope.fillAllDistrictRoute($scope.districtRoutes[filter[0].value]);
								angular.element("#requestSpinner").css('visibility', 'hidden');
								$scope.filterDisable -= 1;
								loadStops([filter[0], filter[1]]);
							});
						};
						load([{
							name: "districtId",
							value: $scope.fields[2].value[i].id,
							type: "id"
						}, {
							name: "forVenue",
							value: 0,
							type: "id"
						}, {
							name: "hideStops",
							value: 1,
							type: "id"
						}]);
					} else {
						$scope.fillAllDistrictRoute($scope.districtRoutes[$scope.fields[2].value[i].id]);
					}
				}
			}

			$scope.fillAllDistrictRoute = function (list) {
				for (var i = 0; i < list.length; i++) {
					$scope.addRoute(list[i], false);
				}
			};

			$scope.addRoute = function (route, isBound) {
				if (route == null || route.routeTrajectories === undefined)
					return;
				for (var i = 0; i < route.routeTrajectories.length; i++) {
					route.routeTrajectories[i].trajectoryNumber = i;
					$scope.mapService.addRouteLine(processTrajectories(route.routeTrajectories[i]), processStops(route.routeStops[route.routeTrajectories[i].trajectoryTypeId]), isBound);
				}
			};

			function processStops(stops) {
				if (stops != null) {
					var results = [];
					for (var i = 0; i < stops.length; i++) {
						if (stops[i] != null && stops[i].wktPointStr != null) {
							results.push({
								id: stops[i].id,
								type: stops[i].type,
								routeId: stops[i].routeId,
								name: stops[i].name,
								routeNames: stops[i].routeNames,
								nameWithoutDistrict: stops[i].nameWithoutDistrict,
								geoJsonPoint: WktParseService.wktToGeoJson(stops[i].wktPointStr)
							});
						}
					}
					return results;
				}
				return null;
			}

			function processTrajectories(trajectories) {
				var result = [];
				result.id = trajectories.id;
				result.routeNumber = trajectories.routeNumber;
				result.geoJsonLine = WktParseService.wktToGeoJson(trajectories.wktLineStr);
				result.trajectoryTypeId = trajectories.trajectoryTypeId;
				result.trajectoryTypeName = trajectories.trajectoryTypeName;
				result.trajectoryNumber = trajectories.trajectoryNumber;
				result.routeType = trajectories.routeType;
				return result;
			}

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init([], $scope.fields, null);

		}]);
