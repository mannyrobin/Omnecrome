angular.module('armada')
	.controller('So2Ctrl', ['$scope', 'So2Service', 'GridService', '$state', 'AppConfig', 'So2ReportService', 'DepartmentsService', 'canExportReport2',
		function ($scope, So2Service, GridService, $state, AppConfig, So2ReportService, DepartmentsService, canExportReport2) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So2Service;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport2
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Табель фактически отработанного времени"
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
			}, {
				name: "fio",
				type: "text",
				placeholder: "ФИО"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти отработанное время контролеров",
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
					}
				}
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "surname",
				gridName: $state.current.name + '.So2Grid',
				load: $scope.gridScope.service.getPage,
				export: So2ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
				afterLoad: initAfterLoad,
                lazyLoad: true
			});

			$scope.scheduleColors = AppConfig.EMPLOYEE_CALENDAR_COLORS;

			$scope.getFio = function (row) {
				var result = '';
				if (row.surname && row.surname.length > 0) {
					result = row.surname;
				}
				if (row.name && row.name.length > 0) {
					result = result + ' ' + row.name[0] + '.';
				}
				if (row.patronumic && row.patronumic.length > 0) {
					result = result + ' ' + row.patronumic[0] + '.';
				}
				return result;
			};

			$scope.getDayOfMonth = function (date) {
				return (new Date(date)).getUTCDate();
			};

			$scope.getCurrentDateColor = function (shift) {
				if (shift) {
					switch (shift.absenceShiftId) {
						case 1:
							return AppConfig.EMPLOYEE_CALENDAR_COLORS.HOLIDAY;
						case 4:
							return AppConfig.EMPLOYEE_CALENDAR_COLORS.VACATION;
						case 5:
							return AppConfig.EMPLOYEE_CALENDAR_COLORS.HOSPITAL;
						default:
							return "";
					}
				}
				return "";
			};

			$scope.getCurrentDateText = function (shift) {
				if (shift) {
					var result = '';
					if (shift.absenceShiftId !== null) { // для корректной обработки absenceShiftId = 0 (если понадобится)
						switch (shift.absenceShiftId) {
							case 1:
								result += "В";
								break;
							case 4:
								result += "О";
								break;
							case 5:
								result += "Б";
								break;
							default:
								return ""; // другие причины отсутствия контролёра пока не предусмотрены
						}
					} else {
						result = Math.round(shift.shiftHours * 100) / 100;
					}
					return result;
				}
				return "";
			};

			$scope.getDepartmentName = function (row) {
				return row.departmentName;
			}

			$scope.gridScope.hideIndexColumn = true;

		}]);
