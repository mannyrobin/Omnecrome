'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ParkCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('ParkCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'ParksService',
		function ($controller, $scope, $stateParams, UtilService, ParksService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = ParksService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.parks.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'Водители', route: 'drivers'},
					{heading: 'История', route: 'history'}
				]
			});
		}]);