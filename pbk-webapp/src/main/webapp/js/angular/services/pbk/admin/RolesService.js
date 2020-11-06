angular.module('armada')
	.service('RolesService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'admin/roles',
			api: ['page', 'get', 'add', 'edit', 'remove']
		});
		// TODO: убрать этот метод, должен быть в сервисе пермишенов
		service.getPermissions = function (filters) {
			return Restangular.one(UrlService.getApiPrefix() + 'admin/permissions/slist').get(
				GridService.getFilterParams(filters));
		};

		return service;
	}]);