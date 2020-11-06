angular.module('armada')
	.controller('RolePermissionCtrl', ['$scope', 'ModulesService', 'GridService', '$state', '$stateParams',
		function ($scope, ModulesService, GridService, $state, $stateParams) {

			$scope.gridScope = $scope;
			$scope.gridScope.service = ModulesService;

			/* Заголовок */
			$scope.gridScope.titleParams = {
				labels: {
					title: "Права роли"
				},
				gridScope: $scope.gridScope
			};

			/* Колонки грида */
			$scope.gridScope.headers = [{
				id: "name",
				label: "Модуль"
			}];

			/* Фильтры */
			$scope.gridScope.filters = [];

			/* Параметры фильтрации */
			$scope.gridScope.filterParams = {
				filterString: "Найти права роли",
				gridScope: $scope.gridScope
			};

			/* Грид */
			$scope.gridScope.grid = GridService.initGridData({
				tableScope: $scope.gridScope,
				defaultPredicate: "name",
				gridName: $state.current.name + '.ModulesGrid',
				load: $scope.gridScope.service.getPage
			});

			$scope.gridScope.grid.infoTemplate = "templates/views/pbk/admin/rolePermissionsEdit.html";

			$scope.gridScope.showAdditionalInfo = function (row, show) {
				row.roleId = $stateParams.itemId;
			};
		}]);
