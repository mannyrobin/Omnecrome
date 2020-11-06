angular.module('armada')
	.controller('RoutesCtrl', ['$scope', 'RoutesService', 'RoutesReportService', 'GridService', '$state', 'RouteTsKindsService', 'isNotReadOnlyUser',
		function ($scope, RoutesService, RoutesReportService, GridService, $state, RouteTsKindsService, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = RoutesService;
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Права */
			$scope.gridScope.perms = {
				//add: isNotReadOnlyUser,
				//remove: isNotReadOnlyUser,
				export: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Маршруты",
					addTip: "Добавить Маршрут"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "routeNumber",
				label: "Номер маршрута",
				href: function (row) {
					return "nsi/routes/" + row.id + "/info";
				}
			}, {
				id: "cities",
				label: "Округ"
			}, {
				id: "district",
				label: "Район"
			}, {
				id: "routeTsKind",
				label: "Вид транспорта"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "routeNumber",
				type: "text",
				placeholder: "Номер маршрута"
			}, {
				name: "routeTsKindId",
				type: "select",
				placeholder: "Вид транспорта",
				load: RouteTsKindsService.getList
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить Маршрут",
					removeConfirm: "Вы действительно хотите удалить Маршрут?"
				},
				check: {
					isRemoveEnable: function (row) {
						return row['delete'] != undefined && !row.delete;
					}
				},
				gridScope: $scope.gridScope
			};

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти Маршрута",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "routeNumber",
				gridName: $state.current.name + '.routesgrid',
				load: $scope.gridScope.service.getPage,
				export: RoutesReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/routes/RouteEditDlg.html", "RouteEditCtrl", "Создание Маршрута", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении Маршрута", $scope.gridScope, $scope.gridScope.service.removeItem, true);

			$scope.gridScope.addSelItemColumn = true;

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete == true ? 'remove-text-record' : null;
			};

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete == true ? 'remove-text-record' : null;
			};

			$scope.gridScope.getRowClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete ? 'remove-record' : null;
			};
		}]);
