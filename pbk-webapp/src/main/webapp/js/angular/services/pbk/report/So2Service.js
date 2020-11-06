angular.module('armada')
	.service('So2Service', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'report/standard/so-2',
			api: ['page']
		});
	}]);
