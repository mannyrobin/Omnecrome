angular.module('armada')
	.service('DriversService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/drivers',
			api: ['page', 'get', 'add', 'edit', 'remove', 'slist', 'history', 'version']
		});
	}]);
