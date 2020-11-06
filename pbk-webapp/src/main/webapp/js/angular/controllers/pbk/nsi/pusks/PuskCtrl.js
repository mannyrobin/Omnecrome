'use strict';

/**
 * @ngdoc function
 * @name armada.controller:PuskCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('PuskCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'PusksService',
		function ($controller, $scope, $stateParams, UtilService, PusksService) {

			$scope.getItemCrumb = function (item) {
				return item.puskNumber;
			};

			$scope.getItem = PusksService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.pusks.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'История', route: 'history'},
					{heading: 'История владения', route: 'ownershistory'}
				]
			});
		}]);