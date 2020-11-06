angular.module('armada')
	.service('UnionAnalysisByRouteService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {

		var baseUrl = UrlService.getApiPrefix() + 'report/union-analysis/routes';

		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page']
		});

		return service;
	}]);
