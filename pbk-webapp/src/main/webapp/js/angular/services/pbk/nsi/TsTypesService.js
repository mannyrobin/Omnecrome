angular.module('armada')
	.service('TsTypesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/ts-types',
			api: ['page', 'get', 'add', 'edit', 'remove', 'slist']
		});
	}]);
