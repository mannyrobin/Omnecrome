angular.module('armada')
	.service('RouteRatiosService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'pbk/plan/routes',
			api: ['page', 'get', 'add', 'edit']
		});
	}]);
