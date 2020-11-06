angular.module('armada')
	.service('ExchangeConfigsService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		var url = UrlService.getApiPrefix() + 'vis/exchange-configs';
		var service = BaseItemService.init({
			serviceurl: url,
			api: ['page', 'get', 'add', 'edit', 'remove', 'slist']
		});
		service.start = function (item) {
			return Restangular.one(url + '/start').post("", item);
		};
		return service;
	}]);
