/**
 * Сервис режима работы сотрудника.
 */
angular.module('armada')
	.service('EmployeeCalendarService', ['Restangular', 'UrlService', function (Restangular, UrlService) {
		var employeeCalendarServiceUrl = UrlService.getApiPrefix() + 'nsi/employee/calendar';
		return {
			getCalendarForPeriod: function (employeeId, from, to) {
				var params = {employeeId: employeeId, from: from, to: to};
				if (params && params != null) {
					angular.forEach(params, function (value, name) {
						params[name] = value;
					});
				}
				return Restangular.one(employeeCalendarServiceUrl).get(params);
			}
		};
	}]);
