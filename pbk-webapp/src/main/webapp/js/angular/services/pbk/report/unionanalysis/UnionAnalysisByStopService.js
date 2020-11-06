angular.module('armada')
	.service('UnionAnalysisByStopService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {

		var baseUrl = UrlService.getApiPrefix() + 'report/union-analysis/stops';

		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page']
		});

		return service;
	}]);
