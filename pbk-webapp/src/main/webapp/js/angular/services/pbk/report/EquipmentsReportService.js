angular.module('armada')
	.service('EquipmentsReportService', ['Restangular', 'UrlService', 'GridService', function (Restangular, UrlService, GridService) {

		return {
			exportReport: function (format, filters) {
				window.location.href = "/api/pbk/reports/aspose/nsi/equipments?format=" + format + GridService.getFilterParamsForUrl(filters);
			}
		};
	}]);