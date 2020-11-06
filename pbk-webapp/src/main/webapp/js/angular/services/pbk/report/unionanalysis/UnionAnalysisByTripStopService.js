angular.module('armada')
	.service('UnionAnalysisByTripStopService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {

		var baseUrl = UrlService.getApiPrefix() + 'report/union-analysis-on-date/trip-stops';

		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page']
		});

		return service;
	}]);
