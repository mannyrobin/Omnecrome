'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:arDatetimepicker
 * @description
 * Директива для выбора даты и времени
 */
angular.module('armada').directive('arDatetimepicker', function () {
	return {
		restrict: 'A',
		scope: {
			disabledState: "=",
			readonly: "="
		},
		require: 'ngModel',
		link: function (scope, element, attrs, ctrl) {
			scope.placeholder = attrs.placeholder;
			scope.name = attrs.name;
			var dateTimePickerElement = $(element).children(".date");
			var params = {
				locale: 'ru'
			};
			if (angular.isDefined(attrs.dateFormat) && attrs.dateFormat != null) {
				params['format'] = attrs.dateFormat;
			}

			dateTimePickerElement.datetimepicker(params).on('dp.change', function (data) {
				ctrl.$setViewValue(data.date ? moment(data.date) : null)
			});

			if (scope.disabledState) {
				$(dateTimePickerElement).data('DateTimePicker').disable();
			}

			//test
			if (scope.readonly == true) {
				$(dateTimePickerElement).data('DateTimePicker').disable();
			}

			scope.$watch(function () {
				return scope.readonly
			}, function (newValue, oldValue) {
				if (newValue != oldValue) {
					if (scope.readonly == true) {
						$(dateTimePickerElement).data('DateTimePicker').disable();
					} else {
						$(dateTimePickerElement).data('DateTimePicker').enable();
					}
				}
			});

			scope.$watch(function () {
				return scope.disabledState
			}, function (newValue, oldValue) {
				if (newValue != oldValue) {
					if (newValue) {
						$(dateTimePickerElement).data('DateTimePicker').disable();
					} else {
						$(dateTimePickerElement).data('DateTimePicker').enable();
					}
				}
			});

			ctrl.$render = function () {
				var date = null;
				if (ctrl.$viewValue) {
					date = moment(ctrl.$viewValue);
				}
				$(dateTimePickerElement).data('DateTimePicker').date(date);
			};
		},
		templateUrl: 'templates/directives/datetimepicker.html'
	}
});