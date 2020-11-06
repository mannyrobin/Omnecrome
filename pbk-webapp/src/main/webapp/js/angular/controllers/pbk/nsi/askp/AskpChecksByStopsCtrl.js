angular.module('armada')
	.controller('AskpChecksByStopsCtrl', ['$scope', 'AskpChecksByStopsService', 'GridService', '$state', 'canExport', 'DepartmentsService', 'RouteTsKindsService', 'RoutesService', '$timeout', '$stateParams', 'Notification', 'UtilService', 'AskpByStopReportService', 'StopIntervalsStatsService',
		function ($scope, AskpChecksByStopsService, GridService, $state, canExport, DepartmentsService, RouteTsKindsService, RoutesService, $timeout, $stateParams, Notification, UtilService, AskpByStopReportService, StopIntervalsStatsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = AskpChecksByStopsService;

			/* Права */
			$scope.gridScope.perms = {
				export: canExport
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Количество перевезенных пассажиров поостановочно"
				},
				gridScope: $scope.gridScope
			};
			$scope.gridScope.caption = "Для формирования отчета необходимо выбрать маршрут и выход";

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "tripNum",
				label: "Вариант маршрута",
				disableSort: true
			}, {
				id: "orderNum",
				label: "№",
				disableSort: true
			}, {
				id: "stopName",
				label: "Остановка",
				style: "min-width: 200px;",
				disableSort: true
			}, {
				id: "sum",
				label: "Общая сумма",
				disableSort: true
			}, {
				id: "sumPaid",
				label: "Платные",
				disableSort: true
			}];

			var now = new Date();

			$scope.$watch('gridScope.filters[0].value', function (newValue, oldValue) {
				if (newValue.valueOf() !== oldValue.valueOf()) {
					$scope.filters[1].refresh();
					$scope.filters[4].refresh();
				}
			});

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "date",
				type: "date",
				placeholder: "Дата",
				defval: now,
				value: $stateParams.date == null ? now : $stateParams.date
			}, /* {
			 name: "isWorkDate",
			 type: "yesno",
			 placeholder: "Рабочий день"
			 }, {
			 name: "departmentId",
			 type: "multiselect",
			 placeholder: "Подразделение",
			 load: function() {
			 return DepartmentsService.getList([{
			 name: "forPlanUse",
			 value: 1,
			 type: "id"
			 }]);
			 }
			 },*/ {
				name: "parkId",
				type: "select",
				placeholder: "Парк",
				load: function () {
					return StopIntervalsStatsService.getParkSelectList([{
						name: "dateFrom",
						value: $scope.gridScope.filters[0].value,
						type: "date"
					}, {
						name: "dateTo",
						value: $scope.gridScope.filters[0].value,
						type: "date"
					}])
				}
			}, {
				name: "tsType",
				type: "multiselect",
				placeholder: "Тип ТС",
				load: RouteTsKindsService.getList
			}, {
				name: "route",
				type: "select",
				lazyload: function (query) {
					return RoutesService.getList([{
						name: "name",
						value: query,
						type: "text"
					}, {
						name: "tsType",
						value: $scope.gridScope.filters[2].value,
						type: "multiselect"
					}, {
						name: "parkId",
						type: "select",
						value: $scope.gridScope.filters[1].value
					}]);
				},
				lazymin: 1,
				placeholder: "Маршруты",
				required: false,
				onChange: function () {
					$scope.filters[4].refresh();
				},
				value: $stateParams.routeId == null || $stateParams.routeName == null ? null : {
					id: $stateParams.routeId,
					name: $stateParams.routeName
				}
			}, {
				name: "moveCode",
				type: "select",
				load: function () {
					return StopIntervalsStatsService.getRouteMoveSelectList([{
						name: "date",
						type: "date",
						value: $scope.gridScope.filters[0].value
					}, {
						name: "routeId",
						type: "select",
						value: $scope.gridScope.filters[3].value
					}]);
				},
				placeholder: "Выход",
				value: $stateParams.moveCode == null ? null : {
					id: parseInt($stateParams.moveCode),
					name: $stateParams.moveCode
				}
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

			$scope.getExtHeader = function (item) {
				var from = item % 24;
				if (from < 10) {
					from = '0' + from;
				}
				var to = (item + 1) % 24;
				if (to < 10) {
					to = '0' + to;
				}
				return {
					id: item,
					label: from + '-' + to
				};
			};

			/* Грид */
			$scope.gridScope.extheaders = [];
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "orderNum",
				gridName: $state.current.name + '.AskpChecksByStops' + $stateParams.date + $stateParams.routeId + $stateParams.moveCode,
				load: $scope.gridScope.service.getPage,
				lazyLoad: $stateParams.routeId == null,
				afterLoad: function () {
					$scope.gridScope.extheaders = [];
					if ($scope.gridScope.grid.rows && $scope.gridScope.grid.rows.length > 0) {
						for (var i = 0; i < $scope.gridScope.grid.rows[0].hours.length; ++i) {
							$scope.gridScope.extheaders.push($scope.getExtHeader($scope.gridScope.grid.rows[0].hours[i]));
						}
					}
				},
				errorLoadFunc: function (response) {
					var errMsg = "";
					if (response.status == 400) {
						var errors = UtilService.getAllResponseErrors(response.data);
						errMsg += errors;
					}
					Notification.error(errMsg);
				},
				export: AskpByStopReportService.exportReport,
				exportFormats: ["xlsx"]

			});
			$scope.gridScope.showRowCount = false;
		}]);
