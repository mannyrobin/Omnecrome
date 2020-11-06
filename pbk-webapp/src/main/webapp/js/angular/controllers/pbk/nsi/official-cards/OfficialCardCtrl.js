'use strict';

/**
 * @ngdoc function
 * @name armada.controller:OfficialCardCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('OfficialCardCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'OfficialCardsService',
		function ($controller, $scope, $stateParams, UtilService, OfficialCardsService) {

			$scope.getItemCrumb = function (item) {
				return item.cardNumber;
			};

			$scope.getItem = OfficialCardsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.official-cards.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'История', route: 'history'},
					{heading: 'История владения', route: 'ownershistory'}
				],
				service: null
			});
		}]);