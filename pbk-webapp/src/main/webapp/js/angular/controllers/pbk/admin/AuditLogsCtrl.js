angular.module('armada')
	.controller('AuditLogsCtrl', ['$scope', 'AuditLogsService', 'GridService', '$state',
		function ($scope, AuditLogsService, GridService, $state) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = AuditLogsService;

			/* Права */
			$scope.gridScope.perms = {
				add: false,
				edit: false,
				remove: false
			};

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Журнал очистки аудита"
				},
				gridScope: $scope.gridScope
			};

			/* Фильтры */
			$scope.gridScope.filters = [];

			/* Колонки грида */
			$scope.gridScope.headers = [
				{
					id: "startDate",
					label: "Период очистки",
					class: "col-md-4",
					get: function (row) {
						return row.startDate + ' - ' + row.endDate
					}
				}, {
					id: "toDate",
					label: "Удалены до",
					class: "col-md-2"
				}, {
					id: "state",
					label: "Статус",
					class: "col-md-2"
				}, {
					id: "message",
					label: "Сообщение",
					class: "col-md-3"
				}
			];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти записи журнала очистки",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "startDate",
				gridName: $state.current.name + '.auditLogsGrid',
				load: $scope.gridScope.service.getPage
			});
		}]);