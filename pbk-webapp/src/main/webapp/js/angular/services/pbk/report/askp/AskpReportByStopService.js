angular.module('armada')
	.service('AskpReportByStopService', ['UrlService', 'BaseItemService', 'GridService', 'Restangular', function (UrlService, BaseItemService, GridService, Restangular) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'report/askp/stops',
			api: ['page']
		});

		return service;

	}]);
