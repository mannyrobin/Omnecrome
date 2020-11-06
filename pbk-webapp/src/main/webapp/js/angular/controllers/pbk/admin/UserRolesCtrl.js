angular.module('armada')
	.controller('UserRolesCtrl', ['$scope', 'UserRolesService', '$stateParams', 'Notification', 'GridService', '$state', 'userItem', 'isNotReadOnlyUser',
		function ($scope, UserRolesService, $stateParams, Notification, GridService, $state, userItem, isNotReadOnlyUser) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = UserRolesService;
			//var readonly = (angular.isDefined($scope.userInfo.isLdapUser) && $scope.userInfo.isLdapUser==true);

			/* Права */
			$scope.gridScope.perms = {
				add: !userItem.isLdap && isNotReadOnlyUser,
				remove: !userItem.isLdap && isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Роли пользователя",
					addTip: "Добавить роль"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-3"
			}, {
				id: "description",
				label: "Описание",
				class: "col-md-5"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "userId",
				type: "text",
				defval: $stateParams.itemId,
				hide: true
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "",
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
						if (!row || !row.name) {
							return false;
						}
						return row.name != "Роль по умолчанию";
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
				"pbk/admin/UserRoleAddDlg.html", "UserRoleAddCtrl", "Добавление роли", "sm", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении роли", $scope.gridScope, $scope.gridScope.service.removeItem);
		}]);