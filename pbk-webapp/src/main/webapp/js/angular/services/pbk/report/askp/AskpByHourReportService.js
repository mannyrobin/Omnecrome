angular.module('armada')
	.service('AskpByHourReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/askp-by-hour?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
