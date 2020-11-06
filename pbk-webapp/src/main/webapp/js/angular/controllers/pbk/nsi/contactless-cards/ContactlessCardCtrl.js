'use strict';

/**
 * @ngdoc function
 * @name armada.controller:ContactlessCardCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('ContactlessCardCtrl', ['$controller', '$scope', '$stateParams', 'UtilService', 'ContactlessCardsService',
		function ($controller, $scope, $stateParams, UtilService, ContactlessCardsService) {

			$scope.getItemCrumb = function (item) {
				return item.cardNumber;
			};

			$scope.getItem = ContactlessCardsService.getItem;

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.nsi.contactless-cards.detail",
				tabs: [
					{heading: 'Общее', route: 'info'},
					{heading: 'История', route: 'history'},
					{heading: 'История владения', route: 'ownershistory'}
				],
				service: null,
			});
		}]);