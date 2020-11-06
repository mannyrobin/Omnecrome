'use strict';

/**
 * @ngdoc function
 * @name armada.controller:RoleCtrl
 * @description
 *
 * Сотрудник
 */
angular.module('armada')
	.controller('RoleCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'RolesService',
		function ($controller, $scope, $stateParams, UtilService, RolesService) {
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

			$scope.getItem = RolesService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.admin.roles.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'Права', route: 'permissions'},
				]
			});
		}]);