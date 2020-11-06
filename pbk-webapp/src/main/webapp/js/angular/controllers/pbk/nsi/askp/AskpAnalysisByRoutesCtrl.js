angular.module('armada')
	.controller('AskpAnalysisByRoutesCtrl', ['$scope', 'AskpAnalysisByRoutesService', 'GridService', '$state', 'canExport', 'DepartmentsService', 'RouteTsKindsService', 'RoutesService', '$timeout', 'DateTimeService',
		function ($scope, AskpAnalysisByRoutesService, GridService, $state, canExport, DepartmentsService, RouteTsKindsService, RoutesService, $timeout, DateTimeService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = AskpAnalysisByRoutesService;

			/* Права */
			$scope.gridScope.perms = {
				export: canExport
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Сравнительный анализ пассажиропотока по маршрутам"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "date",
				label: "Дата",
				class: "col-md-1",
				disableSort: true
			}, {
				id: "routeNumber",
				label: "Номер маршрута",
				class: "col-md-1"
			}, {
				id: "askpPassengerCount",
				label: "Количество перевезенных пассажиров (АСКП)",
				class: "col-md-2"
			}, {
				id: "asmppPassengerCount",
				label: "Количество перевезенных пассажиров (АСМ-ПП)",
				class: "col-md-2"
			}, {
				id: "diffs",
				label: "Разница",
				class: "col-md-2"
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
			}/*, {
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
			 }*/, {
				name: "tsType",
				type: "multiselect",
				placeholder: "Тип ТС",
				load: RouteTsKindsService.getList
			}, {
				name: "routes",
				type: "multiselect",
				lazyload: function (query) {
					return RoutesService.getList([{
						name: "name",
						value: query,
						type: "text"
					}, {
						name: "tsType",
						value: $scope.gridScope.filters[1].value,
						type: "multiselect"
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
				defaultPredicate: "routeNumber",
				gridName: $state.current.name + '.AskpAnalysisByRoutes',
				load: $scope.gridScope.service.getPage,
				afterLoad: function () {
					if ($scope.gridScope.grid.rows && $scope.gridScope.grid.rows.length > 0) {
						$scope.gridScope.grid.rows[0]['date'] =
							moment($scope.gridScope.filters[0].value.fromDate).format('DD.MM.YYYY') + " - " +
							moment($scope.gridScope.filters[0].value.toDate).format('DD.MM.YYYY');
					}
				},
				//export: So17ReportService.exportReport,
				//exportFormats:  ["xlsx", "zip", "pdf"]
			});

			$scope.gridScope.hideIndexColumn = true;
		}]);
