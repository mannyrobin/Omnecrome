angular.module('armada')
	.service('ExchangeObjectService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'vis/exchange-objects',
			api: ['page', 'get', 'slist']
		});
	}]);
