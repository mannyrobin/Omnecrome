angular.module('armada')
	.controller('TaskWithdrawnCardInfoCtrl', ['$controller', '$scope', '$stateParams', 'AppConfig', 'TicketsService', 'TaskWithdrawnCardsService', 'TasksService', 'TaskFilesService', 'Notification', 'BsosService', 'withdrawnCard', 'RoutesService',
		function ($controller, $scope, $stateParams, AppConfig, TicketsService, TaskWithdrawnCardsService, TasksService, TaskFilesService, Notification, BsosService, withdrawnCard, RoutesService) {

			$scope.attachedFile = null;
			$scope.streamId = null;
			/* Поля ввода */
			$scope.fields = [
				{
					name: "bsoId",
					type: "select",
					label: "Номер акта об изъятии/Имя файла",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					required: true,
					load: function () {
						return BsosService.getBsosForTaskCard([{
							name: "taskId",
							value: withdrawnCard.taskId,
							type: "id"
						},
							{
								name: "bsoTypeId",
								value: 2,
								type: "id"
							},
							{
								name: "currentId",
								value: $scope.fields[0].value,
								type: "select"
							}]);
					},
					onChange: function () {
						$scope.fields[9].value = $scope.fields[0].value.name;
					},
					afterLoad: function () {
						$scope.oldValues[9] = $scope.fields[0].value == undefined ? null : $scope.fields[0].value.name;
						$scope.fields[9].value = $scope.fields[0].value == undefined ? null : $scope.fields[0].value.name;
					}
				}, {
					name: "cardNumber",
					type: "text",
					label: "Номер карты",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					required: false
				}, {
					name: "cardId",
					type: "select",
					label: "Тип карты",
					load: TicketsService.getList,
					required: false
				}, {
					name: "routeId",
					type: "select",
					label: "Маршрут",
					load: RoutesService.getList,
					required: false
				}, {
					name: "ownerFio",
					type: "text",
					label: "ФИО владельца карты",
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE,
					required: false
				}, {
					name: "violatorFio",
					type: "text",
					label: "ФИО нарушителя",
					maxLength: AppConfig.FIELD_LENGTHS.SHORT_INPUT_SIZE,
					required: false
				}, {
					name: "taskId",
					type: "text",
					label: "Задача",
					defval: withdrawnCard.taskId
				}, {
					name: "description",
					type: "textarea",
					label: "Описание",
					required: false
				}, {
					name: "fileId",
					type: "text",
					label: "Файл"
				}, {
					name: "fileName",
					type: "text",
					label: "Номер акта об изъятии/Имя файла",
					maxLength: AppConfig.FIELD_LENGTHS.X_SHORT_INPUT_SIZE,
					readonly: true
				}];

			$scope.addFile = function () {
				var formData = new FormData();
				if ($scope.attachedFile == null) {
					$scope.save();
					return;
				}
				formData.append('file', $scope.attachedFile);
				formData.append('name', $scope.fields[9].value);
				formData.append('description', $scope.fields[7].value);
				formData.append('isScan', 0);
				TaskFilesService.addFileToTask(formData, withdrawnCard.taskId, $scope.attachedFile).then(function (response) {
					clearFileFormData();
					$scope.fields[8].value = response.id;
					$scope.save();
				}).catch(function () {
					Notification.error("Ошибка добавления файла карты изъятия");
				}).finally(function () {
					$scope.editMode = false;
				});
			};

			$scope.saveFile = function () {
				if ($scope.attachedFile == null) {
					$scope.updateFile();
				} else {
					$scope.addFile();
				}

			}

			/* Очистка выбранного файла */
			function clearFileFormData() {
				$scope.attachedFile = null;
			}

			/* Очистка выбранного файла */
			$scope.backToCreateFile = function () {
				clearFileFormData();
				$scope.fields[8].value = null;
				$scope.fields[7].value = null;
			};

			/* Проверка для отображения */
			$scope.isFileAttached = function () {
				return angular.isDefined($scope.attachedFile) && $scope.attachedFile != null ||
					$scope.fields[8] != null && $scope.fields[8].value != null;
			};

			$scope.updateFile = function () {
				var item = {
					name: $scope.fields[9].value,
					description: $scope.fields[7].value,
					id: $scope.fields[8].value
				}
				TaskFilesService.editItem(item).then(function () {
					clearFileFormData();
					$scope.save();
				}).catch(function () {
					Notification.error("Ошибка обновления скана карты изъятия");
				});
			};

			$scope.callbackResetAction = function () {
				$scope.fields[9].value = $scope.fields[0].value.name;
				if ($scope.fields[8].value != null) {
					TaskFilesService.getItem($scope.fields[8].value).then(function (response) {
						$scope.fields[7].value = response.description;
						$scope.oldValues[7] = $scope.fields[7].value;
						$scope.oldValues[9] = $scope.fields[0].value;
						$scope.streamId = response.streamId;
					});
				}
			}

			$scope.download = function () {
				window.location.href = "/api/pbk/pbk/task-files/stream/" + $scope.streamId;
			}

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init({id: $stateParams.itemId}, $scope.fields, TaskWithdrawnCardsService);

		}]);
