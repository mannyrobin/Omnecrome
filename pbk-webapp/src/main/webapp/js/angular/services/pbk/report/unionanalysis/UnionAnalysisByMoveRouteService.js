angular.module('armada')
	.service('UnionAnalysisByMoveRouteService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {

		var baseUrl = UrlService.getApiPrefix() + 'report/union-analysis-on-date/move-routes';

		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page']
		});

		return service;
	}]);
