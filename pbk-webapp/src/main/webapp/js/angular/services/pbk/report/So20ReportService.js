angular.module('armada')
	.service('So20ReportService', ['BaseSoReportService', function (BaseSoReportService) {
		return {
			exportReport: function (format, filters) {
				window.location.href = BaseSoReportService.getBaseSoUrl(20, format, filters, this.defaultNumber, this.defaultStart, !this.defaultReverse);
			}
		};
	}]);
