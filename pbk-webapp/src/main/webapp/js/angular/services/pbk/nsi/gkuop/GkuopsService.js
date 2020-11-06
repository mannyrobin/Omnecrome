angular.module('armada')
	.service('GkuopsService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/gkuops',
			api: ['page', 'get', 'add', 'edit', 'remove', 'history', 'version', 'slist']
		});
	}]);
