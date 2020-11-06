angular.module('armada')
	.controller('AskpChecksByMovesCtrl', ['$scope', 'AskpChecksByMovesService', 'GridService', '$state', 'canExport', 'DepartmentsService', 'RouteTsKindsService', 'RoutesService', '$timeout', 'AppConfig', 'DateTimeService', 'UtilService', 'AskpByMoveReportService', 'StopIntervalsStatsService',
		function ($scope, AskpChecksByMovesService, GridService, $state, canExport, DepartmentsService, RouteTsKindsService, RoutesService, $timeout, AppConfig, DateTimeService, UtilService, AskpByMoveReportService, StopIntervalsStatsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = AskpChecksByMovesService;
			$scope.calendarColors = AppConfig.CALENDAR_COLORS;
			$scope.askpColors = AppConfig.ASKP_COLORS;

			/* Права */
			$scope.gridScope.perms = {
				add: true,
				export: canExport
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Количество перевезенных пассажиров по выходам",
					addTip: "Пересчитать данные аскп  с учетом выбранных видов билетов",
					addTipIcon: "play-circle"
				},
				gridScope: $scope.gridScope
			};

			$scope.gridScope.caption = "Для формирования отчета необходимо выбрать маршрут";

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "routeNumber",
				label: "Номер маршрута"
			}, {
				id: "moveCode",
				label: "Номер выхода",
				disableSort: true
			}, {
				id: "sum",
				label: "Сумма за период",
				disableSort: true
			}, {
				id: "count",
				label: "Количество дней",
				disableSort: true
			}, {
				id: "avg",
				label: "Среднее за день",
				disableSort: true
			}, {
				id: "min",
				label: "Минимум",
				disableSort: true
			}, {
				id: "max",
				label: "Максимум",
				disableSort: true
			}];

			var now = new Date();

			$scope.$watch('gridScope.filters[0].value', function (newValue, oldValue) {
				if (oldValue && newValue && (newValue.fromDate.valueOf() !== oldValue.fromDate.valueOf()
					|| newValue.toDate.valueOf() !== oldValue.toDate.valueOf())) {
					$scope.gridScope.filters[2].refresh();
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

			$scope.getColor = function (detail) {
				if (angular.isDefined(detail)) {
					if (detail.depotNumber !== null && detail.isHasTelematics) {
						return $scope.calendarColors[0]
					}
					return $scope.calendarColors[1];
				}
				return "";
			}

			$scope.getTooltip = function (detail) {
				if (angular.isDefined(detail)) {
					if (detail.depotNumber === null) {
						return "Не найдены фактические рейсы";
					}
					if (!detail.isHasTelematics) {
						return "Не найдена телематика. Бортовой номер ТС - " + detail.depotNumber;
					}
					return "Есть телематика";
				}
				return "";
			}

			/* Грид */
			$scope.gridScope.extheaders = [];
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "routeNumber",
				gridName: $state.current.name + '.AskpChecksByMoves',
				load: $scope.gridScope.service.getPage,
				lazyLoad: true,
				afterLoad: function () {
					$scope.gridScope.extheaders = [];
					if ($scope.gridScope.grid.rows && $scope.gridScope.grid.rows.length > 0) {
						for (var i = 0; i < $scope.gridScope.grid.rows[0].days.length; ++i) {
							var day = $scope.gridScope.grid.rows[0].days[i];
							$scope.gridScope.extheaders.push({
								label: day.day,
								style: 'border-right: 1px solid #ddd; background-color: ' + (day.holiday ? $scope.calendarColors[1] : $scope.calendarColors[0]) + ';'
								,
								hrefClick: function (row, label) {
									$("td").tooltip("hide");
									var date = new Date();
									var arr = label.split(".");
									date.setMonth(parseInt(arr[1]) - 1, parseInt(arr[0]));
									$state.go('app.main.askp.checks.stops', {
										date: date,
										routeId: row.routeId,
										moveCode: row.moveCode,
										routeName: row.routeName
									});
								}
								,
								hrefShow: function (row) {
									return row['moveCode'] != null;
								}
							});
						}
					}
				},
				export: AskpByMoveReportService.exportReport,
				exportFormats: ["xlsx"]
			});

			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/askp/AskpChecksUpdateDlg.html", "AskpChecksUpdateCtrl", "Пересчет данных аскп", "md", $scope.gridScope);
		}]);
