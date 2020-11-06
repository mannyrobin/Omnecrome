angular.module('armada')
	.controller('VenicleHistoryCtrl', ['$scope', '$state', '$stateParams', 'VenicleService', '$controller', 'isNotReadOnlyUser',
		function ($scope, $state, $stateParams, VenicleService, $controller, isNotReadOnlyUser) {

			$controller('BaseHistCtrl', {$scope: $scope});
			$scope.init("История ТС", "venicleHeadId", VenicleService, $stateParams.itemId, $state.current.name + ".venicleHistoryGrid",
				"VeniclesHistoryDetailsCtrl", "templates/dialogs/pbk/nsi/venicles/VenicleHistoryDetailsDlg.html", isNotReadOnlyUser);

		}]);
