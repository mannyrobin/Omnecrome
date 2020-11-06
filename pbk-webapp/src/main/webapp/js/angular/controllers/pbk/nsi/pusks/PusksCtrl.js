angular.module('armada')
	.controller('PusksCtrl', ['$scope', 'PusksService', 'PusksReportService', 'GridService', '$state', 'isNotReadOnlyUser', 'DepartmentsService', 'EmployeesService',
		function ($scope, PusksService, PusksReportService, GridService, $state, isNotReadOnlyUser, DepartmentsService, EmployeesService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = PusksService;
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
					title: "ПУсК",
					addTip: "Добавить ПУсК"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "puskNumber",
				label: "Номер ПУсК",
				href: function (row) {
					return "nsi/pusks/" + row.id + "/info";
				}
			}, {
				id: "puskModel",
				label: "Модель ПУсК"
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
				name: "puskNumber",
				type: "text",
				placeholder: "Номер ПУсК"
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
					removeTip: "Удалить ПУсК",
					removeConfirm: "Вы действительно хотите удалить ПУсК?"
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
				filterString: "Найти ПУсК",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "puskNumber",
				gridName: $state.current.name + '.puskgrid',
				load: $scope.gridScope.service.getPage,
				export: PusksReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/pusks/PuskEditDlg.html", "PuskEditCtrl", "Создание ПУсК", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении ПУсК", $scope.gridScope, $scope.gridScope.service.removeItem, true);

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
