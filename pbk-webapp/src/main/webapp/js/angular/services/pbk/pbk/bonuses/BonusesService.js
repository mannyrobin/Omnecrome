angular.module('armada')
	.service('BonusesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		var baseUrl = UrlService.getApiPrefix() + 'pbk/bonuses';

		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['get', 'edit', 'page', 'add', 'remove', 'slist']
		});

		return service;
	}]);
