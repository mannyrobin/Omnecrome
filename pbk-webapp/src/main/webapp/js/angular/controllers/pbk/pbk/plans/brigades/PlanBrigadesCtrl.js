angular.module('armada')
	.controller('PlanBrigadesCtrl', ['$scope', 'PlanBrigadesService', 'GridService', '$state', '$stateParams', 'AppConfig', '$modal', 'Notification', 'isNotReadOnlyUser', '$timeout', 'isBrigadeEditRole',
		function ($scope, PlanBrigadesService, GridService, $state, $stateParams, AppConfig, $modal, Notification, isNotReadOnlyUser, $timeout, isBrigadeEditRole) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = PlanBrigadesService;
			$scope.departmentId = $stateParams.itemId;

			$scope.scrolls = null;

			/* Права */
			$scope.gridScope.perms = {
				add: isBrigadeEditRole,
				export: isNotReadOnlyUser
			};

			$scope.brigadeColors = AppConfig.BRIGADE_COLORS;
			$scope.tableWidth = null;

			/* Заголовок */
			$scope.gridScope.titleWidth = 'col-lg-7';
			$scope.gridScope.titleButtonsWidth = 'col-lg-5';
			$scope.gridScope.titleParams = {
				labels: {
					title: "Бригады",
					addTip: "Согласовать бригады",
					addTipIcon: "thumbs-up",
					buttons: [
						{
							tip: "Снять согласование бригад",
							tipIcon: "thumbs-down",
							status: "danger",
							perm: isBrigadeEditRole,
							action: GridService.getAddAction(
								"pbk/pbk/plans/brigades/BrigadeDisApproveDlg.html", "BrigadeDisApproveCtrl", "Снять согласование бригад", "md", $scope.gridScope)
						},
						{
							tip: "Сформировать бригады",
							tipIcon: "user",
							status: "success",
							perm: isNotReadOnlyUser,
							action: GridService.getAddAction(
								"pbk/pbk/plans/brigades/BrigadeReCreateDlg.html", "BrigadeReCreateCtrl", "Сформировать бригады", "md", $scope.gridScope)
						}]
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
				filterString: "Найти бригаду",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			var initAfterLoad = function () {
				$scope.days = [];
				if ($scope.gridScope.grid.rows && $scope.gridScope.grid.rows.length > 0) {
					if (!$scope.gridScope.filters[1].value || !$scope.gridScope.filters[1].value.fromDate || !$scope.gridScope.filters[1].value || !$scope.gridScope.filters[1].value.toDate ||
						(new Date($scope.gridScope.filters[1].value.fromDate)).getTime() > (new Date($scope.gridScope.filters[1].value.toDate)).getTime()) {
						return;
					} else {
						$scope.startDate = moment($scope.gridScope.filters[1].value.fromDate).add(-1, 'day');
						$scope.endDate = moment($scope.gridScope.filters[1].value.toDate);
					}
					for (; $scope.startDate.isBefore($scope.endDate);) {
						$scope.days.push($scope.startDate.add(1, 'day').format('YYYY-MM-DD'));
					}
					var width = angular.element(document.querySelector('#brigades-table')).width() - 365;
					var dayWidthValue = ((width / $scope.days.length) > AppConfig.MIN_WIDTH_PLAN_COLUMN ? (width / $scope.days.length) : AppConfig.MIN_WIDTH_PLAN_COLUMN);
					$scope.dayWidth = dayWidthValue + 'px';
					$scope.footerWidth = (365 + dayWidthValue * $scope.days.length) + 'px';
					$scope.tableWidth = width;
					$timeout(resize, 30);
					$timeout(function () {
						if ($scope.scrolls != null) {
							$(window).scrollTop($scope.scrolls.wy);
							$(window).scrollLeft($scope.scrolls.wy);
							$("#tableBody").scrollTop($scope.scrolls.tby);
							$("#tableBody").scrollLeft($scope.scrolls.tbx);
							$scope.scrolls = null;
						}
					}, 2);
				}
			};

			var resize = function () {
				if ($scope.tableWidth != null) {
					var width = angular.element(document.querySelector('#brigades-table')).width() - 365;
					if ($scope.tableWidth != width) {
						var dayWidthValue = ((width / $scope.days.length) > AppConfig.MIN_WIDTH_PLAN_COLUMN ? (width / $scope.days.length) : AppConfig.MIN_WIDTH_PLAN_COLUMN);
						$scope.dayWidth = dayWidthValue + 'px';
						$scope.footerWidth = (365 + dayWidthValue * $scope.days.length) + 'px';
						$scope.tableWidth = width;
					}
				}
				$timeout(resize, 30);
			}

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "venueName",
				gridName: $state.current.name + '.planBrigadesGrid',
				load: $scope.gridScope.service.getPage,
				afterLoad: initAfterLoad,
				export: PlanBrigadesService.exportBrigades,
				exportFormats: ['xlsx']
			});

			$scope.getVenueView = function (row) {
				var result = '';
				if (row.venueName && row.venueName.length > 0) {
					result = row.venueName;
				}
				if (row.countyName && row.countyName.length > 0) {
					result = result + ' (' + row.countyName;
					if (row.districtName && row.districtName.length > 0) {
						result = result + ' - ' + row.districtName + '';
					}
					result = result + ')';
				}
				return result;
			};

			$scope.getShiftPeriod = function (row) {
				if (row.shiftWorkStart && row.shiftWorkStart.length > 0 && row.shiftWorkEnd && row.shiftWorkEnd.length > 0) {
					return row.shiftWorkStart + ' - ' + row.shiftWorkEnd;
				}
				return "";

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

			$scope.getReserveOfDay = function (date) {
				var reserves = $scope.gridScope.grid.rows[0].reserveCounts;
				if (reserves != null && reserves[date] != null) {
					return reserves[date];
				}
				return "0 0 0 0";
			}

			$scope.getReserveOfDayTitle = function (date) {
				var rs = $scope.getReserveOfDay(date).split(" ");
				return "В резерве: Первая смена - " + rs[0] + ",  вторая - " + rs[1] + ",  дневная - " + rs[2] + ",  ночная - " + rs[3];
			}

			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/pbk/plans/brigades/BrigadeApproveDlg.html", "BrigadeApproveCtrl", "Согласование бригад", "md", $scope.gridScope);

			$scope.addEditShiftBrigade = function (row, day) {
				var item = {};
				if (row.brigades[day] == null) {
					item = {
						deptId: $stateParams.itemId,
						planDate: new Date(day),
						cityVenueId: row.id,
						shiftId: row.shiftId,
						tpu: row.tpu
					};
					addShiftBrigadeDilog(item);
				} else {
					if (row.brigades[day].isAgree) {
						Notification.info('Нельзя редактировать согласованную бригаду.');
					} else if (row.brigades[day].id != null) {
						editShiftBrigadeDilog(row.brigades[day].id, new Date(day), row.tpu);
					}
				}
			};

			function editShiftBrigadeDilog(id, date, tpu) {
				var modalInstance = $modal.open({
					templateUrl: "templates/dialogs/pbk/pbk/plans/brigades/BrigadeShiftEditDlg.html",
					windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
					controller: 'BrigadeShiftEditCtrl',
					size: 'md',
					resolve: {
						data: function () {
							return {
								title: "Формирование бригады",
								id: id,
								date: date,
								tpu: tpu
							};
						}
					}
				});
				modalInstance.result.then(function () {
					$scope.scrolls = {
						wx: $(window).scrollLeft(), wy: $(window).scrollTop(),
						tbx: $("#tableBody").scrollLeft(), tby: $("#tableBody").scrollTop()
					};
					$scope.gridScope.grid.refreshAction();
				});
			};

			function addShiftBrigadeDilog(item) {
				var modalInstance = $modal.open({
					templateUrl: "templates/dialogs/pbk/pbk/plans/brigades/BrigadeShiftEditDlg.html",
					windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
					controller: 'BrigadeShiftEditCtrl',
					size: 'md',
					resolve: {
						data: function () {
							return {
								title: "Формирование бригады",
								item: item,
								date: item.planDate,
								tpu: item.tpu
							};
						}
					}
				});
				modalInstance.result.then(function () {
					$scope.gridScope.grid.refreshAction();
				});
			};

			$scope.getColor = function (brigade) {
				if (brigade && brigade.isAgree) {
					if (brigade.notFull) {
						return AppConfig.BRIGADE_COLORS.NOT_FULL;
					}
					if (brigade.haveFreeControlers) {
						return AppConfig.BRIGADE_COLORS.HAVE_FREE_CONTROLERS;
					}
					return AppConfig.BRIGADE_COLORS.AGREE;
				} else if (brigade && !brigade.isAgree) {
					if (brigade.id == null) {
						return "lightgray";
					}
					if (brigade.notFull) {
						return AppConfig.BRIGADE_COLORS.NOT_FULL_NOT_AGREE;
					}
					if (brigade.haveFreeControlers) {
						return AppConfig.BRIGADE_COLORS.HAVE_FREE_CONTROLERS_NOT_AGREE;
					}
				}
				else if (brigade && !brigade.enough) {
					return AppConfig.BRIGADE_COLORS.NOT_ENOUGH;
				}
				return "";
			};
		}]);
