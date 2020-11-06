angular.module('armada')
	.service('OfficialCardsReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/official-cards?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
