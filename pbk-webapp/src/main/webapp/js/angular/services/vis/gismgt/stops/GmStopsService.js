angular.module('armada')
	.service('GmStopsService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'viss/gm/stops',
			api: ['slist']
		});
	}]);
