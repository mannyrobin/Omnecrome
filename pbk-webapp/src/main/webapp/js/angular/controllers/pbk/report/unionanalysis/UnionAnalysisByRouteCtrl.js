angular.module('armada')
	.controller('UnionAnalysisByRouteCtrl', ['$scope', 'UnionAnalysisByRouteService', 'GridService', '$state', 'DateTimeService', 'RouteTsKindsService', 'RoutesService', 'AsmppStopsService',
		function ($scope, UnionAnalysisByRouteService, GridService, $state, DateTimeService, RouteTsKindsService, RoutesService, AsmppStopsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = UnionAnalysisByRouteService;

			$scope.$watch('gridScope.filters[0].value', function (newValue, oldValue) {
				if (oldValue && newValue && oldValue.toDate && oldValue.fromDate && newValue.toDate && newValue.fromDate && (newValue.fromDate.valueOf() !== oldValue.fromDate.valueOf()
					|| newValue.toDate.valueOf() !== oldValue.toDate.valueOf())) {
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
					title: "Аналитика по маршрутам"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "criterion",
				label: "Показатели",
				class: "col-md-10"
			}, {
				id: "curPeriod",
				label: "Текущий период",
				class: "col-md-1"
			}, {
				id: "prevPeriod",
				label: "Прошлый период",
				class: "col-md-1"
			}];

			/* Колонки грида */
			$scope.gridScope.showHeaders = [{
				id: "routeName",
				class: "col-md-1",
				hrefClick: function (row) {
					$state.go('app.main.report.unionanalysis.stops', {
						fromDate: $scope.gridScope.filters[0].value.fromDate
						, toDate: $scope.gridScope.filters[0].value.toDate
						, routeId: row.routeId
						, routeName: row.routeFilterName
					});
				}
			}, {
				id: "workdayAsmppPassengersIncludedCount",
				class: "col-md-1"
			}, {
				id: "workdayAsmppPassengersLeftCount",
				class: "col-md-1"
			}, {
				id: "workdayAsmppPassengersTransportedCount",
				class: "col-md-1"
			}, {
				id: "workdayAskpPassengersCount",
				class: "col-md-1"
			}, {
				id: "holidayAsmppPassengersIncludedCount",
				class: "col-md-1"
			}, {
				id: "holidayAsmppPassengersLeftCount",
				class: "col-md-1"
			}, {
				id: "holidayAsmppPassengersTransportedCount",
				class: "col-md-1"
			}, {
				id: "holidayAskpPassengersCount",
				class: "col-md-1"
			}];

			var now = new Date();

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: {
					fromName: "dateFrom",
					toName: "dateTo"
				},
				defval: {
					fromDate: DateTimeService.addDays(now, -7),
					toDate: now
				},
				type: "range",
				placeholder: {
					from: "Начало периода",
					to: "Окончание периода"
				}
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
						value: $scope.gridScope.filters[0].value.fromDate,
						type: "date"
					}, {
						name: "dateTo",
						value: $scope.gridScope.filters[0].value.toDate,
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
				defaultPredicate: "routeName",
				gridName: $state.current.name + '.soUnionAnalysisByRoute',
				load: $scope.gridScope.service.getPage,
				lazyLoad: true
			});

			$scope.gridScope.hideIndexColumn = true;

			$scope.isNull = function (value) {
				return value ? value : 0;
			}

		}]);
