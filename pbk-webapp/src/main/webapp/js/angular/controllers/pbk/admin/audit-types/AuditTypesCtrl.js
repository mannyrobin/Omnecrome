angular.module('armada')
	.controller('AuditTypesCtrl', ['$scope', 'AuditTypesService', 'GridService', '$state',
		function ($scope, AuditTypesService, GridService, $state) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = AuditTypesService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Справочник действий"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Название",
				class: "col-md-3",
				href: function (row) {
					return "admin/audit-types/" + row.id + "/info";
				}
			}, {
				id: "description",
				label: "Описание"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [{
				name: "name",
				type: "text",
				placeholder: "Название"
			}, {
				name: "cod",
				type: "text",
				placeholder: "Код"
			}];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти событие аудита",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.AuditTypes',
				load: $scope.gridScope.service.getPage
			});
		}]);
