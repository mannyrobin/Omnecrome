angular.module('armada')
	.controller('DvrsCtrl', ['$scope', 'DvrsService', 'DvrsReportService', 'GridService', '$state', 'isNotReadOnlyUser', 'DepartmentsService', 'EmployeesService',
		function ($scope, DvrsService, DvrsReportService, GridService, $state, isNotReadOnlyUser, DepartmentsService, EmployeesService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = DvrsService;
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				export: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Видеорегистраторы",
					addTip: "Добавить видеорегистратор"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "dvrNumber",
				label: "Номер видеорегистратора",
				href: function (row) {
					return "nsi/dvrs/" + row.id + "/info";
				}
			}, {
				id: "dvrModel",
				label: "Модель видеорегистратора"
			}, {
				id: "owner",
				type: "text",
				label: "Владелец"
			}, {
				id: "dptName",
				type: "text",
				label: "Подразделение"
			}, {
				id: "description",
				label: "Описание"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "dvrNumber",
				type: "text",
				placeholder: "Номер видеорегистратора"
			}, {
				name: "departmentId",
				type: "select",
				placeholder: "Подразделение",
				load: function () {
					return DepartmentsService.getList([]);
				}
			}, {
				name: "employeeId",
				type: "select",
				placeholder: "Владелец",
				load: EmployeesService.getList
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить видеорегистратор",
					removeConfirm: "Вы действительно хотите удалить видеорегистратор?"
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
				filterString: "Найти видеорегистратор",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "dvrNumber",
				gridName: $state.current.name + '.dvrgrid',
				load: $scope.gridScope.service.getPage,
				export: DvrsReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/dvrs/DvrEditDlg.html", "DvrEditCtrl", "Создание видеорегистратора", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении видеорегистратора", $scope.gridScope, $scope.gridScope.service.removeItem, true);

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
