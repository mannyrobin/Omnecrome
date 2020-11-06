'use strict';

/**
 * @ngdoc directive
 * @name armada.directive:arDaymonthpicker
 * @description
 * Директива для выбора дня и месяца (без года)
 */
angular.module('armada').directive('arDaymonthpicker', function () {
	return {
		restrict: 'A',
		scope: {
			disabledState: "="
		},
		require: 'ngModel',
		link: function (scope, element, attrs, ctrl) {
			scope.placeholder = attrs.placeholder;
			scope.name = attrs.name;
			var dateTimePickerElement = $(element).children(".date");
			var params = {
				locale: 'ru',
				format: 'DD.MM'
			};

			dateTimePickerElement.datetimepicker(params).on('dp.change', function (data) {
				ctrl.$setViewValue(data.date ? getDatetime(moment(data.date)) : null)
			});

			function getDatetime(momentTime) {
				var commonDate = moment("1970-01-01 00:00:00.000");
				commonDate.month(momentTime.month());
				commonDate.date(momentTime.date());
				return commonDate;
			}

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
				if (ctrl.$viewValue) {
					date = moment(ctrl.$viewValue);
				}
				$(dateTimePickerElement).data('DateTimePicker').date(date);
			};
		},
		templateUrl: 'templates/directives/datetimepicker.html'
	}
});
