angular.module('armada')
	.service('So19Service', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'report/standard/so-19',
			api: ['page']
		});
	}]);
