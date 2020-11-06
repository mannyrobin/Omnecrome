angular.module('armada')
	.controller('RouteRatioReCalcCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'PlanBrigadesService', 'AppConfig', '$state',
		function ($controller, $scope, $modalInstance, data, PlanBrigadesService, AppConfig, $state) {

			var startDate = new Date();
			startDate.setDate(1);

			var today = new Date();

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
					default: today,
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			data.item = {deptId: $state.params.itemId};
			$scope.init(data, $scope.fields, {addItem: PlanBrigadesService.ratioReCalc}, $modalInstance);
		}]);