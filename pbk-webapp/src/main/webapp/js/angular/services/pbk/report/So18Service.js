angular.module('armada')
	.service('So18Service', ['UrlService', 'BaseItemService', 'GridService', 'Restangular', function (UrlService, BaseItemService, GridService, Restangular) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'report/standard/so-18',
			api: ['page']
		});

		service.getTotal = function (filters) {
			return Restangular.one(UrlService.getApiPrefix() + 'report/standard/so-18/total').get(GridService.getFilterParams(filters));
		}

		return service;
	}]);
