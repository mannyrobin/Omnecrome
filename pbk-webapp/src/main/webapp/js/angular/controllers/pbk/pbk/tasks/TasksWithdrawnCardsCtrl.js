angular.module('armada')
	.controller('TasksWithdrawnCardsCtrl', ['$scope', 'TaskWithdrawnCardsService', 'GridService', '$state', '$modal', 'isNotReadOnlyUser', '$stateParams', 'EmployeesService', 'TaskWithdrawCardReportService', 'RoutesService', 'TicketsService', 'FileViewerService',
		function ($scope, TaskWithdrawnCardsService, GridService, $state, $modal, isNotReadOnlyUser, $stateParams, EmployeesService, TaskWithdrawCardReportService, RoutesService, TicketsService, FileViewerService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = TaskWithdrawnCardsService;

			/* Права */
			$scope.gridScope.perms = {
				export: isNotReadOnlyUser
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Изъятые проездные документы"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "taskDate",
				label: "Дата изъятия",
				class: "col-md-1"
			}, {
				id: "cardNumber",
				label: "Номер карты",
				class: "col-md-1"
			}, {
				id: "actNumber",
				label: "Номер акта об изъятии",
				class: "col-md-1",
				href: function (row) {
					return "pbk/withdrawn-cards/" + row.id + "/info";
				}
			}, {
				id: "routeNumber",
				label: "Маршрут",
				class: "col-md-1"
			}, {
				id: "ownerFio",
				label: "ФИО владельца карты",
				class: "col-md-2"
			}, {
				id: "violatorFio",
				label: "ФИО нарушителя",
				class: "col-md-2"
			}, {
				id: "employeeName",
				label: "Сотрудник",
				class: "col-md-2"
			}, {
				id: "cardName",
				label: "Виды проездных документов",
				class: "col-md-1"
            }, {
                id: "chipNum",
                label: "Номер чипа",
                class: "col-md-1"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [
				{
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
					name: "cardNumber",
					type: "text",
					placeholder: "Номер карты"
				}, {
					name: "actNumber",
					type: "text",
					placeholder: "Номер акта об изъятии"
				}, {
					name: "routeIds",
					type: "multiselect",
					placeholder: "Маршрут",
					load: RoutesService.getList
				}, {
					name: "ownerFio",
					type: "text",
					placeholder: "ФИО владельца карты"
				}, {
					name: "violatorFio",
					type: "text",
					placeholder: "ФИО нарушителя"
				}, {
					name: "employeeId",
					type: "select",
					placeholder: "Сотрудник",
					lazyload: EmployeesService.getList
				}, {
					name: "ticketId",
					type: "select",
					placeholder: "Вид проездного документа",
					load: TicketsService.getList
				}];

			/* Кнопки */
			$scope.gridScope.buttons = {

				extraButtons: [{
					tip: "Просмотреть",
					class: "glyphicon glyphicon-picture",
					show: function (row) {
						row.viewer = FileViewerService.init(row.fileName);
						return row.viewer !== undefined;
					},
					action: function (row) {
						row.viewer.view("api/pbk/pbk/task-files/stream/" + row.streamId);
					}
				}],
				gridScope: $scope.gridScope
			};

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти изъятый проездной документ",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "id",
				gridName: $state.current.name + '.tasksWithdrawnCardsGrid',
				load: $scope.gridScope.service.getPage,
				export: TaskWithdrawCardReportService.exportReport
			});

			$scope.gridScope.addSelItemColumn = false;

		}]);
