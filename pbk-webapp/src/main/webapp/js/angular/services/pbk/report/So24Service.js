angular.module('armada')
	.service('So24Service', ['UrlService', 'BaseItemService', 'GridService', 'Restangular', function (UrlService, BaseItemService, GridService, Restangular) {
        var serviceUrl = UrlService.getApiPrefix() + 'report/standard/so-24';
		var service = BaseItemService.init({
			serviceurl: serviceUrl,
			api: ['page']
		});

        service.getTaskCheckType = function () {
            return Restangular.one(serviceUrl + '/task-check-type').get();
        };

		return service;
	}]);
