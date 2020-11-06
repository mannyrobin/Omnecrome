angular.module('armada')
	.service('BranchesService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/branches',
			api: ['slist']
		});
		return service;
	}]);
