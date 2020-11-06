angular.module('armada')
	.service('GmRoutesService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'viss/gm/routes',
			api: ['slist']
		});
	}]);
