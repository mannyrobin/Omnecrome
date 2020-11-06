angular.module('armada')
	.service('AskpChecksByMovesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/askp/checks/moves',
			api: ['page']
		});
	}]);
