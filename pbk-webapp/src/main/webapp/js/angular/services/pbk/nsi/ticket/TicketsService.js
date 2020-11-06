angular.module('armada')
	.service('TicketsService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/tickets',
			api: ['page', 'get', 'add', 'edit', 'remove', 'history', 'slist', 'version']
		});
	}]);
