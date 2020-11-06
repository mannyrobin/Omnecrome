'use strict';

/**
 * @ngdoc function
 * @name armada.controller:UsersCtrl
 * @description
 *
 * Контроллер контрактов
 */
angular.module('armada')
	.controller('UsersCtrl', ['$scope', 'UsersService', '$modal', 'Notification', 'GridService', 'isNotReadOnlyUser', '$state', 'AppConfig',
		function ($scope, UsersService, $modal, Notification, GridService, isNotReadOnlyUser, $state, AppConfig) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = UsersService;

			$scope.userColors = AppConfig.USER_COLORS;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				remove: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Пользователи системы",
					addTip: "Добавить пользователя"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "login",
				label: "Логин",
				href: function (row) {
					return "admin/users/" + row.id + "/info";
				}
			}, {
				id: "name",
				label: "Имя"
			}, {
				id: "expirationDate",
				label: "Дата прекращения доступа",
				get: function (row) {
					if (!row.active) {
						return "заблокирован";
					}
					return row.expirationDate;
				}
			}, {
				id: "roles",
				label: "Роли",
				class: "col-md-3"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "login",
				type: "text",
				placeholder: "Логин"
			}, {
				name: "name",
				type: "text",
				placeholder: "Имя"
			}, {
				name: {
					fromName: "expirationDateFrom",
					toName: "expirationDateTo"
				},
				type: "range",
				placeholder: {
					from: "Начало периода",
					to: "Окончание периода"
				}
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти пользователей",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить пользователя",
					removeConfirm: "Вы действительно хотите удалить пользователя?"
				},
				check: {
					isRemoveEnable: function (row) {
						if (!row) {
							return false;
						}
						return !row.delete && !row.ldap && row.login != "ADMIN" && row.login != "SYSTEM";
					}
				},
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "expirationDate",
				gridName: $state.current.name + '.usersGrid',
				load: $scope.gridScope.service.getPage
			});

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/admin/UserEditDlg.html", "UserEditCtrl", "Создание пользователя", "sm", $scope.gridScope);
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении пользователя", $scope.gridScope, $scope.gridScope.service.removeItem, true);
			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return row.delete == true ? 'history-record' : null;
			};
			$scope.gridScope.getRowClassFunc = function (row) {
				if (!row) {
					return null;
				}
				if (row.delete) {
					return 'remove-record';
				}
				if (!row.active) {
					return "blocked-user";
				}
				if (row.ldap) {
					return "ldap-user"
				}
				return "inner-user";
			};
		}]);
