angular.module('armada')
	.service('ParksService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		var parksUrl = UrlService.getApiPrefix() + 'nsi/parks';
		var service = BaseItemService.init({
			serviceurl: parksUrl,
			api: ['page', 'get', 'add', 'edit', 'remove', 'history', 'slist', 'version']
		});
		return service;
	}]);
