angular.module('armada')
	.service('ExchangesService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		var url = UrlService.getApiPrefix() + 'vis/exchanges';
		var service = BaseItemService.init({
			serviceurl: url,
			api: ['page', 'get']
		});
		service.repeat = function (row) {
			return Restangular.one(url + '/repeat').get({id: row.id});
		};
		return service;
	}]);
