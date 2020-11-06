angular.module('armada')
	.service('GmRouteTsKindsService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'viss/gm/route-ts-kinds/',
			api: ['slist']
		});
	}]);
