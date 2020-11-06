angular.module('armada')
	.service('TaskAskpContactlessCardsService', ['UrlService', 'BaseItemService', 'Restangular', 'GridService', function (UrlService, BaseItemService, Restangular, GridService) {
		var baseUrl = UrlService.getApiPrefix() + 'pbk/task-contactless-card';
		var service = BaseItemService.init({
			serviceurl: baseUrl,
			api: ['page']
		});

		service.getTitle = function (taskId) {
			return Restangular.one(baseUrl + '/title').get({taskId: taskId});
		};

		service.bind = function (taskId) {
			return Restangular.one(baseUrl + '/bind').get({taskId: taskId});
		};

		return service;
	}]);
