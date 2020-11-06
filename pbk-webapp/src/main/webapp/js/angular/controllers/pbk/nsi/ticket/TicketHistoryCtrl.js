angular.module('armada')
	.controller('TicketHistoryCtrl', ['$scope', '$state', '$stateParams', 'TicketsService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, TicketsService, $controller, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История билета", "ticketHeadId", TicketsService, $stateParams.itemId, $state.current.name + ".ticketHistoryGrid",
				"TicketHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/ticket/TicketsHistoryDetailsDlg.html", isNotReadOnlyUser);

		}]);
