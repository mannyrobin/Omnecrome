angular.module('armada')
	.service('DutiesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/duties',
			api: ['page', 'get', 'add', 'edit', 'remove']
		});
	}]);
