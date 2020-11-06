angular.module('armada')
	.controller('ExchangeAttemptsCtrl', ['$scope', 'ExchangeAttemptsService', 'GridService', '$state', '$stateParams', 'ExchangeStateService',
		function ($scope, ExchangeAttemptsService, GridService, $state, $stateParams, ExchangeStateService) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = ExchangeAttemptsService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Взаимодействия"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "attemptDate",
				label: "Дата попытки",
				class: "col-md-2"
			}, {
				id: "exchangeState",
				label: "Статус попытки",
				class: "col-md-2"
			}, {
				id: "comment",
				label: "Комментарий",
				class: "col-md-4"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [
				{
					name: "exchangeId",
					type: "text",
					defval: $stateParams.itemId,
					hide: true
				},
				{
					name: "exchangeState",
					type: "select",
					load: ExchangeStateService.getList,
					placeholder: "Статус попытки"
				}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти попытку взаимодействия",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "attemptDate",
				gridName: $state.current.name + '.exchangeAttemptsGrid',
				load: $scope.gridScope.service.getPage
			});

			$scope.gridScope.grid.infoTemplate = "templates/views/vis/exchange/exchangeResults.html";

			$scope.gridScope.showAdditionalInfo = function (row, show) {
			};
		}]);