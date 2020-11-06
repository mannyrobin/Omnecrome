'use strict';

/**
 * @ngdoc service
 * @name armada.PermissionService
 * @description
 *
 * ������ ����������.
 */
angular.module('armada')
	.service('PermissionService', ['$rootScope', function ($rootScope) {
		var permissions = null;
		return {
			setPermission: function (newPermissions) {
				permissions = newPermissions;
				$rootScope.hasPermission = this.hasPermission;
				$rootScope.hasAnyPermission = this.hasAnyPermission;
				$rootScope.ADMIN_GRANTS = ["Главный администратор", "Системные администраторы", "PC_FULL_ACCESS", "CC_FULL_ACCESS", "MCC_FULL_ACCESS"];
			},
			/**
			 * Проверяет, есть ли роль, по ключу.
			 *
			 * @param name - название роли
			 */
			hasPermission: function (name) {
				for (var i = 0; i < permissions.length; i++) {
					if (permissions[i] == name) {
						return true;
					}
				}
				return false;
			},

			hasAnyPermission: function (names) {
				for (var i = 0; i < permissions.length; i++) {
					for (var j = 0; j < names.length; j++) {
						if (permissions[i] == names[j]) {
							return true;
						}
					}
				}
				return false;
			}
		};
	}]);
