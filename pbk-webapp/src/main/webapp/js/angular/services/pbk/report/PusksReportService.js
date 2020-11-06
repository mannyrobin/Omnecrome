angular.module('armada')
	.service('PusksReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/pusks?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
