angular.module('armada')
	.service('UserRolesService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'admin/users/roles',
			api: ['page', 'add', 'remove', 'slist']
		});
		return service;
	}]);