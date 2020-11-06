angular.module('armada')
	.service('TaskFilesService', ['UrlService', 'BaseItemService', 'Restangular', function (UrlService, BaseItemService, Restangular) {
		var service = BaseItemService.init({
			serviceurl: UrlService.getApiPrefix() + 'pbk/task-files',
			api: ['page', 'get', 'edit']
		});
		service.addFileToTask = function (formData, taskId, tfile) {
			return Restangular.one(UrlService.getPrefix() + "pbk/task-files/" + taskId + "/file")
				.withHttpConfig({transformRequest: angular.identity})
				.customPOST(formData, '', null, {'Content-Type': undefined})
		};
		service.removeFileFromTask = function (taskId, streamId) {
			var ids = [];
			ids.push(streamId);
			return Restangular.one(UrlService.getPrefix() + "pbk/task-files/" + taskId + "/file/delete")
				.get({
					ids: ids
				});
		};
		return service;
	}]);
