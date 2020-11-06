'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:ValidationErrors
 * @description
 * Директива для вывода ошибок валидации.
 */
angular.module('armada').directive('arValidationErrors', function () {
	return {
		restrict: 'A',
		templateUrl: 'templates/directives/validationErrors.html'
	}
});