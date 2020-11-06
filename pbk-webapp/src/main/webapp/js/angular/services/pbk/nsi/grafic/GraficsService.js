angular.module('armada')
	.service('GraficsService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var baseUrl = UrlService.getApiPrefix() + 'nsi/grafics';
		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page', 'get', 'add', 'edit', 'remove']
		});

/*		service.getListForPlan = function (filters) {
			return Restangular.one(baseUrl + '/slist-plan').get(GridService.getFilterParams(filters));
		};*/
		return service;
	}]);
