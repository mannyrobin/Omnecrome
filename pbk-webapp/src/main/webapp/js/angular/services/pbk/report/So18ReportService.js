angular.module('armada')
	.service('So18ReportService', ['BaseSoReportService', function (BaseSoReportService) {
		return {
			exportReport: function (format, filters) {
				window.location.href = BaseSoReportService.getBaseSoUrl(18, format, filters, this.defaultNumber, this.defaultStart, !this.defaultReverse);
			}
		};
	}]);
