'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TaskReportFieldCtrl
 * @description
 *
 * Пол
 */
angular.module('armada')
	.controller('GraficCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'GraficsService',
		function ($controller, $scope, $stateParams, UtilService, GraficsService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = GraficsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.grafics.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				],
				service: null,
			});
		}]);