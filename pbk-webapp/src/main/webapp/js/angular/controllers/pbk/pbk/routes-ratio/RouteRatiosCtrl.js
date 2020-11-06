angular.module('armada')
	.controller('RouteRatiosCtrl', ['$scope', 'RouteRatiosService', 'RouteRatiosReportService', 'CountiesService', 'DistrictsService', 'GridService',
		'$state', '$stateParams', 'AppConfig', '$modal', 'isNotReadOnlyUser', 'Notification', 'RouteTsKindsService',
		function ($scope, RouteRatiosService, RouteRatiosReportService, CountiesService, DistrictsService, GridService, $state, $stateParams, AppConfig, $modal, isNotReadOnlyUser, Notification, RouteTsKindsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = RouteRatiosService;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				export: isNotReadOnlyUser,
				gridexport: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Рейтинги маршрутов",
					addTip: "Пересчитать рейтинги",
					addTipIcon: "stats"
				},
				gridScope: $scope.gridScope
			};

			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/pbk/plans/routes-ratio/RouteRatioReCalcDlg.html", "RouteRatioReCalcCtrl", "Пересчет рейтингов", "md", $scope.gridScope);

			var date = new Date();
			var defStartFilterDate = new Date(date.getFullYear(), date.getMonth(), 1);
			var defEndFilterDate = new Date(date.getFullYear(), date.getMonth() + 1, 0);

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "routeNumber",
				type: "text",
				placeholder: "Номер маршрута"
			}, {
				name: "routeTypeId",
				type: "select",
				placeholder: "Вид транспорта",
				load: RouteTsKindsService.getList
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
				name: "countyId",
				type: "select",
				load: CountiesService.getList,
				placeholder: "Округ",
				onChange: function () {
					$scope.filters[4].refresh();
				}
			}, {
				name: "districtId",
				type: "multiselect",
				load: function () {
					return DistrictsService.getList([{
						name: "countyId",
						value: $scope.filters[3].value == null ? null : $scope.filters[3].value.id,
						type: "id"
					}
					]);
				},
				placeholder: "Район"
			}, {
				name: "rating",
				type: "text",
				placeholder: "Рейтинг"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти маршрут",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			var initAfterLoad = function () {
				$scope.days = [];
				if ($scope.gridScope.grid.rows && $scope.gridScope.grid.rows.length > 0) {
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
				}
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "routeNumber",
				gridName: $state.current.name + '.routesRatioGrid',
				load: $scope.gridScope.service.getPage,
				afterLoad: initAfterLoad,
				export: RouteRatiosReportService.exportReport,
				exportFormats: ["xlsx", "pdf", "csv", "zip"]
			});

			$scope.getDayOfMonth = function (date) {
				return (new Date(date)).getUTCDate();
			};

			$scope.getRouteRatioText = function (route) {
				if (route) {
					return route.routeRatio;
				}
				return "";
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

			if (isNotReadOnlyUser) {
				$scope.addEditRouteRatio = function (row, day) {
					var currentDate = new Date();
					currentDate.setHours(0, 0, 0, 0);
					var bExpired = new Date(day).getTime() < currentDate.getTime();
					var item = {};
					if (row.routes[day] == null) {
						item = {
							workDate: new Date(day),
							routeId: row.id
						};
						if (bExpired) {
							Notification.info('Нельзя создавать рейтинг маршрута на прошедшую дату.');
						} else {
							addRouteRatioDilog(item);
						}
						return;
					}
					editRouteRatioDilog(row.routes[day], bExpired);
					return;
				};

				function editRouteRatioDilog(row, bExpired) {
					var modalInstance = $modal.open({
						templateUrl: "templates/dialogs/pbk/pbk/plans/routes-ratio/RouteRatioEditDlg.html",
						windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
						controller: 'RouteRatioEditCtrl',
						size: 'sm',
						resolve: {
							data: function () {
								return {
									title: "Редактирование рейтинга маршрута",
									id: row.id,
									bExpired: bExpired,
									workDate: row.workDate
								};
							}
						}
					});
					modalInstance.result.then(function () {
						$scope.gridScope.grid.refreshAction();
					});
				};

				function addRouteRatioDilog(item) {
					var modalInstance = $modal.open({
						templateUrl: "templates/dialogs/pbk/pbk/plans/routes-ratio/RouteRatioEditDlg.html",
						windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
						controller: 'RouteRatioEditCtrl',
						size: 'sm',
						resolve: {
							data: function () {
								return {
									title: "Создание рейтинга маршрута",
									item: item
								};
							}
						}
					});
					modalInstance.result.then(function () {
						$scope.gridScope.grid.refreshAction();
					});
				};
			}
		}]);
