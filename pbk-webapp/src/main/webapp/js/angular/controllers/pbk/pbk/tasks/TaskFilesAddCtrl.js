angular.module('armada')
	.controller('TaskFilesAddCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'TaskFilesService',
		'TasksService', '$stateParams', 'Notification', 'AppConfig',
		function ($controller, $scope, $modalInstance, data, TaskFilesService, TasksService, $stateParams, Notification, AppConfig) {


			/* Поля ввода */
			$scope.fields = [
				{
					name: "description",
					type: "textarea",
					label: "Описание",
					maxLength: AppConfig.FIELD_LENGTHS.MIDDLE_INPUT_SIZE,
					required: true
				}, {
					name: "isScan",
					type: "number",
					defval: data.item.isScan,
					required: false
				}];

			var taskId = $stateParams.taskId != null ? $stateParams.taskId : $stateParams.itemId;

			/* Получение задания для прикрепления файла */
			$scope.task = TasksService.getItem(taskId);

			$scope.addFile = function () {
				var formData = new FormData();
				formData.append('file', $scope.task.attachedFile);
				formData.append('description', $scope.fields[0].value);
				formData.append('isScan', $scope.fields[1].defval);
				TaskFilesService.addFileToTask(formData, taskId, $scope.task.attachedFile).then(function () {
					clearFileFormData();
					$modalInstance.close();
				}).catch(function () {
					Notification.error("Ошибка добавления файла к заданию");
				}).finally(function () {
					$scope.editMode = false;
				});
			};

			$scope.updateFile = function () {
				var formData = new FormData();
				formData.append('description', $scope.task.fileComment);
				formData.append('id', $scope.task.fileId);
				TaskFilesService.editItem(formData, taskId).then(function () {
					clearFileFormData();
				}).catch(function () {
					Notification.error("Ошибка обновления файла задания");
				}).finally(function () {
					$scope.editMode = false;
				});
			};

			$scope.editTaskFile = function (file) {
				$scope.editMode = true;
				if (file.fileName && file.fileName != null) {
					var filename = file.fileName.replace("C:\\fakepath", "");
					$("#upload-file-info").html(filename);
				}
				$scope.task.fileComment = file.note;
				$scope.task.fileId = file.id;
			};

			$scope.removeTaskFile = function (file) {
				TaskFilesService.removeFileFromTask(taskId, file.id).then(function () {
					//$scope.getAttachedFiles();  // refresh
				}).catch(function () {
					Notification.error("Ошибка при удалении файла задания");
				});
			};

			$scope.saveFile = function () {
				if ($scope.editMode) {
					$scope.updateFile();
				} else {
					$scope.addFile();
				}

			}

			/* Очистка выбранного файла */
			function clearFileFormData() {
				$("#upload-file-info").html("");
				$scope.task.attachedFile = null;
			}

			/* Очистка выбранного файла */
			$scope.backToCreateFile = function () {
				clearFileFormData();
			};

			/* Проверка для отображения */
			$scope.isFileAttached = function () {
				return angular.isDefined($scope.task.attachedFile) && $scope.task.attachedFile != null;
			};

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, TaskFilesService, $modalInstance);
		}]);
