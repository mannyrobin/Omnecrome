angular.module('armada')
	.controller('DistributeRoutesCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'PlanBrigadesService', 'AppConfig', '$state',
		function ($controller, $scope, $modalInstance, data, PlanBrigadesService, AppConfig, $state) {

			var startDate = new Date();
			startDate.setDate(1);

			var today = new Date();
			var lastDayOfMonth = new Date(today.getFullYear(), today.getMonth() + 1, 0);

			/* Поля ввода */
			$scope.fields = [
				{
					name: "startDate",
					type: "date",
					label: "Начало",
					default: startDate,
					required: true
				}, {
					name: "endDate",
					type: "date",
					label: "Окончание",
					default: lastDayOfMonth,
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			data.item = {deptId: $state.params.itemId};
			$scope.init(data, $scope.fields, {addItem: PlanBrigadesService.distributeRoutes}, $modalInstance);
		}]);