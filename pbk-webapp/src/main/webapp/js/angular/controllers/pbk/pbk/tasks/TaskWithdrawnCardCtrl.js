'use strict';

/**
 * @ngdoc function
 * @name armada.controller:TaskCtrl
 * @description
 *
 * Округ
 */
angular.module('armada')
	.controller('TaskWithdrawnCardCtrl', ['$controller', '$scope', '$stateParams', 'TaskWithdrawnCardsService', 'BsosService',
		function ($controller, $scope, $stateParams, TaskWithdrawnCardsService, BsosService) {

			$scope.getItemCrumb = function (item) {
				return item;
			};

			$scope.getItem = function (id) {
				return TaskWithdrawnCardsService.getItem(id).then(function (response) {
					return BsosService.getItem(response.bsoId).then(function (response) {
						return response.bsoNumber;
					});
				});
			}

			$controller('BaseTabsCtrl', {$scope: $scope});
			$scope.init({
				route: "app.main.pbk.withdrawn-cards.detail",
				tabs: [
					{heading: 'Общее', route: 'info'}
				]
			});
		}]);
