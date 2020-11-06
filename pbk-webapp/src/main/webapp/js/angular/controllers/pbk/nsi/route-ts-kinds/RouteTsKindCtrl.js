'use strict';

/**
 * @ngdoc function
 * @name armada.controller:RouteTsKindsCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('RouteTsKindCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'RouteTsKindsService',
		function ($controller, $scope, $stateParams, UtilService, RouteTsKindsService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = RouteTsKindsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				RouteTsKinds: "nsi.route-ts-kinds.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				],
				service: null
			});
		}]);