angular.module('armada')
	.service('So3Service', ['UrlService', 'BaseItemService', 'GridService', 'Restangular', function (UrlService, BaseItemService, GridService, Restangular) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'report/standard/so-3',
			api: ['page']
		});

		service.getTotal = function (filters) {
			return Restangular.one(UrlService.getApiPrefix() + 'report/standard/so-3/total').get(GridService.getFilterParams(filters));
		};

		return service;
	}]);
