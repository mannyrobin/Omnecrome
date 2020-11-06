'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TSCtrl
 * @description
 *
 * ТС
 */
angular.module('armada')
	.controller('VenicleCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'VenicleService',
		function ($controller, $scope, $stateParams, UtilService, VenicleService) {

			$scope.getItemCrumb = function (item) {
				return item.depoNumber;
			};

			$scope.getItem = VenicleService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.venicles.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'История', route: 'history'}
				]
			});
		}]);