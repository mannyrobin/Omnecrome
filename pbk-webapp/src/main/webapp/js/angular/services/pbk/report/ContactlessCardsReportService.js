angular.module('armada')
	.service('ContactlessCardsReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/contactless-cards?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
