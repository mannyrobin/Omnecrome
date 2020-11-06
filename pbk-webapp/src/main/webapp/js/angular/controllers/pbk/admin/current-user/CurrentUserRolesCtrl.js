angular.module('armada')
	.controller('CurrentUserRolesCtrl', ['$scope', 'UserRolesService', '$stateParams', 'Notification', 'GridService', '$state',
		function ($scope, UserRolesService, $stateParams, Notification, GridService, $state) {
			var readonly = (angular.isDefined($scope.userInfo.isLdapUser) && $scope.userInfo.isLdapUser == true);
			$scope.gridScope = $scope;
			$scope.gridScope.service = UserRolesService;

			/* Права */
			$scope.gridScope.perms = {
				add: !readonly,
				remove: !readonly
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Роли пользователя",
					addTip: "Добавить роль",
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "cod",
				label: "Код"
			}, {
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
						if (!row || !row.cod) {
							return false;
						}
						return row.cod != "PBK_USERS";
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