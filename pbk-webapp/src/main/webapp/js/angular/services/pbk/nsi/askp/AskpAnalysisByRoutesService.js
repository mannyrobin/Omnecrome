angular.module('armada')
	.service('AskpAnalysisByRoutesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/askp/analysis/routes',
			api: ['page']
		});
	}]);
