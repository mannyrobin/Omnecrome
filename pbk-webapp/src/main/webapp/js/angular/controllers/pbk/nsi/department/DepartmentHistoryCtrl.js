angular.module('armada')
	.controller('DepartmentHistoryCtrl', ['$scope', '$state', '$stateParams', 'DepartmentsService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, DepartmentsService, $controller, isNotReadOnlyUser) {
			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История подразделения", "departmentHeadId", DepartmentsService, $stateParams.itemId, $state.current.name + ".departmentHistoryGrid",
				"DepartmentHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/departments/DepartmentHistoryDetails.html", isNotReadOnlyUser);
		}]);
