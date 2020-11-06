angular.module('armada')
	.controller('EmployeeHistoryCtrl', ['$scope', '$controller', '$state', '$stateParams', 'EmployeesService', 'isNotReadOnlyUser',
		function ($scope, $controller, $state, $stateParams, EmployeesService, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История сотрудника", "employeeHeadId", EmployeesService, $stateParams.itemId, $state.current.name + ".employeeHistoryGrid",
				"EmployeeHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/employee/EmployeeHistoryDetails.html", isNotReadOnlyUser);
		}]);
