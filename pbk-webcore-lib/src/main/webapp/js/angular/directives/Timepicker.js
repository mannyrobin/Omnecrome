'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:Timepicker
 * @description
 * Директива для добавления таймпикера.
 */
angular.module('armada').directive('arTimepicker', [function () {
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
				locale: 'ru',
				format: 'HH:mm'
			};

			dateTimePickerElement.datetimepicker(params).on('dp.change', function (data) {
				ctrl.$setViewValue(data.date ? getDatetime(moment(data.date)) : null)
			});

			function getDatetime(momentTime) {
				var commonDate = moment("1970-01-01 00:00:00.000");
				commonDate.hours(momentTime.hours());
				commonDate.minutes(momentTime.minutes());
				commonDate.seconds(momentTime.seconds());
				return commonDate;
			}

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

			if (scope.disabledState) {
				$(dateTimePickerElement).data('DateTimePicker').disable();
			}

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
				if (angular.isDefined(ctrl.$viewValue)) {
					date = moment(ctrl.$viewValue);
				}
				$(dateTimePickerElement).data('DateTimePicker').date(date);
			};
		},
		templateUrl: 'templates/directives/datetimepicker.html'
	}
}]);