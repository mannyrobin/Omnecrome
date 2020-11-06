angular.module('armada')
	.controller('PlanSchedulesCtrl', ['$scope', 'PlanSchedulesService', 'GridService', '$state', '$stateParams', 'AppConfig', 'Notification', '$modal', '$timeout', 'CountiesService', 'So1ReportService',
		function ($scope, PlanSchedulesService, GridService, $state, $stateParams, AppConfig, Notification, $modal, $timeout, CountiesService, So1ReportService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = PlanSchedulesService;
			$scope.tableWidth = null;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Расписание",
					addTip: "Заполнить расписание"
				},
				gridScope: $scope.gridScope
			};

			/* Права */
			$scope.gridScope.perms = {
				add: true,
				export: true
			};

			var date = new Date();
			var defStartFilterDate = new Date(date.getFullYear(), date.getMonth(), 1);
			var defEndFilterDate = new Date(date.getFullYear(), date.getMonth() + 1, 0);

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "deptId",
				type: "text",
				defval: $stateParams.itemId,
				hide: true
			}, {
				name: "fio",
				type: "text",
				placeholder: "ФИО"
			}, {
				name: {
					fromName: "dateFrom",
					toName: "dateTo"
				},
				defval: {
					fromDate: defStartFilterDate,
					toDate: defEndFilterDate
				},
				type: "range",
				placeholder: {
					from: "Начало периода",
					to: "Окончание периода"
				}
			}, {
				name: "countiesIds",
				type: "multiselect",
				load: CountiesService.getList
			}, {
				name: "departmentId",
				type: "text",
				defval: $stateParams.itemId,
				hide: true
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти расписание сотрудника",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			var initAfterLoad = function () {
				$scope.days = [];
				if ($scope.gridScope.grid.rows && $scope.gridScope.grid.rows.cnt > 0) {
					if (!$scope.gridScope.filters[2].value || !$scope.gridScope.filters[2].value.fromDate ||
						!$scope.gridScope.filters[2].value || !$scope.gridScope.filters[2].value.toDate ||
						(new Date($scope.gridScope.filters[2].value.fromDate)).getTime() > (new Date($scope.gridScope.filters[2].value.toDate)).getTime()) {
						return;
					} else {
						$scope.startDate = moment($scope.gridScope.filters[2].value.fromDate).add(-1, 'day');
						$scope.endDate = moment($scope.gridScope.filters[2].value.toDate);
					}
					for (; $scope.startDate.isBefore($scope.endDate);) {
						$scope.days.push($scope.startDate.add(1, 'day').format('YYYY-MM-DD'));
					}
					var width = angular.element(document.querySelector('#schedule-table')).width() - 265;
					$scope.dayWidth = ((width / $scope.days.length) > AppConfig.MIN_WIDTH_PLAN_COLUMN ? (width / $scope.days.length) : AppConfig.MIN_WIDTH_PLAN_COLUMN) + 'px';
					$scope.tableWidth = width;
					$timeout(resize, 30);
				}
			};

			var resize = function () {
				if ($scope.tableWidth != null) {
					var width = angular.element(document.querySelector('#schedule-table')).width() - 265;
					if ($scope.tableWidth != width) {
						$scope.dayWidth = ((width / $scope.days.length) > AppConfig.MIN_WIDTH_PLAN_COLUMN ? (width / $scope.days.length) : AppConfig.MIN_WIDTH_PLAN_COLUMN) + 'px';
						$scope.tableWidth = width;
					}
				}
				$timeout(resize, 30);
			}

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultNumber: 1000,
				defaultPredicate: "surname",
				gridName: $state.current.name + '.planSchedulesGrid',
				load: $scope.gridScope.service.getPage,
				afterLoad: initAfterLoad,
				export: So1ReportService.exportReport,
				exportFormats: ["xlsx", "zip", "pdf"],
			});

			$scope.gridScope.showRowCount = false;

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
				if (row.shortCountyName) {
					result = result + '(' + row.shortCountyName + ')';
				}
				return result;
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
						case 8:
							result += "И";
							break;
						case 14:
							result += "3";
							break;
						case 15:
							result += "Л1";
							break;	
						case 16:
							result += "Л2";
							break;								
						default:
							return "";
					}
					return result;
				}
				return "";
			};

			$scope.getAllEmpsOfDay = function (date) {
				var allEmps = $scope.gridScope.grid.rows.allEmpsCounts;
				if (allEmps != null && allEmps[date] != null) {
					return allEmps[date];
				}
				return "0 0 0 0";
			}

			$scope.getAllEmpsOfDayTitle = function (date) {
				var rs = $scope.getAllEmpsOfDay(date).split(" ");
				return "В резерве: Первая смена - " + rs[0] + ",  вторая - " + rs[1] + ",  дневная - " + rs[2] + ",  ночная - " + rs[3];
			}

			$scope.getDayOfWeek = function (date) {
				var dayNumber = (new Date(date)).getDay();
				switch (dayNumber) {
					case 0:
						return "вс";
					case 1:
						return "пн";
					case 2:
						return "вт";
					case 3:
						return "ср";
					case 4:
						return "чт";
					case 5:
						return "пт";
					case 6:
						return "сб";
					default:
						return "";
				}
			};

			$scope.getTooltip = function (shift) {
				if (shift != null && shift.shiftId == 8) {
					return shift.shiftDescription;
				}
				return '';
			};

			$scope.createScheduleWithoutEmployee = function () {
				var modalInstance = $modal.open({
					templateUrl: "templates/dialogs/pbk/pbk/plans/schedules/ScheduleFillDlgCtrl.html",
					windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
					controller: 'ScheduleFillDlgCtrl',
					size: 'md',
					resolve: {
						data: function () {
							return {
								title: "Создание смены сотрудника",
								item: {
									planDate: moment(new Date()),
									deptId: $stateParams.itemId,
									isForCurrentDay: false
								},
								date: new Date()
							};
						}
					}
				});
				modalInstance.result.then(function () {
					$scope.gridScope.grid.refreshAction();
				});
				return;
			}

			$scope.addEditSchedule = function (row, day) {
				if (row.fireDate != null && row.fireDate < day)
					return;
				var shift = row.shifts[day];
				if (shift == null) {
					var modalInstance = $modal.open({
						templateUrl: "templates/dialogs/pbk/pbk/plans/schedules/ScheduleEditDlg.html",
						windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
						controller: 'ScheduleEditCtrl',
						size: 'md',
						resolve: {
							data: function () {
								return {
									title: "Создание смены сотрудника " + $scope.getFio(row) + " на " + moment(day).format('DD.MM.YYYY'),
									item: {
										planDate: moment(new Date(day)),
										deptId: $stateParams.itemId,
										employeeId: row.id
									},
									date: new Date(day)
								};
							}
						}
					});
					modalInstance.result.then(function () {
						$scope.gridScope.grid.refreshAction();
					});
					return;
				}
				var modalInstance = $modal.open({
					templateUrl: "templates/dialogs/pbk/pbk/plans/schedules/ScheduleEditDlg.html",
					windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
					controller: 'ScheduleEditCtrl',
					size: 'md',
					resolve: {
						data: function () {
							return {
								title: "Редактирование смены сотрудника " + $scope.getFio(row) + " на " + moment(day).format('DD.MM.YYYY'),
								id: shift.id,
								item: {
									planDate: moment(new Date(day)),
									deptId: $stateParams.itemId,
									employeeId: row.id
								},
								date: new Date(day)
							};
						}
					}
				});
				modalInstance.result.then(function () {
					$scope.gridScope.grid.refreshAction();
				});
			}

			/* Экшены */
			$scope.gridScope.addRow = $scope.createScheduleWithoutEmployee;

		}]);