angular.module('armada')
	.service('StopsReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/stops?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
