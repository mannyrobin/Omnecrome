angular.module('armada')
	.controller('PeriodEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'PeriodsService',
		function ($controller, $scope, $modalInstance, data, PeriodsService) {

			$scope.fields = [
				{
					name: "countFrom",
					type: "int",
					label: "От",
					required: true
				}, {
					name: "countTo",
					type: "int",
					label: "До",
					required: false
				}, {
					name: "percent",
					type: "int",
					label: "Процент",
					required: true
				}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, PeriodsService, $modalInstance);
		}]);