angular.module('armada')
	.service('So23ReportService', ['BaseSoReportService', function (BaseSoReportService) {
		return {
			exportReport: function (format, filters) {
				window.location.href = BaseSoReportService.getBaseSoUrl(23, format, filters, this.defaultNumber, this.defaultStart, !this.defaultReverse);
			}
		};
	}]);
