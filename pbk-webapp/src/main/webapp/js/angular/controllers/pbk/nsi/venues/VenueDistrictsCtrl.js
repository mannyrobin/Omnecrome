angular.module('armada')
	.controller('VenueDistrictsCtrl', ['$controller', '$scope', '$state', '$stateParams', '$modal', 'ArMapService', 'RoutesService',
		'DepartmentsService', 'CountiesService', 'DistrictsService', 'VenuesService', 'StopsService', 'WktParseService', 'VenueDistrictsService', 'isNotReadOnlyUser', 'venue', 'GridService', 'Notification', 'UtilService', 'VenueRouteTypesService',
		function ($controller, $scope, $state, $stateParams, $modal, ArMapService, RoutesService,
				  DepartmentsService, CountiesService, DistrictsService, VenuesService, StopsService, WktParseService, VenueDistrictsService, isNotReadOnlyUser, venue, GridService, Notification, UtilService, VenueRouteTypesService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = VenueDistrictsService;
			$scope.readonly = !isNotReadOnlyUser || venue.isDelete == 1;

			/* Права */
			$scope.gridScope.perms = {
				add: !$scope.readonly,
				remove: !$scope.readonly
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Районы"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "versionStartDate",
				label: "Период актуальности",
				class: "col-md-2",
				get: function (row) {
					return row.versionStartDate + ' - ' + row.versionEndDate
				}
			}, {
				id: "districtName",
				label: "Район",
				class: "col-md-2"
			}, {
				id: "countyName",
				label: "Округ",
				class: "col-md-2"
			}, {
				id: "createUser",
				label: "Кто и когда создал",
				class: "col-md-3",
				get: function (row) {
					return row.createUser + ' (' + row.createDate + ')'
				}
			}, {
				id: "updateUser",
				label: "Кто и когда изменил",
				class: "col-md-3",
				get: function (row) {
					return row.updateUser + ' (' + row.updateDate + ')'
				}
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "venueHeadId",
				type: "text",
				defval: $stateParams.itemId,
				hide: true
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить район",
					removeConfirm: "Вы действительно хотите удалить район?"
				},
				check: {
					isRemoveEnable: function (row) {
						return row['active'] != undefined && row.active;
					}
				},
				gridScope: $scope.gridScope
			};

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.VenueDistrictsGrid',
				load: $scope.gridScope.service.getPage
			});

			$scope.gridScope.grid.infoTemplate = "templates/views/pbk/nsi/venues/venueRoutes.html";

			$scope.gridScope.showAdditionalInfo = function (row, show) {
				row.isNotReadOnlyUser = !$scope.readonly;
				row.clearMainMap = function () {
					$scope.clearAll();
				}
			};

			/* Экшены */
			$scope.gridScope.addRow = function () {
				var modalInstance = $modal.open({
					templateUrl: "templates/dialogs/pbk/nsi/venues/venueDistrictAddDlg.html",
					windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
					controller: "VenueDistrictAddCtrl",
					size: "md",
					resolve: {
						data: function () {
							return {
								title: "Добавление района",
								id: null
							}
						}
					}
				});
				modalInstance.result.then(function () {
					$scope.gridScope.grid.refreshAction();
					$scope.clearAll();
				})
			};

			$scope.gridScope.removeRow = function (item) {
				var removeObj = {ids: item.id, tryDelete: true};
				VenuesService.removeDistrict(removeObj).then(function () {
					$scope.gridScope.grid.refreshAction();
					$scope.clearAll();
					Notification.info("Запись успешно удалена.");
				}).catch(function (response) {
					var errMsg = "";
					if (response.status == 400) {
						var errors = UtilService.getFormErrors(response);
						errMsg = " Причина:" + errors.errMessages;
					}
					Notification.error("Удалить не удалось" + errMsg);
				});
			};

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return !row.active ? 'history-record' : null;
			};
////////////////////////////////////////////////////////////////////////////////////////////
			$scope.point = null;
			$scope.venue = null;
			$scope.department = null;
			$scope.all = {id: -1, name: "Не выбрано"};
			$scope.allRouteTypes = {id: -1, name: "Все"};
			$scope.districtRoutes = {};

			$scope.mapServiceYa = ArMapService.initService({mapType: "ya"});
			$scope.mapServiceOther = ArMapService.initService({mapType: "other"});
			$scope.isMapYa = true;
			$scope.isMapOther = false;
			$scope.mapService = $scope.mapServiceYa;
			$scope.filterDisable = 0;

			$scope.$watch('fields[4].value', function () {
				if ($scope.fields[3].value != null && ($scope.fields[3].value.length == 2 && $scope.fields[3].value[0].id == -1 && $scope.fields[3].value[1].id > 0 || $scope.fields[3].value.length == 1 && $scope.fields[3].value[0].id > 0)) {
					RoutesService.getRouteTrajectory($scope.fields[3].value[0].id > 0 ? $scope.fields[3].value[0].id : $scope.fields[3].value[1].id).then(function (trajectories) {
						for (var i = 0; i < trajectories.length; i++) {
							function processTrajectory(trajectory) {
								var onStops = function (stops) {
									$scope.fields[4].list = stops.slice();
									$scope.addAllToFilter(4);
								};
								RoutesService.getRouteStops(trajectories[i].id, trajectories[i].trajectoryTypeId).then(onStops);
							}

							var trajectory = trajectories[i];
							processTrajectory(trajectory);
						}
					});
				}
			});

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

			$scope.$watch('fields[3].value', function () {
				changeMultiFilter(3);
				$scope.fields[4].list = [];
				$scope.fields[4].value = null;
				$scope.addAllToFilter(4);
				$scope.point = null;
			});

			/* Поля ввода */
			$scope.fields = [{
				name: "departmenId",
				type: "select",
				load: function () {
					return DepartmentsService.getSelectItemForMap([
						{
							name: "venueId",
							value: $stateParams.itemId,
							type: "id"
						}
					]);
				},
				label: "МОК",
				required: false,
				onChange: function () {
					$scope.department = null;
					if ($scope.fields[0].value.id != -1) {
						if ($scope.fields[0].value.geoJsonPoint == undefined) {
							DepartmentsService.getItem($scope.fields[0].value.id).then(function (response) {
								if (response.longitude != null && response.latitude != null) {
									$scope.fields[0].value.geoJsonPoint = WktParseService.wktToGeoJson('POINT(' + response.longitude + ' ' + response.latitude + ')');
									$scope.fields[0].value.name = response.name;
									$scope.fields[0].value.type = "department";
									$scope.department = $scope.fields[0].value;
								}
							});
						} else {
							$scope.department = $scope.fields[0].value;
						}
					}
				},
				afterLoad: function () {
					$scope.addAllToFilter(0);
					$scope.department = null;
				}
			}, {
				name: "districtId",
				type: "multiselect",
				label: "Район",
				load: function () {
					return VenueDistrictsService.getSelectDistricts([{
						name: "venueId",
						value: $stateParams.itemId,
						type: "id"
					}
					]);
				},
				required: false,
				onChange: function () {
					changeMultiFilter(1);
					$scope.fields[3].refresh();
					if ($scope.fields[1].value.length > 0 && $scope.fields[1].value[$scope.fields[1].value.length - 1].id != -1 && $scope.fields[1].value[$scope.fields[1].value.length - 1].district == null) {
						DistrictsService.getEgko($scope.fields[1].value[$scope.fields[1].value.length - 1].id).then(function (polygon) {
							$scope.fields[1].value[$scope.fields[1].value.length - 1].district = polygon;
						});
					}
				},
				afterLoad: function () {
					changeMultiFilter(1);
				}
			}, {
				name: "venueName",
				type: "text",
				label: "Место встречи",
				required: false,
				readonly: true
			}, {
				name: "routeId",
				type: "multiselect",
				label: "Номер маршрута",
				load: function () {
					if ($scope.fields[1].value == null && $scope.fields[2].value == null) {
						return RoutesService.getList([{
							name: "districtInId",
							value: -2,
							type: "multiselect"
						}, {
							name: "venueInId",
							value: -2,
							type: "id"
						}]);
					} else {
						return VenueDistrictsService.getSelectRoutes([{
							name: "districtIds",
							value: $scope.fields[1].value == null ? -1 : $scope.fields[1].value,
							type: "multiselect"
						}, {
							name: "venueId",
							value: $stateParams.itemId,
							type: "id"
						}, {
							name: "venueRouteTypeId",
							value: $scope.fields[6].value == null || $scope.fields[6].value.id == -1 ? null : $scope.fields[6].value,
							type: "select"
						}])
					}
					;
				},
				lazymin: 1,
				required: false,
				lazyload: function (query) {
					return RoutesService.getLazySelectItemsForVenues([{
						name: "name",
						value: query,
						type: "text"
					}, {
						name: "venueId",
						value: $stateParams.itemId,
						type: "id"
					}]);
				}, isLazyload: function () {
					return $scope.fields[4].list == undefined || $scope.fields[4].list.length == 0 ||
						($scope.fields[4].list.length == 1 && $scope.fields[4].list[0].id == -1);
				},
				afterLoad: function () {
					changeMultiFilter(3);
					$scope.fields[4].list = [];
					$scope.fields[4].value = null;
					$scope.addAllToFilter(4);
					$scope.point = null;
					if ($scope.fields[1].value != null && $scope.fields[1].value.length > 0 && $scope.fields[1].value[0].id != -1) {
						$scope.fields[3].list.unshift({id: -2, name: "Все маршруты района"});
					}
				}
			}, {
				name: "stopId",
				type: "select",
				label: "Наименование остановочного пункта",
				required: false,
				onChange: function () {
					if ($scope.fields[4].value != null) {
						if ($scope.fields[4].value.id != -1) {
							if ($scope.fields[4].value.geoJsonPoint == undefined) {
								StopsService.getItem($scope.fields[4].value.id).then(function (response) {
									if (response != null && response.longitude != null && response.latitude != null) {
										$scope.fields[4].value.geoJsonPoint = WktParseService.wktToGeoJson('POINT(' + response.longitude + ' ' + response.latitude + ')');
										$scope.fields[4].value.name = response.name;
										$scope.fields[4].value.type = 'stop';
										$scope.fields[4].value.routeNames = response.routeNames != null ? response.routeNames : '';
										$scope.fields[4].value.nameWithoutDistrict = response.nameWithoutDistrict;
										$scope.point = $scope.fields[4].value;
									}
								});
							} else {
								$scope.point = $scope.fields[4].value;
							}
						}
					}
				},
				afterLoad: function () {
					$scope.point = null;
					$scope.addAllToFilter(4);
				},
				lazyload: function (query) {
					return VenueDistrictsService.getSelectStops([{
						name: "stopName",
						value: query,
						type: "text"
					}, {
						name: "venueId",
						value: $stateParams.itemId,
						type: "id"
					}]);
				}, isLazyload: function () {
					return $scope.fields[4].list == undefined || $scope.fields[4].list.length == 0 ||
						($scope.fields[4].list.length == 1 && $scope.fields[4].list[0].id == -1);
				}
			}, {
				name: "mapType",
				type: "mapType",
				label: "Тип карты",
				required: false,
				onChange: function () {
					$scope.isMapYa = $scope.fields[5].value.id == 0;
					$scope.isMapOther = $scope.fields[5].value.id == 1;
					$scope.mapService = $scope.fields[5].value.id == 1 ? $scope.mapServiceOther : $scope.mapServiceYa;
					$scope.mapService.removeAll();
					if ($scope.venue != null) {
						$scope.mapService.addPoint($scope.venue);
					}
				}
			}, {
				name: "venueRouteTypeId",
				type: "select",
				label: "Тип маршрута",
				required: false,
				load: VenueRouteTypesService.getList,
				afterLoad: function () {
					$scope.fields[6].list.unshift($scope.allRouteTypes);
					if ($scope.fields[6].value == null) {
						$scope.fields[6].value = $scope.allRouteTypes;
					}
				},
				onChange: function () {
					$scope.fields[3].refresh();
				}
			}];

			$scope.onLoadMap = function () {
				VenuesService.getItem($stateParams.itemId).then(function (response) {
					if (response != null && response.longitude != null && response.latitude != null) {
						$scope.venue = {};
						$scope.venue.geoJsonPoint = WktParseService.wktToGeoJson('POINT(' + response.longitude + ' ' + response.latitude + ')');
						$scope.venue.name = response.name;
						$scope.venue.type = "venue";
						$scope.venue.id = $stateParams.itemId;
						$scope.fields[2].value = $scope.venue.name;
						$scope.mapService.addPoint($scope.venue);
					}
				});
			};

			$scope.clearAll = function () {
				$scope.point = null;
				$scope.fields[1].refresh();
				$scope.fields[3].refresh();
				$scope.fields[6].refresh();
				$scope.mapService.removeAll();
				if ($scope.venue != null) {
					$scope.mapService.addPoint($scope.venue);
				}
			};

			$scope.refreshMap = function () {
				$scope.point = null;
				$scope.fields[1].refresh();
				$scope.fields[3].refresh();
				$scope.fields[6].refresh();
			};

			$scope.isShowClearButton = function () {
				for (var i = 0; i < $scope.fields.length; i++) {
					if (i == 1 || $scope.fields[i].type == 'mapType') {
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
				if (angular.isDefined($scope.fields[1].value) && $scope.fields[1].value != null) {
					for (var i = 0; i < $scope.fields[1].value.length; i++) {
						if ($scope.fields[1].value[i].district != undefined) {
							$scope.mapService.addPolygon($scope.fields[1].value[i].district);
						}
					}
				}
				if ($scope.department != null) {
					$scope.mapService.addPoint($scope.department);
				}
				if ($scope.point != null) {
					$scope.mapService.addPoint($scope.point);
				}
				if ($scope.venue != null) {
					$scope.mapService.addPoint($scope.venue);
				}
				if (angular.isDefined($scope.fields[3].value) && $scope.fields[3].value != null) {
					for (var i = 0; i < $scope.fields[3].value.length; i++) {
						if ($scope.fields[3].value[i].id != -1) {
							if ($scope.fields[3].value[i].id == -2) {
								fillDistrictRoute();
							}
							RoutesService.getRouteTrajectory($scope.fields[3].value[i].id).then(function (trajectories) {
								for (var i = 0; i < trajectories.length; i++) {
									trajectories[i].trajectoryNumber = i;
									function processTrajectory(trajectory) {
										$scope.mapService.addRouteLine(trajectory, null, true);
										var onStops = function (stops) {
											$scope.fields[4].list = stops.slice();
											$scope.addAllToFilter(4);
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
				for (var i = 0; i < $scope.fields[1].value.length; i++) {
					var loadStops = function (filter) {
						RoutesService.getRoutesByDistrict(filter).then(function (response) {
							$scope.districtRoutes[filter[1].value] = response.slice();
							$scope.fillAllDistrictRoute($scope.districtRoutes[filter[1].value]);
							$scope.filterDisable += 1;
						});
					};
					var load = function (filter) {
						angular.element("#requestSpinner").css('visibility', 'visible');
						RoutesService.getRoutesByDistrict(filter).then(function (response) {
							$scope.districtRoutes[filter[1].value] = response.slice();
							$scope.fillAllDistrictRoute($scope.districtRoutes[filter[1].value]);
							angular.element("#requestSpinner").css('visibility', 'hidden');
							$scope.filterDisable -= 1;
							loadStops([filter[0], filter[1], filter[2], filter[3]]);
						});
					};
					load([{
						name: "districtId",
						value: $scope.fields[1].value[i].id,
						type: "id"
					}, {
						name: "venueId",
						value: $scope.venue.id,
						type: "id"
					}, {
						name: "venueRouteTypeId",
						value: $scope.fields[6].value == null || $scope.fields[6].value.id == -1 ? null : $scope.fields[6].value,
						type: "select"
					}, {
						name: "forVenue",
						value: 1,
						type: "id"
					}, {
						name: "hideStops",
						value: 1,
						type: "id"
					}]);
				}
			}

			$scope.fillAllDistrictRoute = function (list) {
				for (var i = 0; i < list.length; i++) {
					$scope.addRoute(list[i], false);
				}
			};

			$scope.addRoute = function (route, isBound) {
				if (route == null)
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