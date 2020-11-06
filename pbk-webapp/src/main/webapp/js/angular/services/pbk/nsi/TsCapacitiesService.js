angular.module('armada')
	.service('TsCapacitiesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/ts-capacities',
			api: ['page', 'get', 'add', 'edit', 'remove', 'slist']
		});
	}]);
