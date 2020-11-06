'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ExchangeObjectCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('ExchangeObjectCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'ExchangeObjectService',
		function ($controller, $scope, $stateParams, UtilService, ExchangeObjectService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = ExchangeObjectService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.vis.exhange-objects.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				],
				service: null,
			});
		}]);