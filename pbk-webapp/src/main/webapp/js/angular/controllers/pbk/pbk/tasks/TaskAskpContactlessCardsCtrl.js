angular.module('armada')
	.controller('TaskAskpContactlessCardsCtrl', ['$scope', 'TaskAskpContactlessCardsService', 'GridService', '$state', '$stateParams', 'RoutesService', 'taskWithShift',
		function ($scope, TaskAskpContactlessCardsService, GridService, $state, $stateParams, RoutesService, taskWithShift) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = TaskAskpContactlessCardsService;
			var toDate = new Date();
			toDate.setSeconds(59);
			var taskId = $stateParams.taskId != null ? $stateParams.taskId : $stateParams.itemId;

			/* Права */
			$scope.gridScope.perms = {
				bind: true
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: $scope.$parent.concatlessTitle
				},
				buttons: [{
					name: "bind",
					tip: "Привязать",
					action: function () {
						TaskAskpContactlessCardsService.bind(taskId).then(function () {
							$scope.gridScope.grid.refreshAction();
						});
					},
					icon: "plus"
				}],
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "checkTime",
				label: "Время прохода",
				class: "col-md-5"
			}, {
				id: "routeNumber",
				label: "Маршрут",
				class: "col-md-3"
			}, {
				id: "moveCode",
				label: "Выход",
				class: "col-md-3"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [
				{
					name: "taskId",
					type: "id",
					defval: taskId,
					hide: true
				},
				{
					name: {
						fromName: "dateFrom",
						toName: "dateTo"
					},
					defval: {
						fromDate: new Date(taskWithShift.workStartTime),
						toDate: new Date(taskWithShift.workEndTime)
					},
					type: "time-range",
					placeholder: {
						from: "Начало периода",
						to: "Окончание периода"
					}
				},
				{
					name: "routeId",
					type: "select",
					placeholder: "Маршрут",
					load: RoutesService.getList
				},
				{
					name: "moveCode",
					type: "text",
					placeholder: "Выход"
				}
			];

			$scope.gridScope.buttons = {
				gridScope: $scope.gridScope
			};

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти проход по БСК",
				gridScope: $scope.gridScope
			};

			$scope.gridScope.addSelItemColumn = false;

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "checkTime",
				gridName: $state.current.name + '.taskAskpContactlessCardGrid' + taskWithShift.shiftId,
				load: $scope.gridScope.service.getPage
			});
		}]);
