angular.module('armada')
	.service('TaskReportsService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'pbk/task-reports',
			api: ['get', 'edit']
		});
		return service;
	}]);
