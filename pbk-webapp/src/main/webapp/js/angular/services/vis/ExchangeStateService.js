angular.module('armada')
	.service('ExchangeStateService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'vis/exchange-states',
			api: ['slist']
		});
	}]);
