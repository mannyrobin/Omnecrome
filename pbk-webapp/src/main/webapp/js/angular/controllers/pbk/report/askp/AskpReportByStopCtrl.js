angular.module('armada')
	.controller('AskpReportByStopCtrl', ['$scope', 'GridService', '$state', 'DateTimeService', 'AskpReportByStopService', 'RouteTsKindsService', 'StopIntervalsStatsService',
		function ($scope, GridService, $state, DateTimeService, AskpReportByStopService, RouteTsKindsService, StopIntervalsStatsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = AskpReportByStopService;

			/* Права */
			$scope.gridScope.perms = {};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Пассажиропоток по остановке"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "routeNumber",
				label: "Маршрут",
				class: "col-md-3"
			}, {
				id: "ticketCount",
				label: "АСКП",
				class: "col-md-3"
			}, {
				id: "ticketCountPaid",
				label: "Платные",
				class: "col-md-3"
			}, {
				id: "ticketCountFree",
				label: "Бесплатные",
				class: "col-md-3"
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
				name: "stopIds",
				type: "multiselect",
				lazyload: function (query) {
					return StopIntervalsStatsService.getStopSelectList([{
						name: "stopName",
						value: query,
						type: "text"
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
				placeholder: "Остановки",
				required: false
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "stopId",
				gridName: $state.current.name + '.askpReportByStop',
				load: $scope.gridScope.service.getPage,
				lazyLoad: true
			});

		}]);
