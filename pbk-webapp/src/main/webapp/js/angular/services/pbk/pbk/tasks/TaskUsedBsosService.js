angular.module('armada')
	.service('TaskUsedBsosService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'pbk/tasks/bsos-used',
			api: ['page']
		});
	}]);
