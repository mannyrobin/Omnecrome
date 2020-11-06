angular.module('armada')
	.controller('RolesCtrl', ['$scope', 'RolesService', '$modal', 'Notification', 'GridService', '$state', 'isNotReadOnlyUser',
		function ($scope, RolesService, $modal, Notification, GridService, $state, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = RolesService;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Журнал ролей",
					addTip: "Добавить роль"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-3",
				href: function (row) {
					return "admin/roles/" + row.id + "/info";
				}
			}, {
				id: "description",
				label: "Описание",
				class: "col-md-5"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "name",
				type: "text",
				placeholder: "Название"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти роли",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить роль",
					removeConfirm: "Вы действительно хотите удалить роль?"
				},
				check: {
					isRemoveEnable: function (row) {
						if (!row || !row.id) {
							return false;
						}
						return row.id != 0;
					}
				},
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.settingsGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/admin/RoleEditDlg.html", "RolesEditCtrl", "Создание роли", "sm", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении роли", $scope.gridScope, $scope.gridScope.service.removeItem);
		}]);