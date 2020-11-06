angular.module('armada')
	.service('PlanSchedulesService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var serviceUrl = UrlService.getApiPrefix() + 'pbk/plan/schedules';
		var service = BaseItemService.init({
			serviceurl: serviceUrl,
			api: ['page', 'get', 'edit', 'add', 'slist']
		});
		service.getScheduleShifts = function (filters) {
			return Restangular.one(serviceUrl + '/schedule-shifts').get(
				GridService.getFilterParams(filters));
		};
		service.getHolidayShifts = function () {
			return Restangular.one(serviceUrl + '/holiday-shifts').get();
		};
		service.getSchedule = function (filters) {
			return Restangular.one(serviceUrl + '/get').get(
				GridService.getFilterParams(filters));
		};
		service.getScheduleByTaskId = function (id) {
			return Restangular.one(serviceUrl + '/get-by-task/' + id).get();
		};
		service.getSelectItemsForCreatingTask = function (filters) {
			return Restangular.one(serviceUrl + '/slist-create-task').get(
				GridService.getFilterParams(filters));
		};
		service.getEmployeesWithoutSchedule = function (filters) {
			return Restangular.one(serviceUrl + '/slist-employee-without-schedule').get(
				GridService.getFilterParams(filters));
		};

		return service;
	}]);
