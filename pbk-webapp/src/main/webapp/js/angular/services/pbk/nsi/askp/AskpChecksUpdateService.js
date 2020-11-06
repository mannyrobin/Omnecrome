angular.module('armada')
	.service('AskpChecksUpdateService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		var service = BaseItemService.init({});
		service.update = function (item) {
			return Restangular.one(UrlService.getApiPrefix() + 'nsi/askp/checks/moves/update').post("", item);
		};

		return service;
	}]);