angular.module('armada')
	.service('ParksReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/parks?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
