'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TicketCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('TicketCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'TicketsService',
		function ($controller, $scope, $stateParams, UtilService, TicketsService) {

			$scope.getItemCrumb = function (item) {
				return item.code;
			};

			$scope.getItem = TicketsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.tickets.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'История', route: 'history'}
				]
			});
		}]);