angular.module('armada')
	.service('So16ReportService', ['BaseSoReportService', function (BaseSoReportService) {
		return {
			exportReport: function (format, filters) {
				window.location.href = BaseSoReportService.getBaseSoUrl(16, format, filters, this.defaultNumber, this.defaultStart, !this.defaultReverse);
			}
		};
	}]);
