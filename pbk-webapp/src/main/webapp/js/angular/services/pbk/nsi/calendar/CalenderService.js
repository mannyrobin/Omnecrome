/**
 * Сервис календаря.
 */
angular.module('armada')
	.service('CalendarService', ['Restangular', 'UrlService', function (Restangular, UrlService) {
		var calendarServiceUrl = UrlService.getApiPrefix() + 'nsi/calendar';
		return {
			getCalendarForPeriod: function (from, to) {
				var params = {from: from, to: to};
				if (params && params != null) {
					angular.forEach(params, function (value, name) {
						params[name] = value;
					});
				}
				return Restangular.one(calendarServiceUrl).get(params);
			},
			setCalendarDay: function (day) {
				return Restangular.one(calendarServiceUrl).post("", day);
			},
			fillCalendarForPeriod: function (from, to) {
				var params = {from: from, to: to};
				if (params && params != null) {
					angular.forEach(params, function (value, name) {
						params[name] = value;
					});
				}
				return Restangular.one(calendarServiceUrl + '/fill').post("", "", params);
			}
		};
	}]);
