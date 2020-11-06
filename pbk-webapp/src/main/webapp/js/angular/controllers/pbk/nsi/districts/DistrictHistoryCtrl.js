angular.module('armada')
	.controller('DistrictHistoryCtrl', ['$scope', '$state', '$stateParams', 'DistrictsService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, DistrictsService, $controller, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История Района", "districtHeadId", DistrictsService, $stateParams.itemId, $state.current.name + ".DistrictHistoryGrid",
				"DistrictHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/districts/DistrictHistoryDetails.html", isNotReadOnlyUser);
		}]);
