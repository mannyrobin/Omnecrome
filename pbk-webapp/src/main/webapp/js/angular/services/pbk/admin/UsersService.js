angular.module('armada')
	.service('UsersService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var baseUrl = UrlService.getApiPrefix() + 'admin/users';
		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page', 'get', 'add', 'edit', 'remove', 'slist']
		});
		service.changePass = function (item) {
			return Restangular.one(UrlService.getApiPrefix() + 'admin/users' + '/' + item.id + '/password').customPUT(item);
		};

		service.unlock = function (item) {
			return Restangular.one(UrlService.getApiPrefix() + 'admin/users' + '/' + item.id + '/unlock').customPUT(item);
		};

		service.getListForEmployee = function (filters) {
			return Restangular.one(baseUrl + '/employee-slist').get(
				GridService.getFilterParams(filters));
		};

		return service;
	}]);