angular.module('armada')
	.service('TransportTypeService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'vis/transport-types',
			api: ['slist']
		});
	}]);
