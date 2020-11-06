'use strict';

/**
 * @ngdoc function
 * @name armada.controller:CountyCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('CountyCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'CountiesService',
		function ($controller, $scope, $stateParams, UtilService, CountiesService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = CountiesService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.counties.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'История', route: 'history'},
					{heading: 'Районы', route: 'districts'}
				],
				service: null,
			});
		}]);