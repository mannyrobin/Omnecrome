angular.module('armada')
	.service('SettingsService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'admin/settings',
			api: ['page', 'get', 'edit', 'slist']
		});
	}]);
