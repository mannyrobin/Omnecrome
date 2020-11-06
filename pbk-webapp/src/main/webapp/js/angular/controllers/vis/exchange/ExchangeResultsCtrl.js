angular.module('armada')
	.controller('ExchangeResultsCtrl', ['$scope', 'ExchangeResultsService', 'GridService', '$state',
		function ($scope, ExchangeResultsService, GridService, $state) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = ExchangeResultsService;

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "resultDate",
				label: "Дата ответа",
				class: "col-md-2"
			}, {
				id: "exchangeState",
				label: "Статус попытки",
				class: "col-md-2"
			}, {
				id: "comment",
				label: "Комментарий",
				class: "col-md-4"
			}, {
				id: "fileName",
				label: "Файл",
				class: "col-md-4"
			}];

			/* Кнопки */
			$scope.gridScope.buttons = {
				extraButtons: [{
					tip: "Сохранить",
					action: function (row) {
						window.location.href = "/api/pbk/vis/file/" + row.fileStreamId;
					},
					class: "glyphicon glyphicon-download",
					show: function (row) {
						return row.fileName != null;
					}
				}],
				gridScope: $scope.gridScope
			};

			/* Фильтры */
			$scope.gridScope.filters = [
				{
					name: "exchangeAttemptId",
					type: "id",
					defval: $scope.row.id,
					hide: true
				}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "resultDate",
				gridName: $state.current.name + '.exchangeResultsGrid',
				load: $scope.gridScope.service.getPage
			});
		}]);