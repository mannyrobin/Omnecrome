angular.module('armada')
	.controller('UnionAnalysisByStopCtrl', ['$scope', 'UnionAnalysisByStopService', 'GridService', '$state', 'DateTimeService', 'RoutesService', 'StopIntervalsStatsService', '$stateParams', 'localStorageService', 'AsmppStopsService',
		function ($scope, UnionAnalysisByStopService, GridService, $state, DateTimeService, RoutesService, StopIntervalsStatsService, $stateParams, localStorageService, AsmppStopsService) {

			if ($stateParams.routeId !== null) {
				localStorageService.set('gridParams_unionAnalysisByStopsTmp', undefined);
			}

			$scope.gridScope = $scope;
			$scope.gridScope.service = UnionAnalysisByStopService;

			/* Права */
			$scope.gridScope.perms = {};

			$scope.$watch('gridScope.filters[0].value', function (newValue, oldValue) {
				if (oldValue && newValue && oldValue.toDate && oldValue.fromDate && newValue.toDate && newValue.fromDate && (newValue.fromDate.valueOf() !== oldValue.fromDate.valueOf()
					|| newValue.toDate.valueOf() !== oldValue.toDate.valueOf())) {
					$scope.gridScope.filters[1].refresh();
				}
			});

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
				id: "routeVariant",
				class: "col-md-1",
				get: function (row, rowId) {
					return rowId === 0 ? row.routeVariant :
						$scope.gridScope.grid.rows[rowId - 1].routeVariant === row.routeVariant ? '' : row.routeVariant;
				}
			}, {
				id: "direction",
				class: "col-md-1",
				get: function (row, rowId) {
					if (rowId !== 0 && $scope.gridScope.grid.rows[rowId - 1].routeVariant === row.routeVariant
						&& $scope.gridScope.grid.rows[rowId - 1].direction === row.direction)
						return '';
					return row.direction === 'A' ? 'Прямое' : row.direction === 'B' ? 'Обратное' : '';
				}
			}, {
				id: "orderNum",
				class: "col-md-1",
				get: function (row) {
					return row.orderNum;
				}
			}, {
				id: "stopName",
				class: "col-md-1",
				get: function (row) {
					return row.stopName;
				}
			}, {
				id: "workday.asmppPassengersIncludedCount",
				class: "col-md-1",
				get: function (row) {
					return row.workday && row.workday.asmppPassengersIncludedCount ? row.workday.asmppPassengersIncludedCount : 0;
				}
			}, {
				id: "workday.asmppPassengersLeftCount",
				class: "col-md-1",
				get: function (row) {
					return row.workday && row.workday.asmppPassengersLeftCount ? row.workday.asmppPassengersLeftCount : 0;
				}
			}, {
				id: "workday.asmppPassengersTransportedCount",
				class: "col-md-1",
				get: function (row) {
					return row.workday && row.workday.asmppPassengersTransportedCount ? row.workday.asmppPassengersTransportedCount : 0;
				}
			}, {
				id: "workday.askpPassengersCount",
				class: "col-md-1",
				get: function (row) {
					return row.workday && row.workday.askpPassengersCount ? row.workday.askpPassengersCount : 0;
				}
			}, {
				id: "holiday.asmppPassengersIncludedCount",
				class: "col-md-1",
				get: function (row) {
					return row.holiday && row.holiday.asmppPassengersIncludedCount ? row.holiday.asmppPassengersIncludedCount : 0;
				}
			}, {
				id: "holiday.asmppPassengersLeftCount",
				class: "col-md-1",
				get: function (row) {
					return row.holiday && row.holiday.asmppPassengersLeftCount ? row.holiday.asmppPassengersLeftCount : 0;
				}
			}, {
				id: "holiday.asmppPassengersTransportedCount",
				class: "col-md-1",
				get: function (row) {
					return row.holiday && row.holiday.asmppPassengersTransportedCount ? row.holiday.asmppPassengersTransportedCount : 0;
				}
			}, {
				id: "holiday.askpPassengersCount",
				class: "col-md-1",
				get: function (row) {
					return row.holiday && row.holiday.askpPassengersCount ? row.holiday.askpPassengersCount : 0;
				}
			}];

			$scope.getCellValue = function (row, id) {

			}

			var now = new Date();

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: {
					fromName: "dateFrom",
					toName: "dateTo"
				},
				defval: {
					fromDate: $stateParams.fromDate == null ? DateTimeService.addDays(now, -7) : $stateParams.fromDate,
					toDate: $stateParams.toDate == null ? now : $stateParams.toDate
				},
				type: "range",
				placeholder: {
					from: "Начало периода",
					to: "Окончание периода"
				}
			}, {
				name: "routeId",
				type: "select",
				load: function (query) {
					return AsmppStopsService.getRouteSelectList([{
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
				placeholder: "Маршрут",
				required: false,
				onChange: function () {
					$scope.filters[2].refresh();
					$scope.filters[3].refresh();
				},
				value: $stateParams.routeName == null ? null : {id: $stateParams.routeId, name: $stateParams.routeName}
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
						value: $scope.gridScope.filters[0].value.fromDate,
						type: "date"
					}, {
						name: "dateTo",
						value: $scope.gridScope.filters[0].value.toDate,
						type: "date"
					}]);
				},
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
						value: $scope.gridScope.filters[0].value.fromDate,
						type: "date"
					}, {
						name: "dateTo",
						value: $scope.gridScope.filters[0].value.toDate,
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
				defaultPredicate: "routeId",
				gridName: $stateParams.routeId === null ? $state.current.name + '.unionAnalysisByStops' + $stateParams.routeId === null : 'unionAnalysisByStopsTmp',
				load: $scope.gridScope.service.getPage,
				lazyLoad: $stateParams.routeId === null
			});

			$scope.gridScope.hideIndexColumn = true;

		}]);
