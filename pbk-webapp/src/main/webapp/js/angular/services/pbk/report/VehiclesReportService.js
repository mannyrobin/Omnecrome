angular.module('armada')
	.service('VehiclesReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/vehicles?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
