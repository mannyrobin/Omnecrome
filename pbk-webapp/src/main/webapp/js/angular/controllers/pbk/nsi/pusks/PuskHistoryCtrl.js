angular.module('armada')
	.controller('PuskHistoryCtrl', ['$scope', '$state', '$stateParams', 'PusksService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, PusksService, $controller, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История ПУсКа", "puskHeadId", PusksService, $stateParams.itemId, $state.current.name + ".puskHistoryGrid",
				"PuskHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/pusks/PuskHistoryDetails.html", isNotReadOnlyUser);
		}]);
