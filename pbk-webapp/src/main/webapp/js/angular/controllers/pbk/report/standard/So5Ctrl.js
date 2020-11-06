angular.module('armada')
	.controller('So5Ctrl', ['$scope', 'So5Service', 'GridService', '$state', 'AppConfig', 'So5ReportService', 'DepartmentsService', 'canExportReport5',
		function ($scope, So5Service, GridService, $state, AppConfig, So5ReportService, DepartmentsService, canExportReport5) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So5Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport5
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Посменная численность контролёров ГУП \"Мосгортранс\" и среднее значение численности за период"
				},
				gridScope: $scope.gridScope
			};

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: {
					fromName: "dateFrom",
					toName: "dateTo"
				},
				defval: {
					fromDate: new Date(),
					toDate: new Date()
				},
				type: "range",
				placeholder: {
					from: "Начало периода",
					to: "Окончание периода"
				}
			}, {
				name: "departmentId",
				type: "multiselect",
				placeholder: "Подразделение",
				load: function () {
					return DepartmentsService.getList([
						{
							name: "forPlanUse",
							value: 1,
							type: "id"
						}
					]);
				}
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти посменные численности контролёров ГУП \"Мосгортранс\" и среднее значение численности за период",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			var initAfterLoad = function () {
				$scope.days = [];
				if ($scope.gridScope.grid.rows && $scope.gridScope.grid.rows.length > 0) {
					if ($scope.gridScope.grid.rows && $scope.gridScope.grid.rows.length > 0) {
						if (!$scope.gridScope.filters[0].value || !$scope.gridScope.filters[0].value.fromDate ||
							!$scope.gridScope.filters[0].value || !$scope.gridScope.filters[0].value.toDate ||
							(new Date($scope.gridScope.filters[0].value.fromDate)).getTime() > (new Date($scope.gridScope.filters[0].value.toDate)).getTime()) {
							return;
						} else {
							$scope.startDate = moment($scope.gridScope.filters[0].value.fromDate).add(-1, 'day');
							$scope.endDate = moment($scope.gridScope.filters[0].value.toDate);
						}
						for (; $scope.startDate.isBefore($scope.endDate);) {
							$scope.days.push($scope.startDate.add(1, 'day').format('YYYY-MM-DD'));
						}
						So5Service.getTotal($scope.gridScope.filters).then(function (response) {
							$scope.gridScope.footerRecord = response;
						});
					}
				}
			};

			$scope.gridScope.getFooterRecord = function (id) {
				if ($scope.gridScope.footerRecord != undefined)
					return $scope.gridScope.footerRecord[id];
			}

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "",
				gridName: $state.current.name + '.So5Grid',
				load: $scope.gridScope.service.getPage,
				export: So5ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
				afterLoad: initAfterLoad,
                lazyLoad: true
			});

			$scope.getDayFirstShiftCount = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].firstShift;
					}
				}
				return 0;
			};

			$scope.getDaySecondShiftCount = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].secondShift;
					}
				}
				return 0;
			};

			$scope.getDayTotalCount = function (daysSummaries, date) {
				if (daysSummaries == undefined)
					return 0;
				for (var i = 0; i < daysSummaries.length; i++) {
					if (daysSummaries[i].date === date) {
						return daysSummaries[i].total;
					}
				}
				return 0;
			};

			$scope.getDate = function (date) {
				return new Date(date);
			};

			$scope.getRounded = function (value, precision) {
				return Math.round(value * Math.pow(10, precision)) / Math.pow(10, precision);
			};

			$scope.gridScope.hideIndexColumn = true;

		}]);
