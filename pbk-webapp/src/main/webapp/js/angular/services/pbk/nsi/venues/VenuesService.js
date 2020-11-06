angular.module('armada')
	.service('VenuesService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var baseUrl = UrlService.getApiPrefix() + 'nsi/venues';

		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page', 'get', 'add', 'edit', 'remove', 'slist', 'history', 'version']
		});

		service.removeDistrict = function (removeObj) {
			return Restangular.one(baseUrl + '/remove-districts').get(removeObj);
		};

		service.addDistrict = function (item) {
			return Restangular.one(baseUrl + '/add-district').post("", item);
		};

		service.getListForPlan = function (filters) {
			return Restangular.one(baseUrl + '/slist-plan').get(GridService.getFilterParams(filters));
		};

		service.restore = function (item) {
			return Restangular.one(baseUrl + '/restore-venue').customPUT(item);
		}

		return service;
	}]);
