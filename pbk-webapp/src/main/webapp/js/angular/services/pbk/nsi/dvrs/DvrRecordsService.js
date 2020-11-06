angular.module('armada')
	.service('DvrRecordsService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var baseUrl = UrlService.getApiPrefix() + 'nsi/dvrs/records';
		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page']
		});

		service.bind = function (id) {
			return Restangular.one(baseUrl + '/bind').get({taskId: id});
		};

		service.play = function (url) {
			return '/api/pbk/' + baseUrl + "/play?url=" + url;
		}

		return service;
	}]);
