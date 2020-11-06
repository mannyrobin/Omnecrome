angular.module('armada')
	.service('AskpByStopReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/askp-by-stop?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
