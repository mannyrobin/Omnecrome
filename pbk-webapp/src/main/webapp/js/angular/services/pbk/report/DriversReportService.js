angular.module('armada')
	.service('DriversReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/drivers?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
