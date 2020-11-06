angular.module('armada')
	.service('ShiftsService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var baseUrl = UrlService.getApiPrefix() + 'nsi/shifts';
		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page', 'get', 'add', 'edit', 'slist']
		});

		service.getListForPlan = function (filters) {
			return Restangular.one(baseUrl + '/slist-plan').get(GridService.getFilterParams(filters));
		};

		service.getShiftByScheduleId = function (id) {
			return Restangular.one(baseUrl + '/' + id + '/schedule').get();
		};

		service.getShiftByTaskId = function (id) {
			return Restangular.one(baseUrl + '/' + id + '/task').get();
		};

		return service;
	}]);
