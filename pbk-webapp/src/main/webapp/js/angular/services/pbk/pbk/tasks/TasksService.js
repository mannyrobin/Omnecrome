angular.module('armada')
	.service('TasksService', ['$q', 'UrlService', 'BaseItemService', 'Restangular', 'VenuesService', 'PlanSchedulesService', 'ShiftsService', 'PlanBrigadesService', 'DistrictsService', 'CountiesService', function ($q, UrlService, BaseItemService, Restangular, VenuesService, PlanSchedulesService, ShiftsService, PlanBrigadesService, DistrictsService, CountiesService) {
		var serviceUrl = UrlService.getApiPrefix() + 'pbk/tasks';
		var service = BaseItemService.init({
			serviceurl: serviceUrl,
			api: ['page', 'get', 'add', 'edit', 'remove', 'slist']
		});
		service.changeStatus = function (ids, stateId) {
			var changeDto = {
				taskIds: ids,
				stateId: stateId
			};
			return Restangular.one(serviceUrl + '/changestate').customPUT(changeDto);
		};

		service.getShiftForTask = function (taskId) {
			var promise = ShiftsService.getShiftByTaskId(taskId);
			return promise;
		};

		service.getTaskWithShift = function (id) {
			var promise1 = service.getItem(id);
			var promise2 = service.getShiftForTask(id);
			/*var promise3 = service.getDistrictForTask(id);
			 var promise4 = service.getCountyForTask(id);*/
			return $q.all([promise1, promise2/*, promise3, promise4*/]).then(function (data) {
				var task = data[0];
				var shift = data[1];
				task.shiftId = shift.id;
				if (task.taskTypeId != 2) {
					task.workStartTime = shift.workStartTime;
					task.workEndTime = shift.workEndTime;
					task.breakStartTime = shift.breakStartTime;
					task.breakEndTime = shift.breakEndTime;
				}
				/*task.districtId = data[2].id;
				 task.countyId = data[3].headId;*/
				return $q.when(task);
			});
		};

		service.getGridViewByTaskId = function (id) {
			return Restangular.one(serviceUrl + '/get-by-task/' + id).get();
		};

		service.getTaskRouteType = function () {
			return Restangular.one(serviceUrl + '/task-route-type').get();
		};

		service.getTaskRoutesForType = function (typeId, taskId) {
			return Restangular.one(serviceUrl + '/task-routes').get({typeId: typeId, taskId: taskId});
		};

		service.processTasks = function (ids) {
			return Restangular.one(serviceUrl + '/process-tasks').get({ids: ids});
		};

		service.getAdditionalInfo = function (taskId) {
			return Restangular.one(serviceUrl + '/task-additional').get({taskId: taskId});
		};

		service.getAllRoutesForTask = function (taskId) {
			return Restangular.one(serviceUrl + '/task-all-routes').get({taskId: taskId});
		};

		service.getTaskDistricts = function (taskId, districtId) {
			return Restangular.one(serviceUrl + '/slist-district').get({taskId: taskId, districtId: districtId});
		};

		return service;
	}]);