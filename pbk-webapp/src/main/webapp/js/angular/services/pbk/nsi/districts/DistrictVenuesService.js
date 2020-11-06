angular.module('armada')
	.service('DistrictVenuesService', ['UrlService', 'BaseItemService', 'Restangular', 'WktParseService', function (UrlService, BaseItemService, Restangular, WktParseService) {
		var baseUrl = UrlService.getApiPrefix() + '/nsi/venues/districts';

		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page', 'add', 'remove']
		});

		return service;
	}]);
