angular.module('armada')
	.service('AsmppStopsService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var baseUrl = UrlService.getApiPrefix() + 'vis/asmpp-stops';

		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: []
		});

		service.getRouteSelectList = function (filters) {
			return Restangular.one(baseUrl + '/route-slist').get(
				GridService.getFilterParams(filters));
		}

		service.getParkSelectList = function (filters) {
			return Restangular.one(baseUrl + '/park-slist').get(
				GridService.getFilterParams(filters));
		}

		service.getRouteMoveSelectList = function (filters) {
			return Restangular.one(baseUrl + '/route-move-slist').get(
				GridService.getFilterParams(filters));
		}

		service.getTripSelectList = function (filters) {
			return Restangular.one(baseUrl + '/route-trip-slist').get(
				GridService.getFilterParams(filters));
		}

		service.getTripNumSelectList = function (filters) {
			return Restangular.one(baseUrl + '/route-trip-num-slist').get(
				GridService.getFilterParams(filters));
		}

		return service;
	}]);
