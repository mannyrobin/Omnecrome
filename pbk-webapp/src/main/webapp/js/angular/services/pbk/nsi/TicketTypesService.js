angular.module('armada')
	.service('TicketTypesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/ticket-types',
			api: ['slist']
		});
	}]);
