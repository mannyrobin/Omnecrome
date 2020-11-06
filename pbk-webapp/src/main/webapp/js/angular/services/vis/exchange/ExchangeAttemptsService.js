angular.module('armada')
	.service('ExchangeAttemptsService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'vis/exchanges/attempts',
			api: ['page']
		});
	}]);
