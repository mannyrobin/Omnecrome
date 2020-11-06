angular.module('armada')
	.service('AskpByMoveReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/askp-by-move?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
