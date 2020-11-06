angular.module('armada')
	.service('AskpPuskChecksService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/askp-pusk-checks',
			api: ['page', 'slist']
		});

		service.bind = function (id) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/askp-pusk-checks/bind').get({taskId: id});
		};
		return service;
	}]);
