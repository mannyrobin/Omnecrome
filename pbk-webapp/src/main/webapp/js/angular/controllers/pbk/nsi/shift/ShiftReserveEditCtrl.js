/**
 * Контроллер диалога редактирования настроек.
 */
angular.module('armada')
	.controller('ShiftReserveEditCtrl', ['$controller', '$scope', '$modalInstance', 'data', 'ShiftDepartmentsService',
		function ($controller, $scope, $modalInstance, data, ShiftDepartmentsService) {

			$scope.isSelect = false;

			/* Поля ввода */
			$scope.fields = [{
				name: "reserveCount",
				type: "int",
				label: "Резерв",
				required: true
			}];

			/* Инициализация */
			$controller('BaseEditCtrl', {$scope: $scope});
			$scope.init(data, $scope.fields, ShiftDepartmentsService, $modalInstance);
		}]);