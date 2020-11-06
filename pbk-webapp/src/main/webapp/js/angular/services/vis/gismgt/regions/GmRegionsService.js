angular.module('armada')
	.service('GmRegionsService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'viss/gm/regions',
			api: ['slist']
		});
	}]);
