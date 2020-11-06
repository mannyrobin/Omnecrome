angular.module('armada')
	.controller('TaskAskpPuskCheckForRouteCtrl', ['$scope', 'GridService', 'AskpPuskChecksService', '$state', 'RoutesService', 'TicketsService', '$stateParams', 'BsosService', 'RouteTsKindsService',
		function ($scope, GridService, AskpPuskChecksService, $state, RoutesService, TicketsService, $stateParams, BsosService, RouteTsKindsService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = AskpPuskChecksService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Журнал данных из АСКП для маршрута " + $scope.row.routeNumber
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "moveCode",
				label: "Выход ТС",
				class: "col-md-1"
			}, {
				id: "ticketName",
				label: "Билет",
				class: "col-md-3"
			}, {
				id: "checkStartTime",
				label: "Время начала проверки",
				class: "col-md-2"
			}, {
				id: "checkEndTime",
				label: "Время конца проверки",
				class: "col-md-2"
			}, {
				id: "checkResult1Count",
				label: "Кол-во с результатом \"1\"(нет проходов)",
				class: "col-md-1"
			}, {
				id: "checkResult2Count",
				label: "Кол-во с результатом \"2\"(один проход)",
				class: "col-md-1"
			}, {
				id: "checkResult3Count",
				label: "Кол-во с результатом \"3\"( >1 прохода)",
				class: "col-md-1"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "routeId",
				type: "id",
				placeholder: "Маршрут",
				defval: $scope.row.id,
				hide: true
			}, {
				name: "taskId",
				type: "text",
				defval: $stateParams.taskId != null ? $stateParams.taskId : $stateParams.itemId,
				hide: true
			}, {
				name: "isRouteOnly",
				type: "text",
				defval: "0",
				hide: true
			}, {
				name: "ticketId",
				type: "id",
				defval: $scope.$parent.gridScope.filters[4].value != null && $scope.$parent.gridScope.filters[4].value.id > 0 ? $scope.$parent.gridScope.filters[4].value.id : null,
				hide: true
			}, {
				name: {
					fromName: "startTimeFrom",
					toName: "startTimeTo"
				},
				type: "time-range",
				defval: {
					fromDate: $scope.$parent.gridScope.filters[5].value != null ? $scope.$parent.gridScope.filters[5].value.fromDate : null,
					toDate: $scope.$parent.gridScope.filters[5].value != null ? $scope.$parent.gridScope.filters[5].value.toDate : null
				},
				hide: true
			}/*, {
			 name: {
			 fromName: "endTimeFrom",
			 toName: "endTimeTo"
			 },
			 type: "time-range",
			 defval : {
			 fromDate: $scope.$parent.gridScope.filters[6].value != null ? $scope.$parent.gridScope.filters[6].value.fromDate : null,
			 toDate: $scope.$parent.gridScope.filters[6].value != null ? $scope.$parent.gridScope.filters[6].value.toDate : null
			 },
			 hide: true
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

			$scope.gridScope.addSelItemColumn = false;

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "checkStartTime",
				gridName: $state.current.name + '.askpPuskChecksGridForRoute',
				load: $scope.gridScope.service.getPage
			});
		}]);
