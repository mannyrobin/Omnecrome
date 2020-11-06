angular.module('armada')
	.service('TaskReportFieldsService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/task-report-fields',
			api: ['page', 'get']
		});
	}]);
