angular.module('armada')
	.service('TsCapacitiesReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/ts-capacities?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
