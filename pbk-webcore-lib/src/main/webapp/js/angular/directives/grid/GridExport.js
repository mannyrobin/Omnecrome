'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:DateRange
 * @description
 * Директива для обработки диапазона дат
 */
angular.module('armada').directive('arGridExport', ['$timeout', function ($timeout) {
	return {
		restrict: 'A',
		replace: true,
		scope: {
			exportAction: '=',
			exportFormats: '='
		},
		controller: function ($scope) {
			$scope.exportFormat = $scope.exportFormats[0];

			$scope.selectExportFormat = function (format) {
				$scope.exportFormat = format;
				return false;
			}
		},
		templateUrl: 'templates/directives/grid/gridExport.html'
	}
}]);