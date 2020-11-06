angular.module('armada')
	.controller('DvrHistoryCtrl', ['$scope', '$state', '$stateParams', 'DvrsService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, DvrsService, $controller, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История видеорегистратора", "dvrHeadId", DvrsService, $stateParams.itemId, $state.current.name + ".dvrHistoryGrid",
				"DvrHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/dvrs/DvrHistoryDetails.html", isNotReadOnlyUser);
		}]);
