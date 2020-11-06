angular.module('armada')
	.service('RoutesReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/routes?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
