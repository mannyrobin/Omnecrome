angular.module('armada')
	.service('GmParksService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'viss/gm/parks',
			api: ['slist']
		});
	}]);
