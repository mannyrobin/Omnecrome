angular.module('armada')
	.service('RouteTsKindsReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/route-ts-kinds?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
