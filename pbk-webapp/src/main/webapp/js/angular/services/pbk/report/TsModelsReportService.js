angular.module('armada')
	.service('TsModelsReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/ts-models?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
