'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ExchangeConfigCtrl
 * @description
 *
 * Конфигурация взаимодействия с ВИС
 */
angular.module('armada')
	.controller('ExchangeConfigCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'ExchangeConfigsService',
		function ($controller, $scope, $stateParams, UtilService, ExchangeConfigsService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = ExchangeConfigsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.vis.exchange-configs.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				]
			});
		}]);