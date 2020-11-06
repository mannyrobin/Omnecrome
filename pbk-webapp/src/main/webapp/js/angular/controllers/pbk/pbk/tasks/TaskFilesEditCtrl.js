angular.module('armada')
	.controller('TaskFilesEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'TaskFilesService',
		'TasksService', '$stateParams', 'Notification',
		function ($controller, $scope, $modalInstance, data, TaskFilesService, TasksService, $stateParams, Notification) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "description",
					type: "textarea",
					label: "Описание",
					required: true
				}, {
					name: "name",
					type: "text",
					label: "Файл",
					readonly: true,
					required: true
				}];

			/* Получение задания для прикрепления файла */
			$scope.task = TasksService.getItem($stateParams.taskId != null ? $stateParams.taskId : $stateParams.itemId);

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, TaskFilesService, $modalInstance);
		}]);
