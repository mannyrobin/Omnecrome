'use strict';

/**
 * @ngdoc function
 * @name armada.controller:VisCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('VisCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'VisService',
		function ($controller, $scope, $stateParams, UtilService, VisService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = VisService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.vis.viss.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				],
				service: null,
			});
		}]);