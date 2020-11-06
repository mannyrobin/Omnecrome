angular.module('armada')
	.service('TaskWithdrawCardReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/tasks/withdrawn-cards?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
