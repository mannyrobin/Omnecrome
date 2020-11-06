angular.module('armada')
	.controller('TaskAskpPuskChecksByRoutesCtrl', ['$scope', 'GridService', 'AskpPuskChecksService', '$state', 'RoutesService', 'TicketsService', '$stateParams', 'BsosService', 'RouteTsKindsService',
		function ($scope, GridService, AskpPuskChecksService, $state, RoutesService, TicketsService, $stateParams, BsosService, RouteTsKindsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = AskpPuskChecksService;
			$scope.taskId = $stateParams.taskId != null ? $stateParams.taskId : $stateParams.itemId;

			/* Права */
			$scope.gridScope.perms = {
				bind: true
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Журнал проверок АСКП по маршрутам"
				},
				buttons: [{
					name: "bind",
					tip: "Привязать",
					action: function () {
						AskpPuskChecksService.bind($scope.taskId).then(function () {
							$scope.gridScope.grid.refreshAction();
						});
					},
					icon: "plus"
				}],
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "routeNumber",
				label: "Маршрут",
				class: "col-md-12"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "routeId",
				type: "select",
				load: function () {
					return AskpPuskChecksService.getList([
						{
							name: "taskId",
							value: $scope.taskId,
							type: "id"
						}
					]);
				},
				placeholder: "Маршрут"
			}, {
				name: "routeTsKindId",
				type: "select",
				placeholder: "Вид транспорта",
				load: RouteTsKindsService.getList
			}, {
				name: "taskId",
				type: "text",
				defval: $scope.taskId,
				hide: true
			}, {
				name: "isRouteOnly",
				type: "text",
				defval: "1",
				hide: true
			}, {
				name: "ticketId",
				type: "select",
				load: TicketsService.getList,
				placeholder: "Билет"
			}, {
				name: {
					fromName: "startTimeFrom",
					toName: "startTimeTo"
				},
				type: "time-range",
				defval: {
					fromDate: new Date(new Date().setHours(0, 0, 0, 0)),
					toDate: new Date(new Date().setHours(23, 59, 0, 0))
				},
				placeholder: {
					from: "Время начала проверки с",
					to: "Время начала проверки по"
				}
			}/*, {
			 name: {
			 fromName: "endTimeFrom",
			 toName: "endTimeTo"
			 },
			 type: "time-range",
			 defval : {
			 fromDate: new Date(new Date().setHours(0,0,0,0)),
			 toDate: new Date(new Date().setHours(23,59,0,0))
			 },
			 placeholder: {
			 from: "Время окончания проверки с",
			 to: "Время окончания проверки по"
			 }
			 }*/];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти данные из АСКП",
				gridScope: $scope.gridScope
			};

			/* Кнопки */
			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "routeNumber",
				gridName: $state.current.name + '.askpPuskChecksByRoutesGrid',
				load: $scope.gridScope.service.getPage
			});

			$scope.gridScope.addSelItemColumn = false;

			$scope.gridScope.grid.infoTemplate = "templates/views/pbk/pbk/tasks/taskAskpPuskCheckForRoute.html";

			$scope.gridScope.showAdditionalInfo = function (row, show) {

			};
		}]);
