angular.module('armada')
	.service('TsTypesReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/ts-types?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
