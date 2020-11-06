'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:arFormatField
 * @description
 * Директива для валидации поля по формату
 */
angular.module('armada').directive('arFormat', function () {
	return {
		restrict: 'A',
		require: 'ngModel',
		scope: {},
		link: function (scope, element, attrs, ctrl) {
			var regexp = attrs.arFormat ? new RegExp(attrs.arFormat) : null;
			ctrl.$parsers.push(function (viewModel) {
				if (viewModel == null || viewModel == "") {
					return null;
				}
				if (regexp == null || regexp.test(viewModel)) {
					return viewModel;
				}
			});
			ctrl.$validators.pattern = function (modelValue) {
				return modelValue == null || regexp == null || regexp.test(modelValue);
			};
		}
	}
});