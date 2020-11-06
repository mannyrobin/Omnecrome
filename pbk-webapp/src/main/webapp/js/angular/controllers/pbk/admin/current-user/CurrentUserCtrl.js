'use strict';

/**
 * @ngdoc function
 * @name armada.controller:UserCtrl
 * @description
 *
 * Сотрудник
 */
angular.module('armada')
	.controller('CurrentUserCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'UsersService', 'UserService',
		function ($controller, $scope, $stateParams, UtilService, UsersService, UserService) {
			$scope.getItemCrumb = function (item) {
				var result = '';
				if (item.name && item.name.length > 0) {
					result = result + ' ' + item.name;
				}
				if (result == '') {
					result = item.id;
				}
				return result;
			};

			$scope.getItem = UsersService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.curuser.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'Роли', route: 'roles'},
				],
				checkPermission: function (tab) {
					if ((tab.route == 'roles' && UserService.hasAnyRole(['ADMIN', 'DEBUG_TO_REPLACE'])) || tab.route != 'roles') {
						return true;
					} else {
						return false;
					}
					//TODO после отладки
					//return (tab.route=='roles' && UserService.hasAnyRole(['ADMIN', 'DEBUG_TO_REPLACE'])) || tab.route!='roles';
				}
			});

		}]);
