'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:PickList
 * @description Директива для PickList
 */
angular.module('armada').directive('arCheckboxList',
	['$timeout', function ($timeout) {
		return {
			restrict: 'A',
			replace: true,
			scope: {
				items: "=",
				selectedItems: "=",
				load: "=",
				header: "="
			},
			controller: function ($scope) {

				$scope.toggle = function () {
					if ($scope.selectedItems.length == $scope.items.length) {
						$scope.selectedItems = [];
					} else {
						$scope.selectedItems = $scope.items.map(function (item) {
							return item.id;
						});
					}
				}

				$scope.select = function (id) {
					var index = $scope.selectedItems.indexOf(id);
					if (index > 0) {
						$scope.selectedItems.splice(index, 1);
					} else {
						$scope.selectedItems.push(id);
					}
				}

				$scope.load().then(function (response) {
					$scope.items = response.slice();
				});
			},

			templateUrl: 'templates/directives/checkboxList.html'
		}
	}]);