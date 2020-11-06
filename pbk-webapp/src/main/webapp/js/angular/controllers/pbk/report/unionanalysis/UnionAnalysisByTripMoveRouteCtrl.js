angular.module('armada')
	.controller('UnionAnalysisByTripMoveRouteCtrl', ['$scope', 'UnionAnalysisByTripMoveRouteService', 'GridService', '$state', 'DateTimeService', 'RouteTsKindsService', 'RoutesService', 'AsmppStopsService', 'localStorageService', '$stateParams',
		function ($scope, UnionAnalysisByTripMoveRouteService, GridService, $state, DateTimeService, RouteTsKindsService, RoutesService, AsmppStopsService, localStorageService, $stateParams) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = UnionAnalysisByTripMoveRouteService;

			if ($stateParams.routeId !== null) {
				localStorageService.set('gridParams_unionAnalysisByTripMoveRouteTmp', undefined);
			}

			$scope.$watch('gridScope.filters[0].value', function (newValue, oldValue) {
				if (oldValue && newValue && newValue.valueOf() !== oldValue.valueOf()) {
					$scope.gridScope.filters[1].refresh();
					$scope.gridScope.filters[2].refresh();
					$scope.gridScope.filters[3].refresh();
				}
			});

			/* Права */
			$scope.gridScope.perms = {};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Аналитика по рейсам маршрута-выхода"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "tripId",
				class: "col-md-1",
				get: function (row, rowId) {
					return rowId === 0 ? row.tripId :
						$scope.gridScope.grid.rows[rowId - 1].tripId === row.tripId ? '' : row.tripId;
				}
			}, {
				id: "tripNum",
				class: "col-md-1",
				hrefClick: function (row) {
					$state.go('app.main.report.unionanalysisondate.trip-stops', {
						workDate: $scope.gridScope.filters[0].value
						, routeId: $stateParams.routeId
						, grafic: row.grafic
						, routeName: $stateParams.routeName
						, tripId: row.tripId
						, tripNum: row.tripNum
					});
				}
			}, {
				id: "startTripTime",
				class: "col-md-1"
			}, {
				id: "asmppPassengersIncludedCount",
				class: "col-md-1"
			}, {
				id: "asmppPassengersLeftCount",
				class: "col-md-1"
			}, {
				id: "asmppPassengersTransportedCount",
				class: "col-md-1"
			}, {
				id: "askpPassengersCount",
				class: "col-md-1"
			}];

			var now = new Date();

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "workDate",
				defval: $stateParams.workDate === null ? now : $stateParams.workDate,
				type: "date",
				placeholder: "Дата"
			}, {
				name: "routeId",
				type: "select",
				load: function (query) {
					return AsmppStopsService.getRouteSelectList([{
						name: "dateFrom",
						value: $scope.gridScope.filters[0].value,
						type: "date"
					}, {
						name: "dateTo",
						value: $scope.gridScope.filters[0].value,
						type: "date"
					}]);
				},
				value: $stateParams.routeName == null ? null : {id: $stateParams.routeId, name: $stateParams.routeName},
				placeholder: "Маршрут",
				required: false,
				onChange: function () {
					$scope.filters[2].refresh();
					$scope.filters[3].refresh();
				}
			}, {
				name: "grafic",
				type: "select",
				load: function () {
					return AsmppStopsService.getRouteMoveSelectList([{
						name: "asduRouteId",
						type: "select",
						value: $scope.gridScope.filters[1].value
					}, {
						name: "dateFrom",
						value: $scope.gridScope.filters[0].value,
						type: "date"
					}, {
						name: "dateTo",
						value: $scope.gridScope.filters[0].value,
						type: "date"
					}]);
				},
				value: $stateParams.grafic == null ? null : {id: $stateParams.grafic, name: $stateParams.grafic},
				placeholder: "Выход",
				onChange: function () {
					$scope.filters[3].refresh();
				}
			}, {
				name: "tripId",
				type: "select",
				load: function () {
					return AsmppStopsService.getTripSelectList([{
						name: "routeId",
						type: "select",
						value: $scope.gridScope.filters[1].value
					}, {
						name: "dateFrom",
						value: $scope.gridScope.filters[0].value,
						type: "date"
					}, {
						name: "dateTo",
						value: $scope.gridScope.filters[0].value,
						type: "date"
					}, {
						name: "grafic",
						type: "select",
						value: $scope.gridScope.filters[2].value
					}]);
				},
				placeholder: "Рейс"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти записи",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "asduRouteId, grafic, tripId, tripNum",
				gridName: $stateParams.routeId === null ? $state.current.name + '.unionAnalysisByTripMoveRoute' + $stateParams.routeId === null : 'unionAnalysisByTripMoveRouteTmp',
				load: $scope.gridScope.service.getPage,
				lazyLoad: $stateParams.routeId === null
			});

			$scope.gridScope.hideIndexColumn = true;

		}]);
