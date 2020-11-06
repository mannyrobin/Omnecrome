angular.module('armada')
	.service('BsoNumberRulesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/bso-number-rules',
			api: ['page', 'get', 'add', 'edit', 'remove']
		});
		return service;
	}]);