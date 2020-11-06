angular.module('armada')
	.service('PlanTimesheetsService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		var serviceUrl = UrlService.getApiPrefix() + 'pbk/plan/timesheets';

		var service = BaseItemService.init({
			serviceurl: serviceUrl,
			api: ['page', 'get', 'edit']
		});

		service.exportTimesheets = function (format, filters) {
			window.location.href = "/api/pbk/reports/aspose/pbk/plans/timesheet?format=" + format
				+ "&deptId=" + filters[0].value
				+ "&fromDate=" + moment(filters[2].value.fromDate).format('DD.MM.YYYY')
				+ "&toDate=" + moment(filters[2].value.toDate).format('DD.MM.YYYY');
		}

		return service;
	}]);
