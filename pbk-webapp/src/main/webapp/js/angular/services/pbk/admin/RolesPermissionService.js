angular.module('armada')
	.service('RolesPermissonService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'admin/roles/permissions',
			api: ['page', 'add', 'remove', 'slist']
		});

		service.addPermissons = function (items) {
			return Restangular.one(UrlService.getApiPrefix() + 'admin/roles/permissions/add').get(items);
		}

		service.removePermissons = function (items) {
			return Restangular.one(UrlService.getApiPrefix() + 'admin/roles/permissions/remove').get(items);
		}

		return service;
	}]);