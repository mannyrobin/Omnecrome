angular.module('armada')
	.service('EmployeesReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/employees?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);