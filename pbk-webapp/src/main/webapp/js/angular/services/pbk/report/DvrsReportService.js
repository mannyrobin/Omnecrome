angular.module('armada')
	.service('DvrsReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/dvrs?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);