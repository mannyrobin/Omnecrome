angular.module('armada')
	.service('So9ReportService', ['BaseSoReportService', function (BaseSoReportService) {
		return {
			exportReport: function (format, filters) {
				window.location.href = BaseSoReportService.getBaseSoUrl(9, format, filters, this.defaultNumber, this.defaultStart, !this.defaultReverse);
			}
		};
	}]);
