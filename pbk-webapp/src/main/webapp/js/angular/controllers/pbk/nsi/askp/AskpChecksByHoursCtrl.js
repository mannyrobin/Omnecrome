angular.module('armada')
	.controller('AskpChecksByHoursCtrl', ['$scope', 'AskpChecksByHoursService', 'GridService', '$state', 'canExport', 'DepartmentsService', 'RouteTsKindsService', 'RoutesService', '$timeout', 'AppConfig', 'DateTimeService', 'AskpByHourReportService', 'StopIntervalsStatsService',
		function ($scope, AskpChecksByHoursService, GridService, $state, canExport, DepartmentsService, RouteTsKindsService, RoutesService, $timeout, AppConfig, DateTimeService, AskpByHourReportService, StopIntervalsStatsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = AskpChecksByHoursService;
			$scope.calendarColors = AppConfig.CALENDAR_COLORS;

			/* Права */
			$scope.gridScope.perms = {
				export: canExport
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Количество перевезенных пассажиров по часам суток"
				},
				gridScope: $scope.gridScope
			};
			$scope.gridScope.caption = "Для формирования отчета необходимо выбрать маршрут";

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "workDate",
				label: "Дата",
				styleFunc: function (row) {
					if (row['workDate'] != "Среднее") {
						return 'background-color: ' + (row.holiday ? $scope.calendarColors[1] : $scope.calendarColors[0]) + ';';
					}
				}
			}, {
				id: "sum",
				label: "Сумма за сутки",
				disableSort: true
			}];

			var now = new Date();

			$scope.$watch('gridScope.filters[0].value', function (newValue, oldValue) {
				if (newValue.valueOf() !== oldValue.valueOf()) {
					$scope.filters[2].refresh();
				}
			});

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
				name: "isWorkDate",
				type: "yesno",
				placeholder: "Рабочий день",
				defval: {id: -1, name: "Все"}
			}, {
				name: "parkId",
				type: "select",
				placeholder: "Парк",
				load: function () {
					return StopIntervalsStatsService.getParkSelectList([{
						name: "isWorkDate",
						value: $scope.gridScope.filters[1].value,
						type: "yesno"
					}, {
						name: "dateFrom",
						value: $scope.gridScope.filters[0].value.fromDate,
						type: "date"
					}, {
						name: "dateTo",
						value: $scope.gridScope.filters[0].value.toDate,
						type: "date"
					}])
				}
			}, {
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
						value: $scope.gridScope.filters[3].value,
						type: "multiselect"
					}, {
						name: "parkId",
						type: "select",
						value: $scope.gridScope.filters[2].value
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
				defaultPredicate: "workDate",
				gridName: $state.current.name + '.AskpChecksByHours',
				load: $scope.gridScope.service.getPage,
				lazyLoad: true,
				afterLoad: function () {
					$scope.gridScope.extheaders = [];
					if ($scope.gridScope.grid.rows && $scope.gridScope.grid.rows.length > 0) {
						for (var i = 0; i < $scope.gridScope.grid.rows[0].hours.length; ++i) {
							$scope.gridScope.extheaders.push($scope.getExtHeader($scope.gridScope.grid.rows[0].hours[i]));
						}
						$scope.gridScope.grid.rows[$scope.gridScope.grid.rows.length - 1]['workDate'] = "Среднее";
					}
				},
				export: AskpByHourReportService.exportReport,
				exportFormats: ["xlsx"]
			});
		}]);
