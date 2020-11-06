angular.module('armada')
	.service('So7Service', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'report/standard/so-7',
			api: ['page']
		});
	}]);
