angular.module('armada')
	.service('DepartmentsReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/departments?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
