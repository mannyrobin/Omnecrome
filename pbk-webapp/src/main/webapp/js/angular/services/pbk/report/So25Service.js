angular.module('armada')
	.service('So25Service', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
        var serviceUrl = UrlService.getApiPrefix() + 'report/standard/so-25';
		var service = BaseItemService.init({
			serviceurl: serviceUrl,
			api: ['page']
		});

		return service;
	}]);
