angular.module('armada')
	.service('So4Service', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'report/standard/so-4',
			api: ['page']
		});

		service.getTotal = function (filters) {
			return Restangular.one(UrlService.getApiPrefix() + 'report/standard/so-4/total').get(GridService.getFilterParams(filters));
		};

		return service;
	}]);
