angular.module('armada')
	.controller('PlanTasksCtrl', ['$scope', 'TasksService', 'GridService', '$state', '$stateParams', 'AppConfig', '$modal', 'Notification', 'ShiftsService', 'isNotReadOnlyUser', 'VenuesService',
		function ($scope, TasksService, GridService, $state, $stateParams, AppConfig, $modal, Notification, ShiftsService, isNotReadOnlyUser, VenuesService) {
			$scope.gridScope = $scope;
			$scope.gridScope.service = TasksService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Задания",
					buttons: [{
						name: "distribute",
						tip: "Распределить маршруты по заданиям",
						action: GridService.getAddAction(
							"pbk/pbk/tasks/DistributeRoutesDlg.html", "DistributeRoutesCtrl", "Распределить маршруты по заданиям", "md", $scope.gridScope),
						tipIcon: "bed",
						status: "success"
					}]
				},
				gridScope: $scope.gridScope
			};

			var date = new Date();
			var defStartFilterDate = new Date(date.getFullYear(), date.getMonth(), 1);
			var defEndFilterDate = new Date(date.getFullYear(), date.getMonth() + 1, 0);

			/* Права */
			$scope.gridScope.perms = {};

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "deptId",
				type: "text",
				defval: $stateParams.itemId,
				hide: true
			}, {
				name: "fio",
				type: "text",
				placeholder: "ФИО"
			}, {
				name: "venueId",
				type: "select",
				placeholder: "Место встречи",
				load: function () {
					return VenuesService.getList([{
						name: "deptId",
						value: $stateParams.itemId,
						type: "text"
					}]);
				}
			}, {
				name: "shiftId",
				type: "select",
				load: function () {
					return ShiftsService.getList([
						{
							name: "isWork",
							value: 1,
							type: "id"
						}
					]);
				},
				placeholder: "Смена"
			}, {
				name: {
					fromName: "dateFrom",
					toName: "dateTo"
				},
				defval: {
					fromDate: defStartFilterDate,
					toDate: defEndFilterDate
				},
				type: "range",
				placeholder: {
					from: "Начало периода",
					to: "Окончание периода"
				}
			}];

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "taskDate",
				label: "Дата выполнения",
				class: "col-md-1",
				hrefClick: function (row) {
					$state.go('app.main.pbk.plans.detail.task-detail.info', {taskId: row.id});
				}
			}, {
				id: "employeeName",
				label: "Сотрудник",
				class: "col-md-2"
			}, {
				id: "countyName",
				label: "Округ",
				class: "col-md-1"
			}, {
				id: "shiftName",
				label: "Смена",
				class: "col-md-1"
			}, {
				id: "planVenueName",
				label: "Место встречи",
				class: "col-md-2"
			}, {
				id: "departmentName",
				label: "Маршруты",
				class: "col-md-2",
				get: function (row) {
					if (row.routes && row.routes.length > 0) {
						var result = row.routes[0].routeNumber;
						for (var i = 1; i < row.routes.length; i++) {
							result += ', ' + row.routes[i].routeNumber;
						}
					}
					return result;
				},
				disableSort: true
			}, {
				id: "bsoNumber",
				label: "Номер БСО",
				class: "col-md-1"
			}, {
				id: "taskState",
				label: "Статус",
				class: "col-md-1"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти сотрудника",
				gridScope: $scope.gridScope
			};

			/* Кнопки Edit, Remove */
			$scope.gridScope.buttons = {
				labels: {
					removeTip: "Удалить задание",
					removeConfirm: "Вы действительно хотите удалить задание?"
				},
				check: {
					isRemoveEnable: function (row) {
						return !row.delete;
					}
				},
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "taskDate",
				gridName: $state.current.name + '.planTasksGrid',
				load: $scope.gridScope.service.getPage,
                lazyLoad: true
			});
		}]);
