angular.module('armada')
	.service('TaskWithdrawnCardsService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		var url = UrlService.getApiPrefix() + 'pbk/tasks/withdrawn-cards';
		var service = BaseItemService.init({
			serviceurl: url,
			api: ['page', 'get', 'add', 'edit', 'remove']
		});

		return service;
	}]);
