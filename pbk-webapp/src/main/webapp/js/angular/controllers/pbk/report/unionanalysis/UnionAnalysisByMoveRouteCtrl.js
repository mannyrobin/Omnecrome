angular.module('armada')
	.controller('UnionAnalysisByMoveRouteCtrl', ['$scope', 'UnionAnalysisByMoveRouteService', 'GridService', '$state', 'DateTimeService', 'RouteTsKindsService', 'AsmppStopsService',
		function ($scope, UnionAnalysisByMoveRouteService, GridService, $state, DateTimeService, RouteTsKindsService, AsmppStopsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = UnionAnalysisByMoveRouteService;

			$scope.$watch('gridScope.filters[0].value', function (newValue, oldValue) {
				if (oldValue && newValue && newValue.valueOf() !== oldValue.valueOf()) {
					$scope.gridScope.filters[2].refresh();
				}
			});

			$scope.$watch('gridScope.filters[1].value', function (newValue, oldValue) {
				if (oldValue && newValue && (newValue !== oldValue)) {
					$scope.gridScope.filters[2].refresh();
				}
			});

			/* Права */
			$scope.gridScope.perms = {};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Аналитика по маршрутам и выходам"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "routeName",
				class: "col-md-1",
				get: function (row, rowId) {
					return rowId === 0 ? row.routeName :
						$scope.gridScope.grid.rows[rowId - 1].routeName === row.routeName ? '' : row.routeName;
				}
			}, {
				id: "grafic",
				class: "col-md-1",
				hrefClick: function (row) {
					$state.go('app.main.report.unionanalysisondate.trip-move-routes', {
						workDate: $scope.gridScope.filters[0].value
						, routeId: row.asduRouteId
						, grafic: row.grafic
						, routeName: row.routeFilterName
					});
				}
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
				defval: now,
				type: "date",
				placeholder: "Дата"
			}, {
				name: "tsType",
				type: "multiselect",
				placeholder: "Тип ТС",
				load: RouteTsKindsService.getList
			}, {
				name: "routes",
				type: "multiselect",
				load: function (query) {
					return AsmppStopsService.getRouteSelectList([{
						name: "tsType",
						value: $scope.gridScope.filters[1].value,
						type: "multiselect"
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
				lazymin: 1,
				placeholder: "Маршруты",
				required: false
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
				defaultPredicate: "routeName, grafic",
				gridName: $state.current.name + '.soUnionAnalysisByRouteMove',
				load: $scope.gridScope.service.getPage,
				lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;

		}]);
