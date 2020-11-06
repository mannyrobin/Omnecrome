angular.module('armada')
	.service('BsoTypesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/bso-types',
			api: ['slist']
		});
		return service;
	}]);