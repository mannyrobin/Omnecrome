angular.module('armada')
	.controller('BrigadesExportCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'PlanBrigadesService', 'AppConfig', '$state',
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

			$scope.save = function () {
				if ($scope.modalInstance) {
					$scope.modalInstance.close();
				}
				PlanBrigadesService.exportAllBrigades(data.item.format, $scope.fields[0].value, $scope.fields[1].value);
			};

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			data.item = {format: data.item.format};
			$scope.init(data, $scope.fields, {}, $modalInstance);
		}]);