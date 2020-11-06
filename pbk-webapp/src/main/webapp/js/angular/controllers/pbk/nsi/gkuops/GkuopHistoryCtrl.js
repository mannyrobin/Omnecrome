angular.module('armada')
	.controller('GkuopHistoryCtrl', ['$scope', '$state', '$stateParams', 'GkuopsService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, GkuopsService, $controller, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История ГКУ ОП", "gkuopHeadId", GkuopsService, $stateParams.itemId, $state.current.name + ".gkuopHistoryGrid",
				"GkuopHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/gkuops/GkuopHistoryDetails.html", isNotReadOnlyUser);

		}]);
