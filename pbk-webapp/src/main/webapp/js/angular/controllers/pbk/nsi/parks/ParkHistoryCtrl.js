angular.module('armada')
	.controller('ParkHistoryCtrl', ['$scope', '$state', '$stateParams', 'ParksService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, ParksService, $controller, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История СКК", "parkHeadId", ParksService, $stateParams.itemId, $state.current.name + ".parkHistoryGrid",
				"ParkHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/parks/ParkHistoryDetails.html", isNotReadOnlyUser);
		}]);
