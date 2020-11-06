angular.module('armada')
	.service('CountiesService', ['UrlService', 'BaseItemService', 'Restangular', 'WktParseService', function (UrlService, BaseItemService, Restangular, WktParseService) {
		var baseUrl = UrlService.getApiPrefix() + 'nsi/counties';

		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page', 'get', 'add', 'edit', 'remove', 'history', 'slist', 'version']
		});

		service.getEgko = function (id) {
			return Restangular.one(baseUrl + '/egko').get({id: id}).then(function (response) {
				var result = [];
				result.id = id;
				result.type = "county";
				for (var i = 0; i < response.length; i++) {
					result[i] = WktParseService.wktToGeoJson(response[i]);
				}
				return result;
			});
		}

		return service;
	}]);
