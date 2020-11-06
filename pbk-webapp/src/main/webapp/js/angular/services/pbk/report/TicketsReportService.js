angular.module('armada')
	.service('TicketsReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/tickets?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
