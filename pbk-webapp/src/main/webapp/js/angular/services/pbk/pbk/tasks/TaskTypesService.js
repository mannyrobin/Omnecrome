angular.module('armada')
	.service('TaskTypesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'pbk/tasks/types',
			api: ['slist']
		});
	}]);