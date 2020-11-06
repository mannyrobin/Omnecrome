angular.module('armada')
	.controller('OfficialCardHistoryCtrl', ['$scope', '$state', '$stateParams', 'OfficialCardsService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, OfficialCardsService, $controller, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История СКК", "officialCardHeadId", OfficialCardsService, $stateParams.itemId, $state.current.name + ".officialCardHistoryGrid",
				"OfficialHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/official-cards/OfficialCardHistoryDetails.html", isNotReadOnlyUser);

		}]);
