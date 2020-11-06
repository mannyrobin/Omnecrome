angular.module('armada')
	.controller('ContactlessCardsCtrl', ['$scope', 'ContactlessCardsService', 'ContactlessCardsReportService', 'GridService',
		'$state', 'isNotReadOnlyUser', 'DepartmentsService', 'EmployeesService',
		function ($scope, ContactlessCardsService, ContactlessCardsReportService, GridService,
				  $state, isNotReadOnlyUser, DepartmentsService, EmployeesService) {

			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			$scope.gridScope = $scope;
			$scope.gridScope.service = ContactlessCardsService;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				export: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "БСК",
					addTip: "Добавить БСК",
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "cardNumber",
				type: "text",
				label: "Номер карты",
				href: function (row) {
					return "nsi/contactless-cards/" + row.id + "/info";
				}
			}, {
				id: "uid",
				type: "text",
				label: "UID"
			}, {
				id: "owner",
				type: "text",
				label: "Владелец"
			}, {
				id: "dptName",
				type: "text",
				label: "Подразделение"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "cardNumber",
				type: "text",
				placeholder: "Номер карты"
			}, {
				name: "uid",
				type: "text",
				placeholder: "UID"
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
					removeTip: "Удалить БСК",
					removeConfirm: "Вы действительно хотите удалить БСК?"
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
				filterString: "Найти БСК",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "cardNumber",
				gridName: $state.current.name + '.ContactlessCardsGrid',
				load: $scope.gridScope.service.getPage,
				export: ContactlessCardsReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/contactless-cards/ContactlessCardEditDlg.html", "ContactlessCardEditCtrl", "Создание БСК", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении БСК", $scope.gridScope, $scope.gridScope.service.removeItem, true);

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
