'use strict';

/**
 * @ngdoc function
 * @name armada.controller:DutyCtrl
 * @description
 *
 * Наряд
 */
angular.module('armada')
	.controller('DutyCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'DutiesService',
		function ($controller, $scope, $stateParams, UtilService, DutiesService) {

			$scope.getItemCrumb = function (item) {
				return item.easuFhdRouteCode;
			};

			$scope.getItem = DutiesService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.duties.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				],
				service: null,
			});
		}]);