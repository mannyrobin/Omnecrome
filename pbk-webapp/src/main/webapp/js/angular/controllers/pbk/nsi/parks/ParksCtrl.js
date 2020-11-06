angular.module('armada')
	.controller('ParksCtrl', ['$scope', 'ParksService', 'ParksReportService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, ParksService, ParksReportService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = ParksService;
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Права */
			$scope.gridScope.perms = {
				//add: isNotReadOnlyUser,
				edit: isNotReadOnlyUser,
				//remove: isNotReadOnlyUser,
				export: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Эксплуатационные филиалы",
					addTip: "Добавить эксплуатационный филиал"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-3",
				href: function (row) {
					return "nsi/parks/" + row.id + "/info";
				}
			}, {
				id: "parkNumber",
				label: "Номер парка",
				class: "col-md-1"
			}, {
				id: "tsKind",
				label: "Вид транспорта",
				class: "col-md-2"
			}, {
				id: "gmParkId",
				label: "ГИС МГТ ИД парка",
				class: "col-md-1"
			}, {
				id: "asduDepotId",
				label: "ID парка в ВИС АСДУ",
				class: "col-md-2"
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
					removeTip: "Удалить эксплуатационный филиал",
					removeConfirm: "Вы действительно хотите удалить эксплуатационный филиал?"
				},
				check: {
					isRemoveEnable: function (row) {
						return !row.delete;
					}
				},
				gridScope: $scope.gridScope
			};

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти эксплуатационный филиал",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.parksgrid',
				load: $scope.gridScope.service.getPage,
				export: ParksReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/parks/ParkEditDlg.html", "ParkEditCtrl", "Создание эксплуатационного филиала", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении эксплуатационного филиала", $scope.gridScope, $scope.gridScope.service.removeItem);

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
