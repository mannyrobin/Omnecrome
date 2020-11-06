angular.module('armada')
	.controller('TasksStatusChangeCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'TaskStatesService', 'TasksService', 'Restangular', 'UrlService', 'UtilService', 'Notification',
		function ($controller, $scope, $modalInstance, data, TaskStatesService, TasksService, Restangular, UrlService, UtilService, Notification) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "stateId",
					type: "select",
					load: function () {
						return TaskStatesService.getGroupList([
							{
								name: "taskIds",
								value: data.ids,
								type: "id"
							}
						]);
					},
					readonly: false,
					label: "Состояние задания",
					required: false
				}];

			$scope.changeStatus = function () {
				var stateId = $scope.fields[0].value.id;
				TasksService.changeStatus(data.ids, stateId).then(function (result) {
					$modalInstance.close();
				}).catch(function (response) {
					var errors = UtilService.getFormErrors(response);
					$scope.errTitle = errors.errTitle;
					$scope.errMessages = errors.errMessages;
				});
			};

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, TasksService, $modalInstance);
		}]);
