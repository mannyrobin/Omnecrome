angular.module('armada')
	.service('StopsService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/stops',
			api: ['page', 'get', 'add', 'edit', 'remove', 'history', 'slist', 'version']
		});
	}]);
