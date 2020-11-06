angular.module('armada')
	.service('ContactlessCardsService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var baseUrl = UrlService.getApiPrefix() + 'nsi/contactless-cards';
		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page', 'get', 'add', 'edit', 'remove', 'slist', 'ownershistory', 'history', 'version']
		});

		service.getListForEmployee = function (filters) {
			return Restangular.one(baseUrl + '/employee-slist').get(
				GridService.getFilterParams(filters));
		};

		return service;
	}]);
