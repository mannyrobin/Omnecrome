angular.module('armada')
	.service('CountiesReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/counties?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
