angular.module('armada')
	.service('AskpChecksByStopsService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/askp/checks/stops',
			api: ['page', 'slist']
		});
	}]);
