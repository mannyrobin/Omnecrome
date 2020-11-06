'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:arMoneyField
 * @description
 * Директива для форматирования денежного поля поля
 */
angular.module('armada').directive('arMoneyField', function () {
	return {
		restrict: 'A',
		require: 'ngModel',
		replace: false,
		scope: {},
		controller: function ($scope) {

		},
		link: function (scope, element, attrs, ctrl) {
			ctrl.$parsers.push(function (viewModel) {
				if (viewModel == null || viewModel == "") {
					return null;
				}
				var normalizedString = viewModel.replace(/\s/g, '');
				var dot = normalizedString.indexOf('.');
				var period = null;
				if (dot != -1) {
					period = normalizedString.substr(dot, normalizedString.length);
					normalizedString = normalizedString.replace(period, period.substr(0, 3));
				}
				if (/^\d+(\.\d+)?$/.test(normalizedString)) {
					var num = Number(normalizedString);
					var money = (num).toMoney();
					if (period && money.indexOf('.') == -1)
						money += period.substring(0, 2);
					element[0].value = money;
					return num;
				}
			});
			ctrl.$formatters.push(function (modelValue) {
				return modelValue != null ? (modelValue).toMoney() : null;
			});
			ctrl.$validators.pattern = function (modelValue) {
				return modelValue == null || typeof modelValue === 'number';
			};
		}
	};
});