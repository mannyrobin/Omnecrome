angular.module('armada')
	.controller('UnionAnalysisByTripStopCtrl', ['$scope', 'UnionAnalysisByTripStopService', 'GridService', '$state', 'DateTimeService', 'RouteTsKindsService', 'RoutesService', 'AsmppStopsService', 'localStorageService', '$stateParams',
		function ($scope, UnionAnalysisByTripStopService, GridService, $state, DateTimeService, RouteTsKindsService, RoutesService, AsmppStopsService, localStorageService, $stateParams) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = UnionAnalysisByTripStopService;

			if ($stateParams.routeId !== null) {
				localStorageService.set('gridParams_unionAnalysisByTripStopGridTmp', undefined);
			}

			$scope.$watch('gridScope.filters[0].value', function (newValue, oldValue) {
				if (oldValue && newValue && newValue.valueOf() !== oldValue.valueOf()) {
					$scope.gridScope.filters[1].refresh();
					$scope.gridScope.filters[2].refresh();
					$scope.gridScope.filters[3].refresh();
					$scope.gridScope.filters[4].refresh();
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
				id: "orderNum",
				class: "col-md-2"
			}, {
				id: "stopName",
				class: "col-md-2"
			}, {
				id: "asmppPassengersIncludedCount",
				class: "col-md-2"
			}, {
				id: "asmppPassengersLeftCount",
				class: "col-md-2"
			}, {
				id: "asmppPassengersTransportedCount",
				class: "col-md-2"
			}, {
				id: "askpPassengersCount",
				class: "col-md-2"
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
					$scope.gridScope.filters[2].refresh();
					$scope.gridScope.filters[3].refresh();
					$scope.gridScope.filters[4].refresh();
				}
			}, {
				name: "grafic",
				type: "select",
				onChange: function () {
					$scope.gridScope.filters[3].refresh();
					$scope.gridScope.filters[4].refresh();
				},
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
				placeholder: "Рейс",
				value: $stateParams.tripId == null ? null : {id: $stateParams.tripId, name: $stateParams.tripId},
				onChange: function () {
					$scope.gridScope.filters[4].refresh();
				}
			}, {
				name: "tripNum",
				type: "select",
				placeholder: "Порядковый номер",
				load: function () {
					return AsmppStopsService.getTripNumSelectList([{
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
					}, {
						name: "tripId",
						type: "select",
						value: $scope.gridScope.filters[3].value
					}]);
				},
				value: $stateParams.tripNum == null ? null : {id: $stateParams.tripNum, name: $stateParams.tripNum}
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
				defaultPredicate: "tripNum, orderNum, stopName",
				gridName: $stateParams.routeId === null ? $state.current.name + '.unionAnalysisByTripStopGrid' + $stateParams.routeId === null : 'unionAnalysisByTripStopGridTmp',
				load: $scope.gridScope.service.getPage,
				lazyLoad: $stateParams.routeId === null
			});

			$scope.gridScope.hideIndexColumn = true;

		}]);
