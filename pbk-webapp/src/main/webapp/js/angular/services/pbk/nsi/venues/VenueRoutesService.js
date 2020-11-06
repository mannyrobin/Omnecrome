angular.module('armada')
	.service('VenueRoutesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/venues/routes',
			api: ['page', 'add', 'remove']
		});
		return service;
	}]);
