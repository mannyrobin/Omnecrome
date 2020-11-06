'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ShiftCtrl
 * @description
 *
 * Смена
 */
angular.module('armada')
	.controller('ShiftCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'ShiftsService',
		function ($controller, $scope, $stateParams, UtilService, ShiftsService) {

			$scope.getItemCrumb = function (item) {
				return item.name;
			};

			$scope.getItem = ShiftsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.shifts.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'Резерв', route: 'reserve'}
				]
			});
		}]);