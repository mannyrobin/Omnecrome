angular.module('armada')
	.service('So13Service', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'report/standard/so-13',
			api: ['page']
		});
	}]);
