angular.module('armada')
	.service('TSModelsService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/ts-models',
			api: ['page', 'get', 'add', 'edit', 'remove', 'slist']
		});
	}]);
