angular.module('armada')
	.controller('OfficialCardOwnersHistoryCtrl', ['$scope', 'OfficialCardsService', 'GridService', '$state', '$stateParams',
		function ($scope, OfficialCardsService, GridService, $state, $stateParams) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = OfficialCardsService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "История смены владельцев"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [
				{
					id: "surname",
					disableSort: true,
					label: "Сотрудник",
					class: "col-md-3",
					get: function (row) {
						return row.surname + ' ' + row.name + ' ' + row.patronumic;
					}
				}, {
					id: "operation",
					disableSort: true,
					label: "Операция",
					class: "col-md-3",
				}, {
					id: "pesonalNumber",
					disableSort: true,
					label: "Персональный номер",
					class: "col-md-3",

				}, {
					id: "updateDate",
					disableSort: true,
					label: "Дата обновления",
					class: "col-md-3",
					get: function (row) {
						return row.updateUser + ' (' + row.updateDate + ')'
					}
				}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "ocId",
				type: "text",
				defval: $stateParams.itemId,
				hide: true
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "updateDate",
				gridName: $state.current.name + ".ocOwnersHistoryGrid",
				load: $scope.gridScope.service.getOwnersHistoryPage
			});

			$scope.gridScope.getRowTextClassFunc = function (row) {
				if (!row) {
					return null;
				}
				return !row.active ? 'history-record' : null;
			};

		}]);