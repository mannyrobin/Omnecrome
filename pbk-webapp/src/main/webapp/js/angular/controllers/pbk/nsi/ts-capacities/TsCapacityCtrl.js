'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TsCapacityCtrl
 * @description
 *
 * Вместимость ТС
 */
angular.module('armada')
	.controller('TsCapacityCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'TsCapacitiesService',
		function ($controller, $scope, $stateParams, UtilService, TsCapacitiesService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = TsCapacitiesService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.ts-capacities.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				]
			});
		}]);