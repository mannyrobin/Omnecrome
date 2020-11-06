angular.module('armada')
	.service('GmDistrictsService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'viss/gm/districts',
			api: ['slist']
		});
	}]);
