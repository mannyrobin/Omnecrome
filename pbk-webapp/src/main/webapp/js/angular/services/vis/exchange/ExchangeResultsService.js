angular.module('armada')
	.service('ExchangeResultsService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'vis/exchanges/results',
			api: ['page']
		});
	}]);
