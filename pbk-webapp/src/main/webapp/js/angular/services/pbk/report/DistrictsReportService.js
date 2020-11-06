angular.module('armada')
	.service('DistrictsReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/districts?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);
