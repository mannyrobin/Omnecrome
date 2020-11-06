angular.module('armada')
	.service('RouteTsKindsService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/route-ts-kinds',
			api: ['page', 'get', 'add', 'edit', 'remove', 'slist']
		});
		return service;
	}]);
