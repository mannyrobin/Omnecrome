'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:arValidateField
 * @description
 * Директива для валидации поля
 */
angular.module('armada').directive('arLongNumber', function () {
	var INTEGER_REGEXP = /^\d{0,17}$/;
	return {
		restrict: 'A',
		require: 'ngModel',
		scope: {},
		link: function (scope, element, attrs, ctrl) {
			var regexp = attrs.arNumber ? new RegExp(attrs.arNumber) : INTEGER_REGEXP;
			ctrl.$parsers.push(function (viewModel) {
				if (viewModel == null || viewModel == "") {
					return null;
				}
				if (regexp.test(viewModel)) {
					return Number(viewModel);
				}
			});
			ctrl.$validators.pattern = function (modelValue) {
				return modelValue == null || typeof modelValue === 'number';
			};
		}
	}
});