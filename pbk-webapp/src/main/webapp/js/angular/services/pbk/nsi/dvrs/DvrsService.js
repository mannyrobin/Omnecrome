angular.module('armada')
	.service('DvrsService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var baseUrl = UrlService.getApiPrefix() + 'nsi/dvrs';
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
