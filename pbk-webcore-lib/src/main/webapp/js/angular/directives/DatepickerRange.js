'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:DateRange
 * @description
 * Директива для обработки диапазона дат
 */
angular.module('armada').directive('arDatepickerRange', function () {
	return {
		restrict: 'A',
		require: 'ngModel',
		scope: {},
		controller: function ($scope) {
			$scope.fromDate = null;
			$scope.toDate = null;
			$scope.fromPlaceholder = null;
			$scope.toPlaceholder = null;
		},
		link: function (scope, element, attrs, ctrl) {
			scope.fromPlaceholder = attrs.fromPlaceholder;
			scope.toPlaceholder = attrs.toPlaceholder;

			if (attrs.required) {
				ctrl.$validators.required = function (modelValue, viewValue) {
					var value = modelValue || viewValue;
					return angular.isDefined(value) && value.fromDate != null && value.toDate != null;
				};
			}

			scope.$watch(function () {
				return scope.fromDate
			}, function (value) {
				ctrl.$setViewValue({fromDate: value, toDate: scope.toDate})
			});

			scope.$watch(function () {
				return scope.toDate;
			}, function (value) {
				ctrl.$setViewValue({fromDate: scope.fromDate, toDate: value})
			});

			scope.$watch(function () {
				return ctrl.$viewValue
			}, function () {
				ctrl.$render();
			}, true);

			ctrl.$render = function () {
				var fromDate = null;
				if (ctrl.$viewValue && ctrl.$viewValue.fromDate) {
					if (ctrl.$viewValue.fromDate <= ctrl.$viewValue.toDate)
						fromDate = ctrl.$viewValue.fromDate;
					else if (ctrl.$viewValue.toDate) {
						fromDate = ctrl.$viewValue.toDate;
					}
					else fromDate = ctrl.$viewValue.fromDate;
				}
				scope.fromDate = fromDate;

				var toDate = null;
				if (ctrl.$viewValue && ctrl.$viewValue.toDate) {
					if (ctrl.$viewValue.fromDate <= ctrl.$viewValue.toDate)
						toDate = ctrl.$viewValue.toDate;
					else toDate = ctrl.$viewValue.fromDate
				}
				scope.toDate = toDate;
			};
		},
		templateUrl: 'templates/directives/datepicker-range.html'
	}
});