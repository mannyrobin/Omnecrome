angular.module('armada')
	.controller('DepartmentsCtrl', ['$scope', 'DepartmentsService', 'DepartmentsReportService', '$modal', 'Notification', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, DepartmentsService, DepartmentsReportService, $modal, Notification, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = DepartmentsService;
			$scope.isNotReadOnlyUser = isNotReadOnlyUser;

			/* Права */
			$scope.gridScope.perms = {
				//add: isNotReadOnlyUser,
				export: isNotReadOnlyUser,
				//remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Подразделения"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-3",
				href: function (row) {
					return "nsi/departments/" + row.id + "/info";
				}
			}, {
				id: "parentName",
				label: "Родительское подразделение",
				class: "col-md-3"
			}, {
				id: "description",
				label: "Описание",
				class: "col-md-3"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "name",
				type: "text",
				placeholder: "Название"
			}, {
				name: "forPlanUse",
				type: "yesno",
				placeholder: "Участвует в планировании"
			}];

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить подразделение",
					removeConfirm: "Вы действительно хотите удалить подразделение?"
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
				filterString: "Найти подразделения",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.departmentsGrid',
				load: $scope.gridScope.service.getPage,
				export: DepartmentsReportService.exportReport
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/nsi/departments/DepartmentEditDlg.html", "DepartmentEditCtrl", "Создание подразделения", "md", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении подразделения", $scope.gridScope, $scope.gridScope.service.removeItem, true);

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
