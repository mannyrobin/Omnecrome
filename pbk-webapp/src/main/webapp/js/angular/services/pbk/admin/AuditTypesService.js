angular.module('armada')
	.service('AuditTypesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'admin/audit-types',
			api: ['page', 'get', 'slist']
		});
	}]);