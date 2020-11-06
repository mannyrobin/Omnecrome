angular.module('armada')
	.controller('TaskAskpPuskChecksByHoursCtrl', ['$scope', 'GridService', 'AskpPuskChecksService', '$state', 'RoutesService', 'TicketsService', '$stateParams', 'BsosService', 'RouteTsKindsService',
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
					title: "Журнал проверок АСКП по часам"
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
				id: "checkStartTime",
				label: "Начало проверки",
				class: "col-md-12",
				get: function (row) {
					return moment(row.checkStartTime, "DD.MM.YYYY HH:mm").format("HH:mm");
				}
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
				name: "isHoursOnly",
				type: "text",
				defval: "1",
				hide: true
			}, {
				name: "ticketId",
				type: "select",
				load: TicketsService.getList,
				placeholder: "Билет"
			}];

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
				defaultPredicate: "checkStartTime",
				gridName: $state.current.name + '.askpPuskChecksByHoursGrid',
				load: $scope.gridScope.service.getPage
			});

			$scope.gridScope.addSelItemColumn = false;

			$scope.gridScope.grid.infoTemplate = "templates/views/pbk/pbk/tasks/taskAskpPuskCheckForHour.html";

			$scope.gridScope.showAdditionalInfo = function (row, show) {

			};
		}]);
