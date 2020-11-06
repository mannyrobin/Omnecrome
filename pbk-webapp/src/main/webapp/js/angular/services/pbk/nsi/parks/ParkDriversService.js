angular.module('armada')
	.service('ParkDriversService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/parks/drivers',
			api: ['page', 'add', 'remove']
		});
		return service;
	}]);
