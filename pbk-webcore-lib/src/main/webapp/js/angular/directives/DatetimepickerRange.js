'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:DateRange
 * @description
 * Директива для обработки диапазона дат и времени
 */
angular.module('armada').directive('arDatetimepickerRange', function () {
	return {
		restrict: 'A',
		require: 'ngModel',
		scope: {},
		controller: function ($scope) {
			$scope.fromDate = null;
			$scope.toDate = null;
			$scope.fromPlaceholder = null;
			$scope.toPlaceholder = null;
			$scope.fromDateName = null;
			$scope.toDateName = null;
		},
		link: function (scope, element, attrs, ctrl) {
			scope.fromPlaceholder = attrs.fromPlaceholder;
			scope.toPlaceholder = attrs.toPlaceholder;
			scope.name = attrs.name;
			if (scope.name) {
				scope.fromDateName = "from-" + scope.name;
				scope.toDateName = "to-" + scope.name;
			}

			if (attrs.required) {
				ctrl.$validators.required = function (modelValue, viewValue) {
					var value = modelValue || viewValue;
					return value.fromDate != null && value.toDate != null;
				};
			}

			scope.$watch(function () {
				return scope.fromDate
			}, function (newValue, oldValue) {
				if (newValue != oldValue) {
					ctrl.$setValidity(scope.fromDateName, moment(newValue).isValid());
					ctrl.$setViewValue({fromDate: newValue, toDate: scope.toDate});
				}
			});

			scope.$watch(function () {
				return scope.toDate;
			}, function (newValue, oldValue) {
				if (newValue != oldValue) {
					ctrl.$setValidity(scope.toDateName, moment(newValue).isValid());
					ctrl.$setViewValue({fromDate: scope.fromDate, toDate: newValue})
				}
			});

			scope.$watch(function () {
				return ctrl.$viewValue
			}, function () {
				ctrl.$render();
			}, true);

			ctrl.$render = function () {
				var fromDate = null;
				if (ctrl.$viewValue && ctrl.$viewValue.fromDate) {
					fromDate = ctrl.$viewValue.fromDate
				}
				scope.fromDate = fromDate;

				var toDate = null;
				if (ctrl.$viewValue && ctrl.$viewValue.toDate) {
					toDate = ctrl.$viewValue.toDate
				}
				scope.toDate = toDate;
			};
		},
		templateUrl: 'templates/directives/datetimepicker-range.html'
	}
});