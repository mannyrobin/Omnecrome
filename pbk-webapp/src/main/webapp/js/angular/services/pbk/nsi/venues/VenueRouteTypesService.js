angular.module('armada')
	.service('VenueRouteTypesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/venues/route-types',
			api: ['slist']
		});
		return service;
	}]);
