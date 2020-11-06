angular.module('armada')
	.service('ShiftDepartmentsService', ['UrlService', 'BaseItemService', function (UrlService, BaseItemService) {
		var baseUrl = UrlService.getApiPrefix() + 'nsi/shift-departmentes';
		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page', 'get', 'edit']
		});

		return service;
	}]);