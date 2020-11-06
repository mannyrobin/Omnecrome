angular.module('armada')
	.service('TaskStatesService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		var serviceUrl = UrlService.getApiPrefix() + 'pbk/task-states';
		var service = BaseItemService.init({
			serviceurl: serviceUrl,
			api: ['slist']
		});
		service.getGroupList = function (taskIds) {
			return Restangular.one(serviceUrl + '/list/group')
				.get({
					taskIds: taskIds[0].value
				});
		};
		return service;
	}]);
