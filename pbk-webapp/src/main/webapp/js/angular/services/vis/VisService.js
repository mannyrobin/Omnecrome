angular.module('armada')
	.service('VisService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'vis/vis',
			api: ['page', 'get', 'slist']
		});
	}]);
