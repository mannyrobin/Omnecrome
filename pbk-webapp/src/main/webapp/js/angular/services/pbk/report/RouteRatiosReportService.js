angular.module('armada')
	.service('RouteRatiosReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/routerates?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
