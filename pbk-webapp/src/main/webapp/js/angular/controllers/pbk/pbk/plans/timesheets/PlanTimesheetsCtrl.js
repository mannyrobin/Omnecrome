angular.module('armada')
	.controller('PlanTimesheetsCtrl', ['$scope', 'PlanTimesheetsService', 'GridService', '$state', '$stateParams', 'AppConfig', 'Notification', '$modal', 'isNotReadOnlyUser', '$timeout',
		function ($scope, PlanTimesheetsService, GridService, $state, $stateParams, AppConfig, Notification, $modal, isNotReadOnlyUser, $timeout) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = PlanTimesheetsService;

			$scope.scheduleColors = AppConfig.EMPLOYEE_CALENDAR_COLORS;
			$scope.tableWidth = null;

			/* Права */
			$scope.gridScope.perms = {
				export: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Табель"
				},
				gridScope: $scope.gridScope
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
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти сотрудника",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			var initAfterLoad = function () {
				$scope.days = [];
				if ($scope.gridScope.grid.rows && $scope.gridScope.grid.rows.length > 0) {
					if (!$scope.gridScope.filters[2].value || !$scope.gridScope.filters[2].value.fromDate || !$scope.gridScope.filters[2].value || !$scope.gridScope.filters[2].value.toDate ||
						(new Date($scope.gridScope.filters[2].value.fromDate)).getTime() > (new Date($scope.gridScope.filters[2].value.toDate)).getTime()) {
						return;
					} else {
						$scope.startDate = moment($scope.gridScope.filters[2].value.fromDate).add(-1, 'day');
						$scope.endDate = moment($scope.gridScope.filters[2].value.toDate);
					}
					for (; $scope.startDate.isBefore($scope.endDate);) {
						$scope.days.push($scope.startDate.add(1, 'day').format('YYYY-MM-DD'));
					}
					var width = angular.element(document.querySelector('#timesheets-table')).width() - 265;
					var dayWidthValue = ((width / $scope.days.length) > AppConfig.MIN_WIDTH_PLAN_COLUMN ? (width / $scope.days.length) : AppConfig.MIN_WIDTH_PLAN_COLUMN);
					$scope.dayWidth = dayWidthValue + 'px';
					$scope.footerWidth = (265 + dayWidthValue * $scope.days.length) + 'px';
					$scope.tableWidth = width;
					$timeout(resize, 30);
				}
			};

			var resize = function () {
				if ($scope.tableWidth != null) {
					var width = angular.element(document.querySelector('#timesheets-table')).width() - 265;
					if ($scope.tableWidth != width) {
						var dayWidthValue = ((width / $scope.days.length) > AppConfig.MIN_WIDTH_PLAN_COLUMN ? (width / $scope.days.length) : AppConfig.MIN_WIDTH_PLAN_COLUMN);
						$scope.dayWidth = dayWidthValue + 'px';
						$scope.footerWidth = (265 + dayWidthValue * $scope.days.length) + 'px';
						$scope.tableWidth = width;
					}
				}
				$timeout(resize, 30);
			}

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "surname",
				gridName: $state.current.name + '.planTimesheetsGrid',
				load: $scope.gridScope.service.getPage,
				afterLoad: initAfterLoad,
				export: PlanTimesheetsService.exportTimesheets,
				exportFormats: ['xlsx']
			});

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

			$scope.getCurrentDateColor = function (timeSheet) {
				if (timeSheet) {
					if (timeSheet.isReserve) {
						return AppConfig.EMPLOYEE_CALENDAR_COLORS.RESERVE;
					}
					switch (timeSheet.shiftId) {
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
			$scope.getCurrentDateText = function (timeSheet) {
				var result = "";
				if (timeSheet) {
					switch (timeSheet.shiftId) {
						case 1:
							result += "В";
							break;
						case 4:
							result += "О";
							break;
						case 5:
							result += "Б";
							break;
					}
				}
				return result;
			};

			$scope.isWorkShift = function (timeSheet) {
				if (timeSheet && [1, 4, 5].indexOf(timeSheet.shiftId) != -1) {
					return true;
				}
				else
					return false;

			};

			$scope.getTooltip = function (timesheet) {
				if (timesheet != null && timesheet.shiftId == 8) {
					return timesheet.shiftDescription;
				}
				return '';
			};

			if (isNotReadOnlyUser) {
				$scope.editTimesheet = function (row, day) {
					var item = {};
					if (row.timesheets[day] == null) {
						Notification.info('Нельзя создавать табель вручную.');
					} else {
						if ([1, 4, 5].indexOf(row.timesheets[day].shiftId) != -1)
							Notification.info('Нерабочая смена.');
						else {
							var modalInstance = $modal.open({
								templateUrl: "templates/dialogs/pbk/pbk/plans/timesheets/TimesheetEditDlg.html",
								windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
								controller: 'TimesheetEditCtrl',
								size: 'sm',
								resolve: {
									data: function () {
										return {
											title: "Редактирование табеля отрудника " + $scope.getFio(row) + " на " + moment(day).format('DD.MM.YYYY'),
											id: row.timesheets[day].id
										};
									}
								}
							});
							modalInstance.result.then(function () {
								$scope.gridScope.grid.refreshAction();
							});
						}
					}
				};
			}
		}]);
