angular.module('armada')
	.service('VenueDistrictsService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var serviceUrl = UrlService.getApiPrefix() + 'nsi/venues/districts';
		var service = BaseItemService.init({
			serviceurl: serviceUrl,
			api: ['page']
		});

		service.getSelectDistricts = function (filters) {
			return Restangular.one(serviceUrl + '/venue-district-slist').get(
				GridService.getFilterParams(filters));
		};

		service.getSelectRoutes = function (filters) {
			return Restangular.one(serviceUrl + '/venue-district-route-slist').get(
				GridService.getFilterParams(filters));
		};

		service.getSelectStops = function (filters) {
			return Restangular.one(serviceUrl + '/venue-district-stop-slist').get(
				GridService.getFilterParams(filters));
		};

		return service;
	}]);
