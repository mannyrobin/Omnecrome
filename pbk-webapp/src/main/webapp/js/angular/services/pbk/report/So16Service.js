angular.module('armada')
	.service('So16Service', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		var url = UrlService.getApiPrefix() + 'report/standard/so-16';
		var service = BaseItemService.init({
			serviceurl: url,
			api: ['page']
		});
		service.asmpp = function (ids) {
			return Restangular.one(url + '/asmpp').get({ids: ids});
		};
		return service;
	}]);
