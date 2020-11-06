angular.module('armada')
	.service('DistrictRoutesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/districts/routes',
			api: ['page', 'add', 'remove']
		});
		return service;
	}]);
