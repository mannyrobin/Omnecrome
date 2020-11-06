angular.module('armada')
	.service('EquipmentsService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/equipments',
			api: ['page', 'get', 'add', 'edit', 'remove', 'slist', 'history', 'version']
		});
	}]);
