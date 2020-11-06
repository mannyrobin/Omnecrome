angular.module('armada')
	.service('SexesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/sexes',
			api: ['slist', 'page', 'get']
		});
	}]);
