angular.module('armada')
	.service('ModulesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'admin/modules',
			api: ['page']
		});
	}]);