angular.module('armada')
	.controller('CountyHistoryCtrl', ['$scope', '$state', '$stateParams', 'CountiesService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, CountiesService, $controller, isNotReadOnlyUser) {
			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История Округа", "countyHeadId", CountiesService, $stateParams.itemId, $state.current.name + ".CountyHistoryGrid",
				"CountyHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/counties/CountyHistoryDetails.html", isNotReadOnlyUser);
		}]);
