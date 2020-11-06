angular.module('armada')
	.service('PeriodsService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		var baseUrl = UrlService.getApiPrefix() + 'pbk/bonuse-periods';

		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['get', 'edit', 'page', 'add', 'remove']
		});

		return service;
	}]);
