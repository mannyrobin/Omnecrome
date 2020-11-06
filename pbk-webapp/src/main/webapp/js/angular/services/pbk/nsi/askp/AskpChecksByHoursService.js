angular.module('armada')
	.service('AskpChecksByHoursService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/askp/checks/hours',
			api: ['page']
		});
	}]);
