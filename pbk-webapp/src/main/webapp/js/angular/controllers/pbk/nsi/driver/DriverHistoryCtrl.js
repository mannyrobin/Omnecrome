angular.module('armada')
	.controller('DriverHistoryCtrl', ['$scope', '$state', '$stateParams', 'DriversService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, DriversService, $controller, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История водителя", "driverHeadId", DriversService, $stateParams.itemId, $state.current.name + ".driverHistoryGrid",
				"DriverHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/driver/DriverHistoryDetails.html", isNotReadOnlyUser);

		}]);
