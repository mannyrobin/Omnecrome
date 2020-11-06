angular.module('armada')
	.service('DepartmentsService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService',
		function (UrlService, BaseItemService, Restangular, GridService) {
			var serviceUrl = UrlService.getApiPrefix() + 'nsi/departments';
			var service = BaseItemService.init({
				serviceurl: serviceUrl,
				api: ['page', 'get', 'add', 'edit', 'remove', 'history', 'slist', 'version']
			});

			service.getDepartsForBso = function (filters) {
				return Restangular.one(serviceUrl + '/departs-slist').get(GridService.getFilterParams(filters));
			};
			service.getDepartmentByScheduleId = function (id) {
				return Restangular.one(serviceUrl + '/' + id + '/schedule').get();
			};
			service.getSelectItemForMap = function (filters) {
				return Restangular.one(serviceUrl + '/map-slist').get(GridService.getFilterParams(filters));
			};
			service.getByVenueId = function (id) {
				return Restangular.one(serviceUrl + '/' + id + '/venue').get();
			};
			return service;
		}]);
