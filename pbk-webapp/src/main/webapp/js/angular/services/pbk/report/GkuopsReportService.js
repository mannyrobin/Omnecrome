angular.module('armada')
	.service('GkuopsReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/gkuops?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);