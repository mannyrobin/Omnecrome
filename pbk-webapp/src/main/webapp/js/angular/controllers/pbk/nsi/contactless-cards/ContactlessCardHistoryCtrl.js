angular.module('armada')
	.controller('ContactlessCardHistoryCtrl', ['$scope', '$state', '$stateParams', 'ContactlessCardsService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, ContactlessCardsService, $controller, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История  БСК", "contactlessCardHeadId", ContactlessCardsService, $stateParams.itemId, $state.current.name + ".ConCardHistoryGrid",
				"ContractlessCardHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/contactless-cards/ContractlessCardHistoryDetails.html", isNotReadOnlyUser);
		}]);
