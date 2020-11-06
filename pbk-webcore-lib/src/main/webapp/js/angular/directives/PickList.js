'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:PickList
 * @description Директива для PickList
 */
angular.module('armada').directive('arPickList',
	['$timeout', function ($timeout) {
		return {
			restrict: 'A',
			replace: true,
			scope: {
				pickList: "="
			},
			controller: function ($scope) {

				$scope.toActive = function () {
					$scope.pickList.active.action(
						{id: $scope.pickList.itemId, ids: $scope.pickList.noActive.selectedItems})
						.then(function () {
							$scope.refresh();
						});
				}

				$scope.toNoActive = function () {
					$scope.pickList.noActive.action(
						{id: $scope.pickList.itemId, ids: $scope.pickList.active.selectedItems})
						.then(function () {
							$scope.refresh();
						});
				}

				$scope.refresh = function () {
					$scope.pickList.noActive.load().then(function (response) {
						$scope.pickList.noActive.items = response.slice();
						$scope.pickList.noActive.selectedItems = [];
					});
					$scope.pickList.active.load().then(function (response) {
						$scope.pickList.active.items = response.slice();
						$scope.pickList.active.selectedItems = [];
					});
				}
			},
			templateUrl: 'templates/directives/pickList.html'
		}
	}]);