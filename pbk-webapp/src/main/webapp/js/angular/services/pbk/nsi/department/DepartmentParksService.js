angular.module('armada')
	.service('DepartmentParksService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		return BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'nsi/departments/parks',
			api: ['page', 'add', 'remove', 'slist']
		});
	}]);
