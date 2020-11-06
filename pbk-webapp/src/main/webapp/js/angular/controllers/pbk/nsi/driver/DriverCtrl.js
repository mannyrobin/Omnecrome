'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DriverCtrl
 * @description
 *
 * Водитель
 */
angular.module('armada')
	.controller('DriverCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'DriversService',
		function ($controller, $scope, $stateParams, UtilService, DriversService) {

			$scope.getItemCrumb = function (item) {
				var patronumicFirst = item.patronumic != null ? ' ' + item.patronumic[0] + '.' : '';
				return item.surname + ' ' + item.name[0] + '. ' + patronumicFirst;
			};

			$scope.getItem = DriversService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.drivers.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'История', route: 'history'}
				]
			});
		}]);