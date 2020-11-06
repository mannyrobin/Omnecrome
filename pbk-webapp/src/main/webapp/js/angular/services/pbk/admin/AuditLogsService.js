angular.module('armada')
	.service('AuditLogsService', ['BaseItemService', 'UrlService', function (BaseItemService, UrlService) {
		return service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'admin/audit-logs',
			api: ['page']
		});
	}]);