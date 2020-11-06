angular.module('armada')
	.service('So1Service', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'report/standard/so-1',
			api: ['page']
		});
	}]);
