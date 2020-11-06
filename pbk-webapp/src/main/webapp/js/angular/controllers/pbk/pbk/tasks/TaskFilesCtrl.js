angular.module('armada')
	.controller('TaskFilesCtrl', ['$scope', '$modal', 'GridService', '$state', 'TaskFilesService', '$stateParams', 'Notification', 'isNotReadOnlyUser', 'DvrRecordsService', 'FileViewerService',
		function ($scope, $modal, GridService, $state, TaskFilesService, $stateParams, Notification, isNotReadOnlyUser, DvrRecordsService, FileViewerService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = TaskFilesService;

			/* Права */
			$scope.gridScope.perms = {
				add: isNotReadOnlyUser,
				edit: isNotReadOnlyUser,
				remove: isNotReadOnlyUser,
				bind: isNotReadOnlyUser,
				addScan: isNotReadOnlyUser
			};

			var taskId = $stateParams.taskId != null ? $stateParams.taskId : $stateParams.itemId;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Файлы задания",
					addTip: "Добавить файл"
				},
				buttons: [{
					name: "bind",
					tip: "Привязать записи с видеорегистраторов",
					action: function () {
						DvrRecordsService.bind(taskId).then(function () {
							$scope.gridScope.grid.refreshAction();
						});
					},
					icon: "link"
				}, {
					name: "addScan",
					tip: "Добавить скан задания",
					action: GridService.getAddAction(
						"pbk/pbk/tasks/TasksFileDlg.html", "TaskFilesAddCtrl", "Загрузка скана задания", "md", $scope.gridScope, {isScan: 1}),
					icon: "picture"
				}],
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название файла",
				class: "col-md-2"
			}, {
				id: "size",
				label: "Размер Мб",
				class: "col-md-2"
			}, {
				id: "duration",
				label: "Продолжительность",
				class: "col-md-2"
			}, {
				id: "description",
				label: "Описание",
				class: "col-md-2"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: 'taskId',
				type: 'text',
				defval: taskId,
				hide: true
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти файлы",
				gridScope: $scope.gridScope
			};

			/* Кнопки */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить файл",
					removeConfirm: "Вы действительно хотите удалить файл?",
					editTip: "Редактировать файл"
				},
				check: {
					isRemoveEnable: function (row) {
						return !row.delete && row.url == null;
					},
					isEditEnable: function (row) {
						return row.url == null;
					}
				},
				extraButtons: [{
					tip: "Сохранить",
					action: function (row) {
						if (row.url != null) {
							window.location.href = row.url;
						} else {
							window.location.href = "/api/pbk/pbk/task-files/stream/" + row.streamId;
						}
					},
					class: "glyphicon glyphicon-download",
					show: function (row) {
						return row.name != null;
					}
				}, {
					tip: "Воспроизвести",
					action: function (row) {
						$modal.open({
							templateUrl: "templates/dialogs/" + "pbk/nsi/dvrs/DvrVideo.html",
							windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
							controller: "DvrRecordPlayCtrl",
							size: "md",
							resolve: {
								data: function () {
									return {
										title: "Запись с видеорегистратора",
										url: row.url
									}
								}
							}
						});
					},
					class: "glyphicon glyphicon-facetime-video",
					show: function (row) {
						return row.url != null;
					}
				}, {
					tip: "Просмотреть",
					class: "glyphicon glyphicon-picture",
					show: function (row) {
						row.viewer = FileViewerService.init(row.name);
						return row.viewer !== undefined;
					},
					action: function (row) {
						row.viewer.view("api/pbk/pbk/task-files/stream/" + row.streamId);
					}
				}],
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.taskFilesGrid',
				load: $scope.gridScope.service.getPage
			});

			$scope.gridScope.addSelItemColumn = true;

			/* Экшены */
			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/pbk/tasks/TasksFileDlg.html", "TaskFilesAddCtrl", "Загрузка файла", "md", $scope.gridScope, {isScan: 0});
			$scope.gridScope.editRow = GridService.getEditAction(
				"pbk/pbk/tasks/TaskFileEditDlg.html", "TaskFilesEditCtrl", "Редактирование файла", "sm", $scope.gridScope);
			$scope.gridScope.removeRow = function (file) {
				TaskFilesService.removeFileFromTask(taskId, file.streamId).then(function () {
					$scope.gridScope.grid.refreshAction();
				}).catch(function () {
					Notification.error("Ошибка при удалении файла задания");
				});
			};

			$scope.gridScope.grid.infoTemplate = "templates/views/pbk/pbk/tasks/taskActShowImage.html";
		}]);
