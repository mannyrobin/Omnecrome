angular.module('armada')
	.service('GmTsTypesService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'viss/gm/ts-types',
			api: ['slist']
		});
	}]);
