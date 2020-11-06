angular.module('armada')
	.service('UnionAnalysisByTripMoveRouteService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {

		var baseUrl = UrlService.getApiPrefix() + 'report/union-analysis-on-date/trip-move-routes';

		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page']
		});

		return service;
	}]);
