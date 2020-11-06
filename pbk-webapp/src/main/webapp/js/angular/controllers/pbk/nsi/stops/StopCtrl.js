'use strict';

/**
 * @ngdoc function
 * @name armada.controller:StopCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('StopCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'StopsService',
		function ($controller, $scope, $stateParams, UtilService, StopsService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = StopsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.stops.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'История', route: 'history'},
					{heading: 'Местоположение', route: 'location'}
				],
				service: null,
			});
		}]);