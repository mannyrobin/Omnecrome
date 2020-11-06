angular.module('armada')
	.service('So20Service', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'report/standard/so-20',
			api: ['page']
		});
	}]);
