angular.module('armada')
	.controller('EmployeesCtrl', ['$scope', 'EmployeesService', 'EmployeesReportService', 'DepartmentsService', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, EmployeesService, EmployeesReportService, DepartmentsService, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = EmployeesService;
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Права */
			$scope.gridScope.perms = {
				//add: isNotReadOnlyUser,
				edit: isNotReadOnlyUser,
				export: isNotReadOnlyUser,
				//remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Сотрудники",
					addTip: "Добавить сотрудника"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "surname",
				label: "ФИО",
				class: "col-md-5",
				get: function (row) {
					var result = '';
					if (row.surname) {
						result = row.surname;
					}
					if (row.name) {
						result = result + ' ' + row.name;
					}
					if (row.patronumic) {
						result = result + ' ' + row.patronumic;
					}
					return result;
				},
				href: function (row) {
					return "nsi/employees/" + row.id + "/info";
				}
			}, {
				id: "personalNumber",
				label: "Табельный номер",
				class: "col-md-1"
			}, {
				id: "departmentName",
				label: "Подразделение",
				class: "col-md-2"
			}, {
				id: "login",
				label: "Пользователь",
				class: "col-md-1"
			}, {
				id: "emplPost",
				label: "Должность",
				class: "col-md-2"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "departmentId",
				type: "select",
				placeholder: "Подразделение",
				load: function () {
					return DepartmentsService.getList([{
						name: "hasEmployee",
						value: "1",
						type: "text"
					}]);
				}
			}, {
				name: "name",
				type: "text",
				placeholder: "ФИО"
			}, {
				name: "loginFilter",
				type: "text",
				placeholder: "Пользователь"
			}, {
				name: "commonNumber",
				type: "text",
				placeholder: "Номер"
			}, {
				name: "forPlanUse",
				type: "yesno",
				placeholder: "Участвует в планировании"
			}, {
				name: "isFireEmp",
				type: "yesno",
				placeholder: "Уволен",
				defval: {id: 0, name: 'Нет'}
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти сотрудника",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					editTip: "Редактировать сотрудника",
					removeTip: "Удалить сотрудника",
					removeConfirm: "Вы действительно хотите удалить сотрудника?"
				},
				check: {
					isRemoveEnable: function (row) {
						return !row.delete;
					}
				},
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "surname",
				gridName: $state.current.name + '.employeesGrid',
				load: $scope.gridScope.service.getPage,
				export: EmployeesReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/employee/EmployeesEditDlg.html", "EmployeesEditCtrl", "Создание сотрудника", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении сотрудника", $scope.gridScope, $scope.gridScope.service.removeItem, true);

			$scope.gridScope.addSelItemColumn = true;

			$scope.gridScope.getRowClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete ? 'remove-record' : null;
			};

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				if (row.delete) {
					return 'remove-text-record';
				}
				return row.fireDate ? 'fire-user-record' : null;
			};
		}]);
