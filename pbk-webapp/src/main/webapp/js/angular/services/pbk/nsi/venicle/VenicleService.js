angular.module('armada')
	.service('VenicleService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/venicles',
			api: ['page', 'get', 'add', 'edit', 'remove', 'history', 'slist', 'version']
		});
	}]);
