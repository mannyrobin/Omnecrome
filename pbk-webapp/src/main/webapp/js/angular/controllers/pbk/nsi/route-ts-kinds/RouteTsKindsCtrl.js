angular.module('armada')
	.controller('RouteTsKindsCtrl', ['$scope', 'RouteTsKindsService', 'RouteTsKindsReportService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, RouteTsKindsService, RouteTsKindsReportService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = RouteTsKindsService;
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
					title: "Вид транспорта маршрута",
					addTip: "Добавить вид транспорта маршрута",
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				href: function (row) {
					return "nsi/route-ts-kinds/" + row.id + "/info";
				}
			}, {
				id: "routeTsKind",
				label: "Вид транспорта маршрута в ВИС ГИС МГТ"
			}, {
				id: "description",
				label: "Описание"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "name",
				type: "text",
				placeholder: "Название"
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить вид транспорта маршрута",
					removeConfirm: "Вы действительно хотите удалить вид транспорта маршрута?"
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
				filterString: "Найти вид транспорта маршрута",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.RouteTsKindsGrid',
				load: $scope.gridScope.service.getPage,
				export: RouteTsKindsReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/route-ts-kinds/RouteTsKindEditDlg.html", "RouteTsKindEditCtrl", "Создание вида транспорта маршрута", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении вида транспорта маршрута", $scope.gridScope, $scope.gridScope.service.removeItem, true);

			$scope.gridScope.addSelItemColumn = true;

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
