angular.module('armada')
	.controller('BrigadesImportCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'PlanBrigadesService',
		'TasksService', '$stateParams', 'Notification',
		function ($controller, $scope, $modalInstance, data, PlanBrigadesService, TasksService, $stateParams, Notification) {

			/* Поля ввода */
			$scope.fields = [
				/*{
				 name: "description",
				 type: "textarea",
				 label: "Описание",
				 required: true
				 }*/];

			/* Получение задания для прикрепления файла */
			$scope.task = {};

			$scope.importFile = function () {
				var formData = new FormData();
				formData.append('file', $scope.task.attachedFile);
				//formData.append('description', $scope.fields[0].value);
				PlanBrigadesService.importAllBrigades(formData, $scope.task.attachedFile).then(function () {
					clearFileFormData();
					$modalInstance.close();
				}).catch(function () {
					Notification.error("Ошибка добавления файла к заданию");
				}).finally(function () {
					$scope.editMode = false;
				});
			};

			$scope.saveFile = function () {
				$scope.importFile();
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
			$scope.init(data, $scope.fields, PlanBrigadesService, $modalInstance);
		}]);
