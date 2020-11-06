angular.module('armada')
	.controller('So1Ctrl', ['$scope', 'So1Service', 'GridService', '$state', 'AppConfig', 'So1ReportService', 'DepartmentsService', 'canExportReport1',
		function ($scope, So1Service, GridService, $state, AppConfig, So1ReportService, DepartmentsService, canExportReport1) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = So1Service;

			$scope.gridScope.hideIndexColumn = true;
			$scope.gridScope.hideButton = 1;

			/* Права */
			$scope.gridScope.perms = {
				export: canExportReport1
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "График работы контролеров по территориальному подразделению"
				},
				hideInfinityLoad: $scope.gridScope.hideButton,
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
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти графики работы контролеров",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			var initAfterLoad = function () {
				$scope.days = [];
				if ($scope.gridScope.grid.rows && $scope.gridScope.grid.rows.cnt > 0) {
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
					var dayWidthValue = ((1242 / $scope.days.length) > AppConfig.MIN_WIDTH_PLAN_COLUMN ? (1242 / $scope.days.length) : AppConfig.MIN_WIDTH_PLAN_COLUMN);
					$scope.dayWidth = dayWidthValue + 'px';
					$scope.footerWidth = (265 + dayWidthValue * $scope.days.length) + 'px';
				}
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "surname",
				gridName: $state.current.name + '.So1Grid',
				load: $scope.gridScope.service.getPage,
				export: So1ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
				afterLoad: initAfterLoad,
				defaultNumber: 10,
                lazyLoad: true
			});

			$scope.scheduleColors = AppConfig.EMPLOYEE_CALENDAR_COLORS;

			$scope.getDepartmentName = function (row) {
				return row.employee.departmentName;
			}

			$scope.getFio = function (row) {
				var result = '';
				if (row.employee.surname && row.employee.surname.length > 0) {
					result = row.employee.surname;
				}
				if (row.employee.name && row.employee.name.length > 0) {
					result = result + ' ' + row.employee.name[0] + '.';
				}
				if (row.employee.patronumic && row.employee.patronumic.length > 0) {
					result = result + ' ' + row.employee.patronumic[0] + '.';
				}
				return result;
			};

			$scope.getShift = function (schedule, date) {
				for (var i = 0; i < schedule.length; i++) {
					if (schedule[i].date === date) {
						return schedule[i];
					}
				}
				return null;
			};

			$scope.getDayOfMonth = function (date) {
				return (new Date(date)).getUTCDate();
			};

			$scope.getCurrentDateColor = function (shift) {
				if (shift) {
					if (shift.isReserve) {
						return AppConfig.EMPLOYEE_CALENDAR_COLORS.RESERVE;
					}
					switch (shift.shiftId) {
						case 1:
							return AppConfig.EMPLOYEE_CALENDAR_COLORS.HOLIDAY;
						case 4:
							return AppConfig.EMPLOYEE_CALENDAR_COLORS.VACATION;
						default:
							return "";
					}
				}
				return "";
			};

			$scope.getCurrentDateText = function (shift) {
				if (shift) {
					var result = '';
					switch (shift.shiftId) {
						case 1:
							result += "В";
							break;
						case 2:
							result += "Д";
							break;
						case 3:
							result += "Н";
							break;
						case 4:
							result += "О";
							break;
						case 5:
							result += "Б";
							break;
						case 6:
							result += "1";
							break;
						case 7:
							result += "2";
							break;
                        case 14:
                            result += "3";
                            break;
						case 8:
							result += "И";
							break;
						default:
							return "";
					}
					return shift.isReserve ? result + ' (р)' : result;
				}
				return "";
			};

			$scope.getTooltip = function (shift) {
				if (shift != null && shift.shiftId == 8) {
					return shift.shiftDescription;
				}
				return '';
			};
		}]);
