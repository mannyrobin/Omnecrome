angular.module('armada')
	.service('ExchangeOperationService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'vis/exchange-operations',
			api: ['slist']
		});
	}]);
