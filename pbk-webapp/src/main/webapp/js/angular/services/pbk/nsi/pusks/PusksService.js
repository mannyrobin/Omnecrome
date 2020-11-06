angular.module('armada')
	.service('PusksService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var baseUrl = UrlService.getApiPrefix() + 'nsi/pusks';
		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page', 'get', 'add', 'edit', 'remove', 'history', 'ownershistory', 'slist', 'version']
		});

		service.getListForEmployee = function (filters) {
			return Restangular.one(baseUrl + '/employee-slist').get(
				GridService.getFilterParams(filters));
		};

		return service;
	}]);
