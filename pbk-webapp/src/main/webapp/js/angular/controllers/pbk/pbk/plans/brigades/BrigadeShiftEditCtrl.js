angular.module('armada')
	.controller('BrigadeShiftEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'PlanBrigadesService',
		function ($controller, $scope, $modalInstance, data, PlanBrigadesService) {

			var tpu = data.tpu;

			/* Поля ввода */
			$scope.fields = [
				{
					name: "mgtCount",
					type: "int",
					label: "Количество контролеров МГТ",
					required: true,
					onChange: function () {
						if (!tpu) {
							$scope.fields[1].value = $scope.fields[0].value * 2;
						}
					},
					maxLength: 2,
					min: 0
				}, {
					name: "gkuopCount",
					type: "int",
					label: "Количество контролеров ОП",
					required: true,
					maxLength: 2,
					min: 0
					//, readonly: tpu
				},
				{
					name: "dateFrom",
					type: "date",
					label: "Начало",
					required: false,
					default: data.date
				},
				{
					name: "dateTo",
					type: "date",
					label: "Окончание",
					required: false,
					default: data.date
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			data.manual = true;
			$scope.init(data, $scope.fields, PlanBrigadesService, $modalInstance);
		}]);