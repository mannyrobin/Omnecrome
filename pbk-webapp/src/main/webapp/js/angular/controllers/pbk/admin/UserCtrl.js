'use strict';

/**
 * @ngdoc function
 * @name armada.controller:UserCtrl
 * @description
 *
 * Сотрудник
 */
angular.module('armada')
	.controller('UserCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'UsersService',
		function ($controller, $scope, $stateParams, UtilService, UsersService) {

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
				route: "app.main.admin.users.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'Роли', route: 'roles'},
				]
			});
		}]);
