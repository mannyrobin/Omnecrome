angular.module('armada')
	.controller('OfficialCardsCtrl', ['$scope', 'OfficialCardsService', 'OfficialCardsReportService', 'GridService', '$state', 'isNotReadOnlyUser', 'DepartmentsService', 'EmployeesService',
		function ($scope, OfficialCardsService, OfficialCardsReportService, GridService, $state, isNotReadOnlyUser, DepartmentsService, EmployeesService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = OfficialCardsService;
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
					title: "Служебная карта контролера",
					addTip: "Добавить СКК",
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "cardNumber",
				type: "text",
				label: "Номер карты",
				href: function (row) {
					return "nsi/official-cards/" + row.id + "/info";
				},
				required: true
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
					removeTip: "Удалить СКК",
					removeConfirm: "Вы действительно хотите удалить СКК?"
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
				filterString: "Найти СКК",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "cardNumber",
				gridName: $state.current.name + '.OfficialCardsGrid',
				load: $scope.gridScope.service.getPage,
				export: OfficialCardsReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/official-cards/official-cardEditDlg.html", "OfficialCardEditCtrl", "Создание СКК", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении СКК", $scope.gridScope, $scope.gridScope.service.removeItem, true);

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
