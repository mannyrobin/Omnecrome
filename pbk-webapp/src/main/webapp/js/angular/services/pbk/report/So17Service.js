angular.module('armada')
	.service('So17Service', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'report/standard/so-17',
			api: ['page']
		});
	}]);
