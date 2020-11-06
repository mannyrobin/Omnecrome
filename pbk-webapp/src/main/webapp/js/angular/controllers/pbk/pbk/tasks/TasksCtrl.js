angular.module('armada')
	.controller('TasksCtrl', ['$scope', 'TasksService', 'GridService', 'TaskStatesService', 'DepartmentsService', 'EmployeesService', '$state', '$modal',
		'isNotReadOnlyUser', 'isCreateTasks', 'TasksExportReportService', 'VenuesService', 'ShiftsService', 'FileViewerService',
		function ($scope, TasksService, GridService, TaskStatesService, DepartmentsService, EmployeesService, $state, $modal, isNotReadOnlyUser, isCreateTasks,
				  TasksExportReportService, VenuesService, ShiftsService, FileViewerService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = TasksService;

			/* Права */
			$scope.gridScope.perms = {
				export: isNotReadOnlyUser,
				gridexport: isNotReadOnlyUser,
				change: isNotReadOnlyUser,
				add: isCreateTasks
			};

			$scope.openChangeStatusWindow = function (gridScope) {
				return function () {
					var modalInstance = $modal.open({
						templateUrl: "templates/dialogs/pbk/pbk/tasks/TaskStatusChangeDlg.html",
						windowTemplateUrl: 'templates/dialogs/draggableTemplate.html',
						controller: "TasksStatusChangeCtrl",
						size: "sm",
						resolve: {
							data: function () {
								var ids = gridScope.getSelectedItemId();
								return {
									title: "Изменение статуса",
									ids: ids
								};
							}
						}
					});
					modalInstance.result.then(function () {
						gridScope.grid.refreshAction();
						gridScope.setSelectionAll(false);
					});
				};
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Журнал заданий",
					addTip: "Добавить задание"
				},
				buttons: [{
					name: "change",
					tip: "Изменить статус",
					action: $scope.openChangeStatusWindow($scope.gridScope),
					icon: "transfer",
					icon: "resize-small",
					activeWhenItemsSelected: true
				}, {
					name: "gridexport",
					tip: "Выгрузить",
					action: function () {
						TasksExportReportService.exportGridReport("xlsx", $scope.gridScope.filters, $scope.gridScope.tableState.sort.predicate, $scope.gridScope.tableState.sort.reverse);
					},
					icon: "star"
				}],
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "taskDate",
				label: "Дата выполнения",
				class: "col-md-1",
				href: function (row) {
					return "pbk/tasks/" + row.id + "/info";
				}
			}, {
				id: "employeeName",
				label: "Сотрудник",
				class: "col-md-3"
			}, {
				id: "personnelNumber",
				label: "Табельный номер",
				class: "col-md-1"
			}, {
				id: "shiftName",
				label: "Смена",
				class: "col-md-1"
			}, {
				id: "districtName",
				label: "Район",
				class: "col-md-1"
			}, {
				id: "planVenueName",
				label: "Место встречи",
				class: "col-md-2"
			}, {
				id: "departmentName",
				label: "Подразделение",
				class: "col-md-1"
			}, {
				id: "bsoNumber",
				label: "Номер БСО",
				class: "col-md-1"
			}, {
				id: "taskState",
				label: "Статус",
				class: "col-md-1"
			}, {
				id: "isEquals",
				icon: "flag",
				label: "",
				iconTip: "АСКП",
				class: "col-md-1"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: {
					fromName: "dateFrom",
					toName: "dateTo"
				},
				defval: {
					fromDate: new Date(),
					toDate: new Date()
				},
				type: "range",
				placeholder: {
					from: "Начало периода",
					to: "Окончание периода"
				}
			}, {
				name: "taskStateId",
				type: "select",
				placeholder: "Статус",
				load: TaskStatesService.getList
			}, {
				name: "departmentId",
				type: "select",
				placeholder: "Подразделение",
				load: function () {
					return DepartmentsService.getList([
						{
							name: "forPlanUse",
							value: 1,
							type: "id"
						}
					]);
				},
				onChange: function () {
					$scope.gridScope.filters[3].refresh();
				}
			}, {
				name: "employeeId",
				type: "select",
				placeholder: "Сотрудник",
				load: function () {
					return EmployeesService.getList([{
						name: "forPlanUse",
						value: 1,
						type: "id"
					}, {
						name: "includeFired",
						value: 1,
						type: "id"
					}, {
						name: "departmentId",
						value: $scope.gridScope.filters[2].value,
						type: "select"
					}]);
				}
			}, {
				name: "shiftId",
				type: "select",
				placeholder: "Смена",
				load: function () {
					return ShiftsService.getList([
						{
							name: "isWork",
							value: 1,
							type: "id"
						}
					]);
				}
			}, {
				name: "venueId",
				type: "select",
				placeholder: "Место встречи",
				load: VenuesService.getList
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти задания",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {

				extraButtons: [{
					tip: "Просмотреть",
					class: "glyphicon glyphicon-picture",
					show: function (row) {
						row.viewer = FileViewerService.init(row.scanFileName);
						return row.viewer !== undefined;
					},
					action: function (row) {
						row.viewer.view("api/pbk/pbk/task-files/stream/" + row.scanStreamId);
					}
				}],

				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "taskDate",
				gridName: $state.current.name + '.tasksGrid',
				load: $scope.gridScope.service.getPage,
				export: TasksExportReportService.exportReport,
				exportFormats: ["xlsx", "pdf", "csv", "zip"],
                lazyLoad: true
			});

			$scope.gridScope.addSelItemColumn = true;

			$scope.gridScope.addRow = GridService.getAddAction(
				"pbk/pbk/plans/tasks/TaskAddDlg.html", "TaskAddCtrl", "Создание задания", "md", $scope.gridScope, {
					taskTypeId: {
						id: 2,
						name: 'Особые задания'
					}, stateId: {id: 1, name: 'Создано'}
				});
			$scope.gridScope.removeRow = GridService.getRemoveAction(
				"Ошибка при удалении задания", $scope.gridScope, $scope.gridScope.service.removeItem)
		}]);
