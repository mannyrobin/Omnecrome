'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ExchangeCtrl
 * @description
 *
 * Взаимодействие с ВИС
 */
angular.module('armada')
	.controller('ExchangeCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'ExchangesService',
		function ($controller, $scope, $stateParams, UtilService, ExchangesService) {

			$scope.getItemCrumb = function (item) {
				return item.exchangeObjectName;
			};

			$scope.getItem = ExchangesService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.vis.exchanges.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'Взаимодействия', route: 'send'},
				]
			});
		}]);