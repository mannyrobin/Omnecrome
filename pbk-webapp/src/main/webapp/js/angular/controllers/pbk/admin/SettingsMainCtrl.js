'use strict';

/**
 * @ngdoc function
 * @name armada.controller:UserCtrl
 * @description
 *
 * Сотрудник
 */
angular.module('armada')
	.controller('SettingsMainCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'SettingsService',
		function ($controller, $scope, $stateParams, UtilService, SettingsService) {
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

			$scope.getItem = SettingsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.admin.settings.detail",
				tabs: [
					{heading: 'Общие', route: 'common'},
					{heading: 'ВИС', route: 'vis'},
					{heading: 'Active Directory', route: 'ad'},
				]
			});
		}]);
