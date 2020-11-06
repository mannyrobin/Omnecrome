angular.module('armada')
	.service('So10Service', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'report/standard/so-10',
			api: ['page']
		});
	}]);
