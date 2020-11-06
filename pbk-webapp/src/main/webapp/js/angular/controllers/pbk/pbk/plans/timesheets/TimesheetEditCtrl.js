angular.module('armada')
	.controller('TimesheetEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'PlanTimesheetsService',
		function ($controller, $scope, $modalInstance, data, PlanTimesheetsService) {

			/* Поля ввода */
			$scope.fields = [
				{
					name: "factHours",
					type: "number",
					label: "Фактическое кол-во отработанных часов",
					required: true,
					min: 0,
					max: 24
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, PlanTimesheetsService, $modalInstance);
		}]);