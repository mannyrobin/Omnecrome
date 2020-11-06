angular.module('armada')
	.controller('VenueHistoryCtrl', ['$scope', '$state', '$stateParams', 'VenuesService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, VenuesService, $controller, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История Места встречи", "venueHeadId", VenuesService, $stateParams.itemId, $state.current.name + ".VenueHistoryGrid",
				"VenueHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/venues/venueHistoryDetailsDlg.html", isNotReadOnlyUser);

		}]);
