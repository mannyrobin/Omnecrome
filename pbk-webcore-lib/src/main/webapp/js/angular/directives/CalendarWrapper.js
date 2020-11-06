/**
 * @ngdoc directive
 * @name armada.directive:arCalendarWrapper
 * @description
 *
 * Директива, которая является оберткой календарю и добавляет ему функционал.
 */
angular.module('armada').directive('arCalendarWrapper', ['$timeout', 'AppConfig',
	function ($timeout, AppConfig) {
		function setColorToRow(selector, color) {
			var jDay = angular.element('.calendar').find('td[data-date="' + selector + '"].fc-day');
			if (!jDay.hasClass('fc-other-month')) {
				jDay.css("background-color", color);
			}
		}

		function setDaysForMonth(data) {
			if (data != null) {
				for (var i = 0; i < data.length; i++) {
					var selector = moment(data[i].workDate, 'DD.MM.YYYY').format("YYYY-MM-DD");
					switch (data[i].workDayTypeId) {
						case 1:
							setColorToRow(selector, AppConfig.CALENDAR_COLORS.weekday);
							break;
						case 2:
							setColorToRow(selector, AppConfig.CALENDAR_COLORS.weekend);
							break;
						case 3:
							setColorToRow(selector, AppConfig.CALENDAR_COLORS.holiday);
							break;
					}
				}
			}
		}

		return {
			scope: {
				data: "="
			},
			link: function (scope, element, attr) {
				scope.$on('calendar-re-render', function (event, data) {
					$timeout(function () {
						setDaysForMonth(scope.data);
					});
				});
			}
		};
	}]);