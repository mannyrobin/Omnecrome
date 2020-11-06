'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:arValidateField
 * @description
 * Директива для валидации поля
 */
angular.module('armada').directive('arNumber', function () {
	var FLOAT_INTEGER_REGEXP = /^\d+(\.\d+)?$/;
	return {
		restrict: 'A',
		require: 'ngModel',
		scope: {},
		link: function (scope, element, attrs, ctrl) {
			var regexp = attrs.arNumber ? new RegExp(attrs.arNumber) : FLOAT_INTEGER_REGEXP;
			ctrl.$parsers.push(function (viewModel) {
				if (viewModel == null || viewModel == "") {
					return null;
				}
				var normalizedString = viewModel.replace(',', '.');
				if (regexp.test(normalizedString)) {
					return normalizedString;
				}
			});
			ctrl.$validators.pattern = function (modelValue) {
				return modelValue == null || !isNaN(modelValue);
			};
		}
	}
});