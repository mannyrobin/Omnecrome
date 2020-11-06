'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:DateRange
 * @description
 * Директива для обработки диапазона дат
 */
angular.module('armada').directive('arCombobox', function () {
	return {
		restrict: 'A',
		replace: true,
		scope: {
			selectedItem: "=",
			items: "="
		},
		controller: function ($scope) {
			$scope.selectItem = function (item) {
				$scope.selectedItem = item;
			};
		},
		templateUrl: 'templates/directives/combobox.html'
	}
});